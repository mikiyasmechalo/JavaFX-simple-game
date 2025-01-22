package com.example.test1;

import com.example.test1.miniGames.TikTacToeView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartQuestController {

    public Button acceptButton;
    public Button exitButton;
    @FXML
    public AnchorPane GameViewAnchor;

    public void initialize(){
        acceptButton.setOnAction(e -> goToGameScreen());
        exitButton.setOnAction(e -> Platform.exit());
    }

    private void goToGameScreen() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameContainer.fxml")));
            Scene scene = acceptButton.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        try {
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameContainer.fxml")));
//            Stage stage = (Stage) acceptButton.getScene().getWindow();
//            acceptButton.getScene() = new Scene(root)
//            Scene scene = new Scene(root);
//            TikTacToeView t = new TikTacToeView(stage, GameViewAnchor);
//            stage.setScene(scene);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
