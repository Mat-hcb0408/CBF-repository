package org.example.projeto1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

        @Override
        public void start(Stage stage) throws Exception {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720); // define tamanho padrão que bate com o FXML
            stage.setTitle("Sistema de Transações da CBF"); // título mais descritivo
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }
