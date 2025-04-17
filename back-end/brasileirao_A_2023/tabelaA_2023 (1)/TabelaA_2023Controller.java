package com.cbf1.cbf1.tabelaA_2023;

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

public class TabelaA_2023Controller {


    @FXML
    private TableView<TabelaA_2023> tabelaA2023;
    @FXML
    private TableColumn<TabelaA_2023, Integer> colIdClube;
    @FXML
    private TableColumn<TabelaA_2023, Integer> colVitorias;
    @FXML
    private TableColumn<TabelaA_2023, Integer> colEmpates;
    @FXML
    private TableColumn<TabelaA_2023, Integer> colDerrotas;
    @FXML
    private TableColumn<TabelaA_2023, Integer> colGolsPro;
    @FXML
    private TableColumn<TabelaA_2023, Integer> colGolsContra;
    @FXML
    private TableColumn<TabelaA_2023, Integer> colJogos;
    @FXML
    private TableColumn<TabelaA_2023, Integer> colPontos;
    @FXML
    private TableColumn<TabelaA_2023, Integer> colSaldoGols;


    private ObservableList<TabelaA_2023> listaTabelaA_2023;

    public void initialize() {

        this.colIdClube.setCellValueFactory(new PropertyValueFactory<>("id_clube_A23"));
        this.colVitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias_A23"));
        this.colEmpates.setCellValueFactory(new PropertyValueFactory<>("empates_A24"));
        this.colDerrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas_A23"));
        this.colGolsPro.setCellValueFactory(new PropertyValueFactory<>("golsPro_A23"));
        this.colGolsContra.setCellValueFactory(new PropertyValueFactory<>("golsContra_A23"));
        this.colJogos.setCellValueFactory(new PropertyValueFactory<>("jogos_A23"));
        this.colPontos.setCellValueFactory(new PropertyValueFactory<>("pontos_A23"));
        this.colSaldoGols.setCellValueFactory(new PropertyValueFactory<>("saldoGols_A23"));

        loadFromDatabase();
    }

    private void loadFromDatabase() {
        this.listaTabelaA_2023 = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from brasileirao_seriea_2023";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            TabelaA_2023 a23=new TabelaA_2023(
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
            this.listaTabelaA_2023.add(a23);
        }
            this.tabelaA2023.setItems(this.listaTabelaA_2023);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void pesquisar(String filtro) {
        ObservableList<TabelaA_2023> resultados = FXCollections.observableArrayList();

        String query="SELECT * FROM brasileirao_seriea_2023 WHERE " +
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
                TabelaA_2023 a23=new TabelaA_2023(
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
                this.listaTabelaA_2023.add(a23);
            }
            this.tabelaA2023.setItems(resultados);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}