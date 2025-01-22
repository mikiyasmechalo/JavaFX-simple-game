package com.example.test1.miniGames;

import com.example.test1.GameController;
import javafx.event.ActionEvent;

public interface IDoorModel {
    void setParentController(GameController parentController);
    void goToNextDoor(ActionEvent e);
}

