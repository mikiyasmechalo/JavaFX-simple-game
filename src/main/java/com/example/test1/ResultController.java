package com.example.test1;
/*
src/
├── main/
│   ├── Main.java               // Entry point of the JavaFX application
│   ├── GameLoop.java           // Handles game logic
│   ├── controllers/            // Controller classes for FXML files
│   │   ├── MainController.java // Main menu or hub logic
│   │   ├── GameController.java // Game scene logic
│   │   └── ResultController.java // Game over or result screen logic
│   ├── models/                 // Game logic classes
│   │   ├── Player.java         // Player model
│   │   ├── MiniGameList.java   // Game list and mini-games
│   │   └── NarrativeManager.java // Narration logic
│   └── resources/              // FXML and UI resources
│       ├── main.fxml           // Main menu layout
│       ├── game.fxml           // Game layout
│       └── result.fxml         // Result layout
*/
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultController {

    @FXML
    private Label resultLabel;

    @FXML
    private Button playAgainButton;

    @FXML
    private Button exitButton;

    public void setMessage(String message) {
        resultLabel.setText(message);
    }

    public void initialize() {
        playAgainButton.setOnAction(event -> restartGame());
        exitButton.setOnAction(event -> Platform.exit());
    }

    private void restartGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/main.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) playAgainButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
