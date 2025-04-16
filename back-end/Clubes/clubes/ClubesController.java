package com.cbf1.cbf1.clubes;

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


public class ClubesController {


    @FXML
    TextField txtPesquisar;
    @FXML
    Button btnEditar;
    @FXML
    Button btnRemover;
    @FXML
    Button btnClubes;

    @FXML
    private TableView<Clubes> tableViewClubes;
    @FXML
    private TableColumn<Clubes, Integer> colIdClubes;
    @FXML
    private TableColumn<Clubes, String> colNomeClubes;
    @FXML
    private TableColumn<Clubes, Integer> colFundacaoClubes;
    @FXML
    private TableColumn<Clubes, String> colEstadoClubes;
    @FXML
    private TableColumn<Clubes, String> colEscudoClubes;
    @FXML
    private TableColumn<Clubes, Integer> colIdFedClubes;

    private ObservableList<Clubes> listaClubes;

    public void initialize() {
        txtPesquisar.textProperty().addListener((observable, oldValue, newValue)->{
            pesquisarClubes(newValue);
        });

        this.colIdClubes.setCellValueFactory(new PropertyValueFactory<>("id_clube"));
        this.colNomeClubes.setCellValueFactory(new PropertyValueFactory<>("nome_clube"));
        this.colEstadoClubes.setCellValueFactory(new PropertyValueFactory<>("estado_clube"));
        this.colEscudoClubes.setCellValueFactory(new PropertyValueFactory<>("escudo_clube"));
        this.colFundacaoClubes.setCellValueFactory(new PropertyValueFactory<>("fundacao_clube"));
        this.colIdFedClubes.setCellValueFactory(new PropertyValueFactory<>("idFed_clube"));

        loadFromDatabase();
    }

    private void loadFromDatabase() {
        this.listaClubes = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from clubes";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            Clubes c=new Clubes(
                    rs.getInt("idClube"),
                    rs.getString("nome_clube"),
                    rs.getString("estado"),
                    rs.getInt("anoFundacao"),
                    rs.getString("escudo"),
                    rs.getInt("idFederacao")
            );
            this.listaClubes.add(c);
        }
            this.tableViewClubes.setItems(this.listaClubes);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisarClubes(String filtro) {
        ObservableList<Clubes> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM clube WHERE " +
                "CAST(idClube AS CHAR) like ? or " +
                "nome_clube LIKE ? or " +
                "dataNascimento like ? or " +
                "apelido like ? or "+
                "numeracao like ?";

        try (Connection conn= Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i=1;i<=5;i++){
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Clubes c=new Clubes(
                        rs.getInt("IdAtleta"),
                        rs.getString("nome"),
                        rs.getString("dataNascimento"),
                        rs.getString("apelido"),
                        rs.getInt("numeracao")
                );
                resultados.add(c);
            }
            this.tableViewClubes.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}