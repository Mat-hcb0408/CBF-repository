package org.cbf.cbf.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.cbf.cbf.DB.Conexao;
import org.cbf.cbf.POJO.ContratoAtletas;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaContratoAtletasController {


    @FXML
    TextField txtPesquisar;
    @FXML
    Button btnAdicionar;
    @FXML
    Button btnRemover;

    @FXML
    private TableView<ContratoAtletas> tableViewContratoAtletas;
    @FXML
    private TableColumn<ContratoAtletas, Integer> colId;
    @FXML
    private TableColumn<ContratoAtletas, Integer> colAtleta;
    @FXML
    private TableColumn<ContratoAtletas, Integer> colClube;
    @FXML
    private TableColumn<ContratoAtletas, String> colInicio;
    @FXML
    private TableColumn<ContratoAtletas, String> colTermino;
    @FXML
    private TableColumn<ContratoAtletas, String> colTipo;
    @FXML
    private TableColumn<ContratoAtletas, Boolean> colStatus;
    @FXML
    private ObservableList<ContratoAtletas> listaContratosAtletas;

    public void initialize() {
        txtPesquisar.textProperty().addListener((observable, oldValue, newValue)->{
            pesquisar(newValue);
        });

        this.colId.setCellValueFactory(new PropertyValueFactory<>("id_contrato"));
        this.colAtleta.setCellValueFactory(new PropertyValueFactory<>("id_atleta"));
        this.colClube.setCellValueFactory(new PropertyValueFactory<>("id_clube"));
        this.colInicio.setCellValueFactory(new PropertyValueFactory<>("data_inicio"));
        this.colTermino.setCellValueFactory(new PropertyValueFactory<>("data_termino"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo_contrato"));
        this.colStatus.setCellValueFactory(new PropertyValueFactory<>("status_contrato"));

        colStatus.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean ativo, boolean empty) {
                super.updateItem(ativo, empty);
                if (empty || ativo == null) {
                    setText(null);
                } else {
                    setText(ativo ? "Ativo" : "Não Ativo");
                }
            }
        });

        // Formatadores de data em pt-BR
        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        colInicio.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(formatarData(item, formatoBR, empty));
            }
        });

        colTermino.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(formatarData(item, formatoBR, empty));
            }
        });
        loadFromDatabase();
        }
    private String formatarData(String data, DateTimeFormatter formato, boolean empty) {
        if (empty || data == null || data.isEmpty()) return null;
        try {
            LocalDate d = LocalDate.parse(data); // Espera formato ISO do banco
            return d.format(formato);
        } catch (Exception e) {
            return data; // Se falhar a conversão, retorna como veio
        }
    }


    private void loadFromDatabase() {
        this.listaContratosAtletas = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from contratosatletas";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            ContratoAtletas ca=new ContratoAtletas(
                    rs.getInt("IdContratoAtleta"),
                    rs.getString("DataInicio"),
                    rs.getString("DataFim"),
                    rs.getString("TipoContrato"),
                    rs.getBoolean("ativo"),
                    rs.getInt("idClube"),
                    rs.getInt("idAtleta")
            );
            this.listaContratosAtletas.add(ca);
        }
            this.tableViewContratoAtletas.setItems(this.listaContratosAtletas);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisar(String filtro) {
        ObservableList<ContratoAtletas> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM contratosatletas WHERE " +
                "CAST(IdContratoAtleta AS CHAR) like ? or " +
                "DataInicio LIKE ? or " +
                "DataFim like ? or " +
                "TipoContrato like ? or "+
                "ativo like ? or "+
                "CAST(idClube AS CHAR) like ? or " +
                "CAST(idAtleta AS CHAR) like ?";

        try (Connection conn= Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i=1;i<=7;i++){
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                ContratoAtletas ca=new ContratoAtletas(
                        rs.getInt("IdContratoAtleta"),
                        rs.getString("DataInicio"),
                        rs.getString("DataFim"),
                        rs.getString("TipoContrato"),
                        rs.getBoolean("ativo"),
                        rs.getInt("idClube"),
                        rs.getInt("idAtleta")
                );
                resultados.add(ca);
            }
            this.tableViewContratoAtletas.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
