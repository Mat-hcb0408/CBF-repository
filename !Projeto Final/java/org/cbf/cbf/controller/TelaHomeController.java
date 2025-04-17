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
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
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
    private void irParaHome(ActionEvent event) {
        trocarCena("/org/cbf/cbf/TelaHome.fxml", event);
    }
}
