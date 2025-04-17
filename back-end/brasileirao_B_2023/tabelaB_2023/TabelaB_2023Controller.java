package com.cbf1.cbf1.tabelaB_2023;

import com.cbf1.cbf1.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class TabelaB_2023Controller {


    @FXML
    private TableView<TabelaB_2023> tabelaB2023;
    @FXML
    private TableColumn<TabelaB_2023, Integer> colIdClube;
    @FXML
    private TableColumn<TabelaB_2023, Integer> colVitorias;
    @FXML
    private TableColumn<TabelaB_2023, Integer> colEmpates;
    @FXML
    private TableColumn<TabelaB_2023, Integer> colDerrotas;
    @FXML
    private TableColumn<TabelaB_2023, Integer> colGolsPro;
    @FXML
    private TableColumn<TabelaB_2023, Integer> colGolsContra;
    @FXML
    private TableColumn<TabelaB_2023, Integer> colJogos;
    @FXML
    private TableColumn<TabelaB_2023, Integer> colPontos;
    @FXML
    private TableColumn<TabelaB_2023, Integer> colSaldoGols;


    private ObservableList<TabelaB_2023> listaTabelaB_2023;

    public void initialize() {

        this.colIdClube.setCellValueFactory(new PropertyValueFactory<>("id_clube_B23"));
        this.colVitorias.setCellValueFactory(new PropertyValueFactory<>("vitorias_B23"));
        this.colEmpates.setCellValueFactory(new PropertyValueFactory<>("empates_B23"));
        this.colDerrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas_B23"));
        this.colGolsPro.setCellValueFactory(new PropertyValueFactory<>("golsPro_B23"));
        this.colGolsContra.setCellValueFactory(new PropertyValueFactory<>("golsContra_B23"));
        this.colJogos.setCellValueFactory(new PropertyValueFactory<>("jogos_B23"));
        this.colPontos.setCellValueFactory(new PropertyValueFactory<>("pontos_B23"));
        this.colSaldoGols.setCellValueFactory(new PropertyValueFactory<>("saldoGols_B23"));

        loadFromDatabase();
    }

    private void loadFromDatabase() {
        this.listaTabelaB_2023 = FXCollections.observableArrayList();
        String url="jdbc:mysql://localhost:3306/tarde_bd_cbf_teste";
        String user="root";
        String password=null;

        String query="select * from brasileirao_serieb_2023";

        try (
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
        ){while (rs.next()){
            TabelaB_2023 b23=new TabelaB_2023(
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
            this.listaTabelaB_2023.add(b23);
        }
            this.tabelaB2023.setItems(this.listaTabelaB_2023);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}