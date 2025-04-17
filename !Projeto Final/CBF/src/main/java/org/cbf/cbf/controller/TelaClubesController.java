package org.cbf.cbf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.cbf.cbf.model.utils.JanelaUtil;
import org.cbf.cbf.repository.Conexao;
import org.cbf.cbf.model.entity.Clubes;

import java.io.IOException;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TelaClubesController {


    @FXML
    TextField txtPesquisar;
    @FXML
    Button btnEditar;
    @FXML
    Button btnRemover;
    @FXML
    Button btnClubes;

    @FXML
    Button btnVoltar;

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
        txtPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
            pesquisarClubes(newValue);
        });

        // Configuração das colunas
        this.colIdClubes.setCellValueFactory(new PropertyValueFactory<>("id_clube"));
        this.colNomeClubes.setCellValueFactory(new PropertyValueFactory<>("nome_clube"));
        this.colFundacaoClubes.setCellValueFactory(new PropertyValueFactory<>("fundacao_clube"));
        this.colEstadoClubes.setCellValueFactory(new PropertyValueFactory<>("estado_clube"));
        this.colEscudoClubes.setCellValueFactory(new PropertyValueFactory<>("escudo_clube"));
        this.colIdFedClubes.setCellValueFactory(new PropertyValueFactory<>("idFed_clube"));

        centralizarEscudos();
        loadFromDatabase();
    }

    @FXML
    private void handleVoltarAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        JanelaUtil.trocarCenaComEstado(stage, "/fxml/Home.fxml");
    }


    private void trocarCena(String caminhoFxml,ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irParaAtletas(ActionEvent event) {
        trocarCena("/fxml/TelaAtletas.fxml", event);
    }

    private void centralizarEscudos(){
        // Centralizar imagens na coluna "Escudo"
        this.colEscudoClubes.setCellFactory(column -> new TableCell<Clubes, String>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(String imageUrl, boolean empty) {
                super.updateItem(imageUrl, empty);

                if (empty || imageUrl == null || imageUrl.isEmpty()) {
                    setGraphic(null);
                } else {
                    Image image = new Image(imageUrl, true);
                    imageView.setImage(image);

                    // Usando StackPane para centralizar a imagem
                    StackPane pane = new StackPane();
                    pane.getChildren().add(imageView);
                    setGraphic(pane);
                }
            }
        });
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
                    rs.getString("escudo"),
                    rs.getString("nome_clube"),
                    rs.getInt("anoFundacao"),
                    rs.getString("estado"),
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

        String query="SELECT * FROM clubes WHERE " +
                "CAST(idClube AS CHAR) like ? or " +
                "nome_clube LIKE ? or " +
                "anoFundacao like ? or " +
                "estado like ? or "+
                "idFederacao like ?";

        try (Connection conn= Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i=1;i<=5;i++){
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Clubes c=new Clubes(
                        rs.getInt("idClube"),
                        rs.getString("escudo"),
                        rs.getString("nome_clube"),
                        rs.getInt("anoFundacao"),
                        rs.getString("estado"),
                        rs.getInt("idFederacao")
                );
                resultados.add(c);
            }
            this.tableViewClubes.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}