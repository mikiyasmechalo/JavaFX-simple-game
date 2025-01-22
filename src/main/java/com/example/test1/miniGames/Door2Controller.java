package com.example.test1.miniGames;


import com.example.test1.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Door2Controller implements IDoorModel {

    public Label playerScoreLabel;
    public Label computerScoreLabel;
    @FXML
    private VBox board2;

    @FXML
    private VBox board3;


    @FXML
    private VBox board1;

    @FXML
    private Button nextGameButton;

    private GameController parentController;

    @FXML
    public void initialize(){
        board2.setVisible(false);
        board2.setManaged(false);
        board3.setVisible(false);
        board3.setManaged(false);
        nextGameButton.setVisible(false);
        nextGameButton.setManaged(false);
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
    }

    @Override
    public void setParentController(GameController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private Label winnerLabel;

    // game Logic
    private int playerScore = 0;
    private int computerScore = 0;


    @FXML
    void choicePressed(ActionEvent event) {
        char computerChoice;
        char userChoice = ' ';

        computerChoice = getComputerChoice();
        userChoice = getUserChoice(event, userChoice);

        System.out.println("Computer chose: " + computerChoice);

        boolean playerWinner = chooseWinner(userChoice, computerChoice);
        if (playerWinner) {
            winnerLabel.setText("Winner: Player");
            playerScore++;
            parentController.playerScored();
        }
        else {
            winnerLabel.setText("Winner: Computer");
            computerScore++;
        }

        updateScores(playerWinner);

        if (playerScore == 3 || computerScore ==3){
            if(playerScore == 3) toNextGame();
            else restartGame();
        }
    }

    private void updateScores(boolean playerWinner) {
        if(!playerWinner){
            parentController.damagePlayer(10);
        }
        playerScoreLabel.setText(String.valueOf(playerScore));
        computerScoreLabel.setText(String.valueOf(computerScore));
    }

    public void toNextGame() {
        parentController.toNextGame();
    }

    private void restartGame() {
        playerScore = 0;
        computerScore = 0;
    }

    private static char getUserChoice(ActionEvent event, char userChoice) {
        switch (((Button) event.getSource()).getText().toLowerCase()){
            case "rock" -> {
                userChoice = 'r';
            }
            case "paper" -> {
                userChoice = 'p';
            }
            case "scissor" -> {
                userChoice = 's';
            }
        }
        return userChoice;
    }

    private char getComputerChoice() {
        char computerChoice;
        int randomNum = (int) (Math.random() * 3);
        if (randomNum == 0) {
            computerChoice = 'r';
        } else if (randomNum == 1) {
            computerChoice = 'p';
        } else {
            computerChoice = 's';
        }
        return computerChoice;
    }

    private boolean chooseWinner(char userChoice, char computerChoice){

        if (userChoice == 'r') {
            if (computerChoice == 's') {
                System.out.println("You win!");
                return true;
            } else {
                System.out.println("The Shadow wins!");
                return false;
            }
        } else if (userChoice == 'p') {
            if (computerChoice == 'r') {
                System.out.println("You win!");
                return true;
            } else {
                System.out.println("The Shadow wins!");
                return false;
            }
        } else if (userChoice== 's') {
            if (computerChoice == 'p') {
                System.out.println("You win!");
                return true;
            } else {
                System.out.println("The Shadow wins!");
                return false;
            }
        }

        return false;
    }


}
