package org.example.tabela;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

        @Override
        public void start(Stage stage) throws Exception {
            // Carrega o FXML usando o caminho absoluto dentro dos resources
            URL fxmlUrl = getClass().getResource("/org/example/tabela/hello-view.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            Parent root = fxmlLoader.load();

            stage.setTitle("Brasileirão 2023");
            stage.setScene(new Scene(root, 1000, 650));
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
