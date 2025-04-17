package org.cbf.cbf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cbf.cbf.repository.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.cbf.cbf.model.Atletas;

import java.io.IOException;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaAtletasController {


    @FXML
    TextField txtPesquisar;
    @FXML
    Button btnEditar;
    @FXML
    Button btnRemover;
    @FXML
    Button btnClubes;

    @FXML
    private TableView<Atletas> tableViewAtletas;
    @FXML
    private TableColumn<Atletas, Integer> colIdAtletas;
    @FXML
    private TableColumn<Atletas, String> colNomeAtletas;
    @FXML
    private TableColumn<Atletas, String> colNascimentoAtletas;
    @FXML
    private TableColumn<Atletas, String> colApelidoAtletas;
    @FXML
    private TableColumn<Atletas, Integer> colNumAtletas;

    private ObservableList<Atletas> listaAtletas;

    public void initialize() {
        txtPesquisar.textProperty().addListener((observable, oldValue, newValue)->{
            pesquisarFederacoes(newValue);
        });

        this.colIdAtletas.setCellValueFactory(new PropertyValueFactory<>("id_atletas"));
        this.colNomeAtletas.setCellValueFactory(new PropertyValueFactory<>("nome_atletas"));
        this.colApelidoAtletas.setCellValueFactory(new PropertyValueFactory<>("apelido_atletas"));
        this.colNumAtletas.setCellValueFactory(new PropertyValueFactory<>("num_atletas"));
        this.colNascimentoAtletas.setCellValueFactory(new PropertyValueFactory<>("nascimento_atletas"));

        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        colNascimentoAtletas.setCellFactory(column -> new TableCell<Atletas, String>() {
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

    private void trocarCena(String caminhoFxml, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irParaClubes(ActionEvent event) {
        trocarCena("/fxml/TelaClubes.fxml", event);
    }

    private void loadFromDatabase() {
        this.listaAtletas = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from atletas";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            Atletas a=new Atletas(
                    rs.getInt("IdAtleta"),
                    rs.getString("nome"),
                    rs.getString("dataNascimento"),
                    rs.getString("apelido"),
                    rs.getInt("numeracao")
            );
            this.listaAtletas.add(a);
        }
            this.tableViewAtletas.setItems(this.listaAtletas);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisarFederacoes(String filtro) {
        ObservableList<Atletas> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM atletas WHERE " +
                "CAST(IdAtleta AS CHAR) like ? or " +
                "nome LIKE ? or " +
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
                Atletas a=new Atletas(
                        rs.getInt("IdAtleta"),
                        rs.getString("nome"),
                        rs.getString("dataNascimento"),
                        rs.getString("apelido"),
                        rs.getInt("numeracao")
                );
                resultados.add(a);
            }
            this.tableViewAtletas.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}