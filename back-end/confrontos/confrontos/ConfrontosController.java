package com.cbf1.cbf1.confrontos;

import com.cbf1.cbf1.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConfrontosController {


    @FXML
    TextField txtPesquisar;
    @FXML
    Button btnEditar;
    @FXML
    Button btnRemover;
    @FXML
    Button btnPartidas;

    @FXML
    private TableView<Confrontos> tableViewConfrontos;
    @FXML
    private TableColumn<Confrontos, Integer> colIdClubeConfronto;
    @FXML
    private TableColumn<Confrontos, Integer> colIdPartidaConfronto;
    @FXML
    private TableColumn<Confrontos, Integer> colMandanteConfronto;

    private ObservableList<Confrontos> listaConfrontos;

    public void initialize() {
        txtPesquisar.textProperty().addListener((observable, oldValue, newValue)->{
            pesquisarConfrontos(newValue);
        });

        this.colIdClubeConfronto.setCellValueFactory(new PropertyValueFactory<>("id_clubeConfronto"));
        this.colIdPartidaConfronto.setCellValueFactory(new PropertyValueFactory<>("id_partidaConfronto"));
        this.colMandanteConfronto.setCellValueFactory(new PropertyValueFactory<>("id_mandanteConfronto"));

        loadFromDatabase();
    }

    private void loadFromDatabase() {
        this.listaConfrontos = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from confrontos";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            Confrontos con=new Confrontos(
                    rs.getInt("idClube"),
                    rs.getInt("idPartida"),
                    rs.getInt("mandante")
            );
            this.listaConfrontos.add(con);
        }
            this.tableViewConfrontos.setItems(this.listaConfrontos);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisarConfrontos(String filtro) {
        ObservableList<Confrontos> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM confrontos WHERE " +
                "CAST(idClube AS CHAR) like ? or " +
                "CAST(idPartida AS CHAR) like ? or " +
                "mandante LIKE ?";

        try (Connection conn= Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i=1;i<=3;i++){
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Confrontos c=new Confrontos(
                        rs.getInt("idClube"),
                        rs.getInt("idPartida"),
                        rs.getInt("mandante")
                );
                resultados.add(c);
            }
            this.tableViewConfrontos.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}