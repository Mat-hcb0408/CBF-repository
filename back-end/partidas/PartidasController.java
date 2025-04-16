package com.cbf1.cbf1.partidas;

import com.cbf1.cbf1.Conexao;
import com.cbf1.cbf1.receitas.Receitas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PartidasController {


    @FXML
    TextField txtPesquisar;
    @FXML
    Button btnEditar;
    @FXML
    Button btnRemover;
    @FXML
    Button btnDespesa;

    @FXML
    private TableView<Partidas> tableViewPartidas;
    @FXML
    private TableColumn<Partidas, Integer> colIdPartida;
    @FXML
    private TableColumn<Partidas, Integer> colGolMand;
    @FXML
    private TableColumn<Partidas, Integer> colGolVisit;
    @FXML
    private TableColumn<Partidas, String> colDataPartida;

    private ObservableList<Partidas> listaPartidas;

    public void initialize() {
        txtPesquisar.textProperty().addListener((observable, oldValue, newValue)->{
            pesquisarFederacoes(newValue);
        });

        this.colIdPartida.setCellValueFactory(new PropertyValueFactory<>("id_partida"));
        this.colGolMand.setCellValueFactory(new PropertyValueFactory<>("gols_mandante"));
        this.colGolVisit.setCellValueFactory(new PropertyValueFactory<>("gols_visitante"));
        this.colDataPartida.setCellValueFactory(new PropertyValueFactory<>("data_partida"));

        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.colDataPartida.setCellValueFactory(new PropertyValueFactory<>("data_partida"));
        colDataPartida.setCellFactory(column -> new TableCell<Partidas, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    try {
                        LocalDate data = LocalDate.parse(item); // Assume formato ISO vindo do banco (ex: 2025-04-15)
                        setText(data.format(formatoBR));
                    } catch (Exception e) {
                        setText(item); // Se não conseguir converter, exibe como veio
                    }
                }
            }
        });

        loadFromDatabase();
    }

    private void loadFromDatabase() {
        this.listaPartidas = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from partidas";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            Partidas f=new Partidas(
                    rs.getInt("idPartida"),
                    rs.getInt("golsMandante"),
                    rs.getInt("golsVisitante"),
                    rs.getString("realizacao")
            );
            this.listaPartidas.add(f);
        }
            this.tableViewPartidas.setItems(this.listaPartidas);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisarFederacoes(String filtro) {
        ObservableList<Partidas> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM partidas WHERE " +
                "CAST(idPartida AS CHAR) like ? or " +
                "golsMandante LIKE ? or " +
                "golsVisitante like ? or " +
                "realizacao like ?";

        try (Connection conn= Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i=1;i<=4;i++){
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Partidas f=new Partidas(
                        rs.getInt("idPartida"),
                        rs.getInt("golsMandante"),
                        rs.getInt("golsVisitante"),
                        rs.getString("realizacao")
                );
                resultados.add(f);
            }
            this.tableViewPartidas.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}