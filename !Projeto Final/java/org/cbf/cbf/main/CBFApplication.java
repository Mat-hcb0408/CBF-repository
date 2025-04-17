package org.cbf.cbf.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CBFApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CBFApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        /*
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
         */
        stage.setTitle("Gerenciamento de Dividas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}