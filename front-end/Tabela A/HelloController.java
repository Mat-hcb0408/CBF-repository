package org.example.tabela;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Time;

public class HelloController {

        @FXML private TableView<Time> tabelaClassificacao;
        @FXML private TableColumn<Time, Integer> colPosicao;
        @FXML private TableColumn<Time, String> colEscudo;
        @FXML private TableColumn<Time, String> colTime;
        // ... other columns

        @FXML
        public void initialize() {
            configureColumns();
            // loadData() would be implemented to fetch from CBF API
        }

        private void configureColumns() {
            colPosicao.setCellValueFactory(new PropertyValueFactory<>("posicao"));
            colEscudo.setCellValueFactory(new PropertyValueFactory<>("escudo"));
            colTime.setCellValueFactory(new PropertyValueFactory<>("nome"));
            // ... configure other columns
        }

        @FXML
        private void handleAtualizar() {
            // Implementation to refresh data from CBF
        }
    }