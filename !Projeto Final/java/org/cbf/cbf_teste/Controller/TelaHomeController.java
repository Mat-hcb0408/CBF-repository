package org.cbf.cbf_teste.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaHomeController {

    @FXML
    private Label transacoesLabel;

    @FXML
    private Label clubesAtletasLabel;

    @FXML
    private Label contratosLabel;

    @FXML
    private Label dividasDespesasLabel;

    @FXML
    private Label federacaoLabel;

    @FXML
    private Label receitaLabel;

    @FXML
    private Label proximosJogosLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Label placeholderLabel;

    @FXML
    public void initialize() {
        transacoesLabel.setOnMouseClicked(this::irParaTransacoes);
        clubesAtletasLabel.setOnMouseClicked(this::irParaClubesAtletas);
        // Adicione os outros conforme necessário
    }

    private void irParaTransacoes(MouseEvent event) {
        trocarCena("/org/cbf/cbf_teste/TelaTransacoes.fxml");
    }

    private void irParaClubesAtletas(MouseEvent event) {
        trocarCena("/org/cbf/cbf_teste/TelaClubesAtletas.fxml");
    }

    private void trocarCena(String caminhoFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFXML));
            Stage stage = (Stage) transacoesLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
