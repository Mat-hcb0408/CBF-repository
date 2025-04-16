package com.cbf1.cbf1.federacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class CadastroFederacoesApplication {
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cbf1/cbf1/federacao/cadastrofederacoes-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle("Cadastro de Federação");
            stage.setScene(scene);
            stage.show();
        }
        public static void main(String[] args) {
            launch();
        }
    }

