package org.cbf.cbf.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.cbf.cbf.model.entity.TabelaB_2024;
import org.cbf.cbf.model.utils.JanelaUtil;

import java.sql.*;

public class TabelaB_2024Controller {


    @FXML
    private TableView<TabelaB_2024> tabelaB2024;
    @FXML
    private TableColumn<TabelaB_2024, Integer> colIdClube;
    @FXML
    private TableColumn<TabelaB_2024, Integer> colVitorias;
    @FXML
    private TableColumn<TabelaB_2024, Integer> colEmpates;
    @FXML
    private TableColumn<TabelaB_2024, Integer> colDerrotas;
    @FXML
    private TableColumn<TabelaB_2024, Integer> colGolsPro;
    @FXML
    private TableColumn<TabelaB_2024, Integer> colGolsContra;
    @FXML
    private TableColumn<TabelaB_2024, Integer> colJogos;
    @FXML
    private TableColumn<TabelaB_2024, Integer> colPontos;
    @FXML
    private TableColumn<TabelaB_2024, Integer> colSaldoGols;


    private ObservableList<TabelaB_2024> listaTabelaB_2024;

    public void initialize() {

        this.colIdClube.setCellValueFactory(new PropertyValueFactory<>("id_clube_B24"));
        this.colVitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias_B24"));
        this.colEmpates.setCellValueFactory(new PropertyValueFactory<>("empates_B24"));
        this.colDerrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas_B24"));
        this.colGolsPro.setCellValueFactory(new PropertyValueFactory<>("golsPro_B24"));
        this.colGolsContra.setCellValueFactory(new PropertyValueFactory<>("golsContra_B24"));
        this.colJogos.setCellValueFactory(new PropertyValueFactory<>("jogos_B24"));
        this.colPontos.setCellValueFactory(new PropertyValueFactory<>("pontos_B24"));
        this.colSaldoGols.setCellValueFactory(new PropertyValueFactory<>("saldoGols_B24"));

        loadFromDatabase();
    }


    @FXML
    private void handleVoltarAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        JanelaUtil.trocarCenaComEstado(stage, "/fxml/Home.fxml");
    }

    private void loadFromDatabase() {
        this.listaTabelaB_2024 = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from brasileirao_serieb_2024";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            TabelaB_2024 b24=new TabelaB_2024(
                    rs.getInt("idClube"),
                    rs.getInt("vitorias"),
                    rs.getInt("empates"),
                    rs.getInt("derrotas"),
                    rs.getInt("golsPro"),
                    rs.getInt("golsSofridos"),
                    rs.getInt("jogos"),
                    rs.getInt("pontos"),
                    rs.getInt("saldoGols")
            );
            this.listaTabelaB_2024.add(b24);
        }
            this.tabelaB2024.setItems(this.listaTabelaB_2024);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}