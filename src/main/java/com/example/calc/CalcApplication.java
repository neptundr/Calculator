package com.example.calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalcApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalcApplication.class.getResource("calc-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 550, 600);
        scene.setOnKeyPressed(Controller::KeyPressed);

        stage.setTitle("Calculator");
        stage.setMinWidth(450);
        stage.setMinHeight(575);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}