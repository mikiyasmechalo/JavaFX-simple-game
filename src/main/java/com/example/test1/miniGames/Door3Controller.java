package com.example.test1.miniGames;

import com.example.test1.GameController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.Collections;

public class Door3Controller implements IDoorModel {

    public VBox board3;
    public VBox board2;
    public Button nextGameButton;
    public VBox board1;

    private GameController parentController;
    String wordToGuess;
//
//    @FXML
//    private Button enterGuessBtn;
    @FXML
    private Label wordToGuessLabel;

    @FXML
    private TextField guessInput;

    @FXML
    private Label resultLabel;
    private int chances = 3;

    public void initialize(){
        wordToGuess = words[(int) (Math.random() * words.length)];
        String scrambledWord = scrambleWord(wordToGuess);
        wordToGuessLabel.setText(scrambledWord);
        board2.setVisible(false);
        board2.setManaged(false);
        board3.setVisible(false);
        board3.setManaged(false);
        nextGameButton.setVisible(false);
        nextGameButton.setManaged(false);
    }

    public void handleGuess() {
        if(guessInput.getText().equalsIgnoreCase(wordToGuess)){
            resultLabel.setTextAlignment(TextAlignment.RIGHT);
            resultLabel.setText("Correct!");
            parentController.playerScored();
            endGame();
        }else if (chances >= 1){
            resultLabel.setTextAlignment(TextAlignment.CENTER);
            resultLabel.setText("Incorrect! \n You have " + chances-- + " chances left!");
        }else {
            resultLabel.setText("You have 0 chances left!\n Here is another word.");
            parentController.damagePlayer(10);
            restartGame();
        }
    }
    public void takeGuess(Event event) {
        if (event instanceof KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                System.out.println("Enter key was pressed!");
                handleGuess();
            }
        } else if (event instanceof MouseEvent mouseEvent) {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                System.out.println("Mouse button was clicked!");
                handleGuess();
            }
        }
    }


    private void restartGame() {
        initialize();
        chances = 3;
    }

    private void endGame() {
        parentController.toNextGame();
    }

    private final String[] words = {"adventure", "dragon", "mystical", "flower", "puzzle", "talen", "wisdom"};
    //TODO: Maybe a hint system

    private String scrambleWord(String word) {
        String[] letters = word.split("");
        Collections.shuffle(Arrays.asList(letters));
        return String.join("", letters);
    }

    public void gameRules() {
        System.out.println();
        System.out.println("Game Rules:");
        System.out.println("1. You will be given a scrambled word.");
        System.out.println("2. Unscramble it correctly to pass.");
        System.out.println("3. You have 5 chances.");
        System.out.println("3. Pay attention to spelling!");
    }

    @Override
    public void setParentController(GameController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void hideBoard1(MouseEvent event) {
        board1.setVisible(false);
        board1.setManaged(false); // Remove it from layout calculations
        board2.setManaged(true);
        board2.setVisible(true);
    }

    @FXML
    void hideBoard2(MouseEvent event) {
        board2.setVisible(false);  // Hide the node visually
        board2.setManaged(false); // Remove it from layout calculations
        board3.setVisible(true);
        board3.setManaged(true);
    }

    public void goToNextDoor(ActionEvent actionEvent) {
        parentController.toNextGame();
    }
}
