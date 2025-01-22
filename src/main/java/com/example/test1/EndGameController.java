package com.example.test1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class EndGameController {

    public void playAgain(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage.fxml"));
        Parent root = loader.load();
        ((Button)e.getSource()).getScene().setRoot(root);
    }

    public void exit(ActionEvent e) {
        Platform.exit();
    }
}
