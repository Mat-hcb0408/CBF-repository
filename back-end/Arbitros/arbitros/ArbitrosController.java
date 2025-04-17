package com.cbf1.cbf1.arbitros;

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

public class ArbitrosController {


    @FXML
    TextField txtPesquisar;
    @FXML
    Button btnEditar;
    @FXML
    Button btnRemover;
    @FXML
    Button btnNOvo;

    @FXML
    private TableView<Arbitros> tableViewArbitros;
    @FXML
    private TableColumn<Arbitros, Integer> colId;
    @FXML
    private TableColumn<Arbitros, String> colNome;
    @FXML
    private TableColumn<Arbitros, Integer> colIdFederacao;

    private ObservableList<Arbitros> listaArbitros;

    public void initialize() {
        txtPesquisar.textProperty().addListener((observable, oldValue, newValue)->{
            pesquisar(newValue);
        });

        this.colId.setCellValueFactory(new PropertyValueFactory<>("id_arbitros"));
        this.colNome.setCellValueFactory(new PropertyValueFactory<>("nome_arbitros"));
        this.colIdFederacao.setCellValueFactory(new PropertyValueFactory<>("id_federacao"));

        loadFromDatabase();
    }

    private void loadFromDatabase() {
        this.listaArbitros = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from arbitros";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            Arbitros ar=new Arbitros(
                    rs.getInt("IdArbitro"),
                    rs.getString("nome"),
                    rs.getInt("idFederacao")
            );
            this.listaArbitros.add(ar);
        }
            this.tableViewArbitros.setItems(this.listaArbitros);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisar(String filtro) {
        ObservableList<Arbitros> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM arbitros WHERE " +
                "CAST(IdArbitro AS CHAR) like ? or " +
                "nome LIKE ? or "+
                "CAST(idFederacao AS CHAR) like ?";

        try (Connection conn= Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i=1;i<=3;i++){
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Arbitros ar=new Arbitros(
                        rs.getInt("IdArbitro"),
                        rs.getString("nome"),
                        rs.getInt("idFederacao")
                );
                resultados.add(ar);
            }
            this.tableViewArbitros.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}