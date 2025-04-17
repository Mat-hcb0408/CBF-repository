package org.cbf.cbf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.cbf.cbf.repository.Conexao;
import org.cbf.cbf.model.Federacao;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TelaFederacaoController {


    @FXML
    TextField txtPesquisar;
    @FXML
    Button bttnBuscar;
    @FXML
    Button bttnCadastrar;

    @FXML
    private TableView<Federacao> federacoesTableView;
    @FXML
    private TableColumn<Federacao, Integer> colId;
    @FXML
    private TableColumn<Federacao, String> colNome;
    @FXML
    private TableColumn<Federacao, String> colSigla;
    @FXML
    private TableColumn<Federacao, String> colEstado;

    private ObservableList<Federacao> listaFederacoes;

    public void initialize() {
        txtPesquisar.textProperty().addListener((observable, oldValue, newValue)->{
            pesquisarFederacoes(newValue);
        });

        this.colId.setCellValueFactory(new PropertyValueFactory<>("idFederacao"));
        this.colNome.setCellValueFactory(new PropertyValueFactory<>("nome_federacao"));
        this.colSigla.setCellValueFactory(new PropertyValueFactory<>("sigla_federacao"));
        this.colEstado.setCellValueFactory(new PropertyValueFactory<>("estado_federacao"));

        loadFromDatabase();
    }

    private void loadFromDatabase() {
        this.listaFederacoes = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from federacoes";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            Federacao f=new Federacao(
                    rs.getInt("idFederacao"),
                    rs.getString("nome_federacao"),
                    rs.getString("sigla_federacao"),
                    rs.getString("estado_federacao")
            );
            this.listaFederacoes.add(f);
        }
            this.federacoesTableView.setItems(this.listaFederacoes);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisarFederacoes(String filtro) {
        ObservableList<Federacao> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM federacoes WHERE " +
                "CAST(idFederacao AS CHAR) like ? or " +
                "nome_federacao LIKE ? or " +
                "sigla_federacao like ? or " +
                "estado_federacao like ?";

        try (Connection conn= Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i=1;i<=4;i++){
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Federacao f=new Federacao(
                    rs.getInt("idFederacao"),
                    rs.getString("nome_federacao"),
                    rs.getString("sigla_federacao"),
                    rs.getString("estado_federacao")
            );
            resultados.add(f);
        }
            this.federacoesTableView.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}