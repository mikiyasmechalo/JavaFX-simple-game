package com.example.test1;

import com.example.test1.miniGames.IDoorModel;
import com.example.test1.models.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {
    @FXML
    public AnchorPane GameViewAnchor;

    @FXML
    private Label scoreLabel;
    @FXML
    private Label playerNameLabel;

    @FXML
    private Label healthLabel;

    Player player = new Player("Player 1");
    private int score;
    private final ArrayList<String>gameListFxmlPaths;

    public GameController(){
        gameListFxmlPaths = new ArrayList<>();
        gameListFxmlPaths.add("Door1.fxml");
        gameListFxmlPaths.add("Door2.fxml");
        gameListFxmlPaths.add("Door3.fxml");
        gameListFxmlPaths.add("Door4.fxml");
        gameListFxmlPaths.add("Door5.fxml");
    }
    private int gameIndex;

    @FXML
    public void initialize(){
        playerNameLabel.setText(player.getName());
        updateUI();
        toNextGame();
    }

    public void toNextGame(){
        if (gameIndex < gameListFxmlPaths.size()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(gameListFxmlPaths.get(gameIndex++)));
            Parent miniGameUI;
            try {
                miniGameUI = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            IDoorModel miniGameController = loader.getController();
            miniGameController.setParentController(this);

            GameViewAnchor.getChildren().clear();
            GameViewAnchor.getChildren().add(miniGameUI);
        }
        else
            endGame(true);
    }

    public void updateUI(){
        healthLabel.setText(String.valueOf(player.getHealth()));
        scoreLabel.setText(String.valueOf(score));
    }

    public void endGame(boolean gameWon){
        String endScreenFxmlPath;
        if(gameWon) {
            endScreenFxmlPath = "gameWonView.fxml";
        }else{
            endScreenFxmlPath = "gameOverView.fxml";
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(endScreenFxmlPath));
        Parent endGameUI;
        try {
            endGameUI = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = GameViewAnchor.getScene();
        scene.setRoot(endGameUI);
    }

    public void playerScored(){
        score +=10;
        updateUI();
    }

    public void damagePlayer(int damage) {
        player.dealDamage(damage);
        updateUI();
        checkEndGame();
    }

    private void checkEndGame() {
        if(player.getHealth() <= 0) {
            endGame(false);
        }
    }

//    public void initializeGame(IMiniGame miniGame, String narrative) {
//        this.currentMiniGame = miniGame;
//
//        // Bind player state to UI
//        Player player = GameManager.getInstance().getPlayer();
//        playerNameLabel.setText(player.getName());
//        updatePlayerHealthUI(player);
//
//        // Set narration
//        narrationLabel.setText(narrative + "\n" + miniGame.getInstructions());
//    }
//
//    public void playMiniGame() {
//        boolean result = currentMiniGame.play();
//        Player player = GameManager.getInstance().getPlayer();
//
//        if (result) {
//            player.addScore(10); // Reward points
//            narrationLabel.setText("You won the mini-game!");
//        } else {
//            player.dealDamage(10); // Penalize health
//            updatePlayerHealthUI(player);
//
//            if (player.getHealth() <= 0) {
//                narrationLabel.setText("You lost the game! Health is 0.");
//            } else {
//                narrationLabel.setText("You lost the mini-game. Try again?");
//            }
//        }
//    }
//
//    private void updatePlayerHealthUI(Player player) {
//        healthLabel.setText("" + player.getHealth());
////        playerHealthBar.setProgress(player.getHealth() / 100.0);
//    }
}
//public class GameController {
//    @FXML
//    private Label narrativeLabel;
//    @FXML
//    private Button playMiniGameButton;
//    @FXML
//    private AnchorPane miniGameContainer;
//    @FXML
//    private ProgressBar healthBar;
//    @FXML
//    private Label scoreLabel;
//
//    private GameManager gameManager;
//    private MiniGame currentMiniGame;
//
//    public void initialize() {
//        gameManager = GameManager.getInstance();
//        loadCurrentDoor();
//    }
//
//    private void loadCurrentDoor() {
//        // Load the door narrative
//        narrativeLabel.setText(gameManager.getCurrentNarrative());
//        currentMiniGame = gameManager.getCurrentMiniGame();
//
//        // Update player stats
//        Player player = gameManager.getPlayer();
//        healthBar.setProgress(player.getHealth() / 100.0);
//        scoreLabel.setText("Score: " + player.getScore());
//
//        // Ensure mini-game UI is hidden
//        miniGameContainer.getChildren().clear();
//    }
//
//    @FXML
//    private void handlePlayMiniGame() {
//        // Load and display the mini-game UI
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(currentMiniGame.getFxmlPath()));
//            Parent miniGameUI = loader.load();
//
//            // Pass the mini-game logic to its controller
//            MiniGameController miniGameController = loader.getController();
//            miniGameController.initializeGame(currentMiniGame);
//
//            // Display mini-game in the container
//            miniGameContainer.getChildren().clear();
//            miniGameContainer.getChildren().add(miniGameUI);
//            AnchorPane.setTopAnchor(miniGameUI, 0.0);
//            AnchorPane.setBottomAnchor(miniGameUI, 0.0);
//            AnchorPane.setLeftAnchor(miniGameUI, 0.0);
//            AnchorPane.setRightAnchor(miniGameUI, 0.0);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void handleMiniGameComplete(boolean success) {
//        // Update player stats
//        Player player = gameManager.getPlayer();
//        if (success) {
//            player.addScore(50); // Example: Add points for winning
//        } else {
//            player.dealDamage(20); // Example: Lose health on failure
//        }
//
//        // Check if there are more doors
//        if (gameManager.progressToNextDoor()) {
//            loadCurrentDoor();
//        } else {
//            navigateToEndScreen();
//        }
//    }
//
//    private void navigateToEndScreen() {
//        // Transition to the end-game screen
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/endgame.fxml"));
//            Parent root = loader.load();
//
//            EndGameController endGameController = loader.getController();
//            endGameController.initializeResults(gameManager.getPlayer());
//
//            Stage stage = (Stage) narrativeLabel.getScene().getWindow();
//            stage.setScene(new Scene(root));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}


