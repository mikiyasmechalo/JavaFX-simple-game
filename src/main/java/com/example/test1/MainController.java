package com.example.test1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController {

    @FXML
    private Button startButton;

    @FXML
    private Button quitButton;

    public void initialize() {
        startButton.setOnAction(event -> switchToGameScreen());
        quitButton.setOnAction(event -> Platform.exit());
    }

    private void switchToGameScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
            Parent root = loader.load();
            Scene scene = startButton.getScene();

            scene.setRoot(root);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

