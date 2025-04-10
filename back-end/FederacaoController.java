package com.cbf.cbf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FederacaoController {


    @FXML
    TextField txtPesquisar;
    @FXML
    Button bttnBuscar;
    @FXML
    Button bttnCadastrar;

    @FXML
    private TableView<Federacao> federacoesTableView;
    @FXML
    private TableColumn<Federacao, Integer> id;
    @FXML
    private TableColumn<Federacao, String> nome;
    @FXML
    private TableColumn<Federacao, String> sigla;
    @FXML
    private TableColumn<Federacao, String> estado;

    private ObservableList<Federacao> listaFederacoes = FXCollections.observableArrayList();

    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("idFederacao"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome_federacao"));
        sigla.setCellValueFactory(new PropertyValueFactory<>("sigla_federacao"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado_federacao"));

        loadFromDatabase();
    }

    private void loadFromDatabase() {
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from Federacoes";

        try (
            Connection conn=DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            Federacao f=new Federacao(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("sigla"),
                    rs.getString("estado")
            );listaFederacoes.add(f);
        }
        federacoesTableView.setItems(listaFederacoes);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}