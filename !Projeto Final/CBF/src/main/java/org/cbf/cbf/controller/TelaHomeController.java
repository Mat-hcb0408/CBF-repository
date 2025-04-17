package org.cbf.cbf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaHomeController {

    private void trocarCena(String caminhoFXML, ActionEvent event) {
        try {
            // Carregar a nova cena (FXML)
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFXML));

            // Criar a nova cena e aplicar o CSS
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());  // Aplica o CSS

            // Obter o stage atual e configurar a nova cena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);  // Troca a cena
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irParaTransacoes(ActionEvent event) {
        trocarCena("/fxml/TelaTransacao.fxml", event);
    }

    @FXML
    private void irParaClubesAtletas(ActionEvent event) {
        trocarCena("/fxml/TelaClubes.fxml", event);
    }

    @FXML
    private void irParaContratos(ActionEvent event) {
        trocarCena("/fxml/TelaContratoAtletas.fxml", event);
    }

    @FXML
    private void irParaDividas(ActionEvent event) {
        trocarCena("/fxml/TelaDividas.fxml", event);
    }

    @FXML
    private void irParaFederacao(ActionEvent event) {
        trocarCena("/fxml/TelaFederacao.fxml", event);
    }

    @FXML
    private void irParaReceita(ActionEvent event) {
        trocarCena("/fxml/TelaReceitas.fxml", event);
    }

    @FXML
    private void irParaBrasileiraoSerieA2024(ActionEvent event) {
        trocarCena("/fxml/TelaTabelaA_2024.fxml", event);
    }

    @FXML
    private void irParaBrasileiraoSerieA2023(ActionEvent event) {
        trocarCena("/fxml/TelaTabelaA_2023.fxml", event);
    }

    @FXML
    private void irParaBrasileiraoSerieB2024(ActionEvent event) {
        trocarCena("/fxml/TelaTabelaB_2024.fxml", event);
    }

    @FXML
    private void irParaBrasileiraoSerieB2023(ActionEvent event) {
        trocarCena("/fxml/TelaTabelaB_2023.fxml", event);
    }
}
