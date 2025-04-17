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
import org.cbf.cbf.model.entity.TabelaA_2024;
import org.cbf.cbf.model.utils.JanelaUtil;
import org.cbf.cbf.repository.Conexao;

import java.sql.*;

public class TabelaA_2024Controller {


    @FXML
    private TableView<TabelaA_2024> tabelaA2024;
    @FXML
    private TableColumn<TabelaA_2024, Integer> colIdClube;
    @FXML
    private TableColumn<TabelaA_2024, Integer> colVitorias;
    @FXML
    private TableColumn<TabelaA_2024, Integer> colEmpates;
    @FXML
    private TableColumn<TabelaA_2024, Integer> colDerrotas;
    @FXML
    private TableColumn<TabelaA_2024, Integer> colGolsPro;
    @FXML
    private TableColumn<TabelaA_2024, Integer> colGolsContra;
    @FXML
    private TableColumn<TabelaA_2024, Integer> colJogos;
    @FXML
    private TableColumn<TabelaA_2024, Integer> colPontos;
    @FXML
    private TableColumn<TabelaA_2024, Integer> colSaldoGols;


    private ObservableList<TabelaA_2024> listaTabelaA_2024;

    public void initialize() {

        this.colIdClube.setCellValueFactory(new PropertyValueFactory<>("id_clube_A24"));
        this.colVitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias_A24"));
        this.colEmpates.setCellValueFactory(new PropertyValueFactory<>("empates_A24"));
        this.colDerrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas_A24"));
        this.colGolsPro.setCellValueFactory(new PropertyValueFactory<>("golsPro_A24"));
        this.colGolsContra.setCellValueFactory(new PropertyValueFactory<>("golsContra_A24"));
        this.colJogos.setCellValueFactory(new PropertyValueFactory<>("jogos_A24"));
        this.colPontos.setCellValueFactory(new PropertyValueFactory<>("pontos_A24"));
        this.colSaldoGols.setCellValueFactory(new PropertyValueFactory<>("saldoGols_A24"));

        loadFromDatabase();
    }


    @FXML
    private void handleVoltarAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        JanelaUtil.trocarCenaComEstado(stage, "/fxml/Home.fxml");
    }

    private void loadFromDatabase() {
        this.listaTabelaA_2024 = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from brasileirao_seriea_2024";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            TabelaA_2024 a24=new TabelaA_2024(
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
            this.listaTabelaA_2024.add(a24);
        }
            this.tabelaA2024.setItems(this.listaTabelaA_2024);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisar(String filtro) {
        ObservableList<TabelaA_2024> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM brasileirao_seriea_2024 WHERE " +
                "CAST(idClube AS CHAR) like ? or " +
                "vitorias LIKE ? or "+
                "empates LIKE ? or "+
                "derrotas LIKE ? or "+
                "golsPro LIKE ? or "+
                "golsSofridos LIKE ? or "+
                "jogos LIKE ? or "+
                "golsSofridos LIKE ? or "+
                "pontos LIKE ? or "+
                "CAST(saldoGols AS CHAR) like ?";

        try (Connection conn= Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i=1;i<=9;i++){
                stmt.setString(i, "%" + filtro + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                TabelaA_2024 a24=new TabelaA_2024(
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
                this.listaTabelaA_2024.add(a24);
            }
            this.tabelaA2024.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}