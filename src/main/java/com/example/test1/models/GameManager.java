package com.example.test1.models;

import com.example.test1.miniGames.IDoorModel;

import java.util.List;

public class GameManager {
    private static GameManager instance;
    private Player player;
    private List<IDoorModel> miniGames;
    private int currentDoorIndex;

    private GameManager() {
        player = new Player("Player 1");
//        miniGames = NarrativeManager.loadMiniGames(); // Load all mini-games
        currentDoorIndex = 0; // Start at the first door
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public IDoorModel getCurrentMiniGame() {
        return miniGames.get(currentDoorIndex);
    }

    public String getCurrentNarrative() {
        return" NarrativeManager.getNarrativeForDoor(currentDoorIndex);";
    }

    public boolean progressToNextDoor() {
        currentDoorIndex++;
        return currentDoorIndex < miniGames.size();
    }
}
