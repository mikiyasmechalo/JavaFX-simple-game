package com.example.test1.models;

import com.example.test1.miniGames.IDoorModel;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class Door {
    private final int id;
    private final String narrative;
    private final IDoorModel miniGame;

    public Door(int id, String narrative, IDoorModel miniGame) {
        this.id = id;
        this.narrative = narrative;
        this.miniGame = miniGame;
    }

    public int getId() {
        return id;
    }

    public String getNarrative() {
        return narrative;
    }

    public IDoorModel getMiniGame() {
        return miniGame;
    }

    public Scene getScene() {
        return new Scene(new VBox());
    }
}

