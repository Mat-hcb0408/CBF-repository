package org.example.tabela;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Time;

public class HelloController {

        @FXML private TableView<?> tabelaClassificacao;
        @FXML private TableColumn<?, ?> colPosicao;
        @FXML private TableColumn<?, ?> colTime;
        @FXML private TableColumn<?, ?> colPontos;
        @FXML private TableColumn<?, ?> colJogos;
        @FXML private TableColumn<?, ?> colVitorias;
        @FXML private TableColumn<?, ?> colEmpates;
        @FXML private TableColumn<?, ?> colDerrotas;
        @FXML private TableColumn<?, ?> colGolsPro;
        @FXML private TableColumn<?, ?> colGolsContra;
        @FXML private TableColumn<?, ?> colSaldoGols;
        @FXML private TableColumn<?, ?> colAproveitamento;

        @FXML
        public void initialize() {
            configureTable();
        }

        private void configureTable() {
            tabelaClassificacao.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }

        @FXML
        private void handleAtualizar() {
            // Empty refresh method
            tabelaClassificacao.refresh();
        }
    }