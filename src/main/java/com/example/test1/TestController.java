package com.example.test1;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.*;

public class TestController {

    public Label gameResult;
    public AnchorPane gameBoard;

    @FXML
    private AnchorPane board1;

    private int score;

    private final Map<Integer, Button> buttonArray = new HashMap<>();
    ;
    private final char[] board = new char[9];
    int t = 0;
    @FXML
    public Button slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9;

    @FXML
    public void initialize() {
        board2.setVisible(false);
        board2.setManaged(false);
        board3.setVisible(false);
        board3.setManaged(false);
        board4.setManaged(false);
        board4.setVisible(false);
        potionDrankBoard.setVisible(false);
        potionDrankBoard.setManaged(false);
        gameBoard.setVisible(false);
        gameBoard.setManaged(false);

        buttonArray.put(1, slot1);
        buttonArray.put(2, slot2);
        buttonArray.put(3, slot3);
        buttonArray.put(4, slot4);
        buttonArray.put(5, slot5);
        buttonArray.put(6, slot6);
        buttonArray.put(7, slot7);
        buttonArray.put(8, slot8);
        buttonArray.put(9, slot9);
        Arrays.fill(board, ' ');
        boards = new ArrayList<>();
        boards.add(board1);
        boards.add(board2);
        boards.add(board3);
        boards.add(potionDrankBoard);
        boards.add(board4);
        boards.add(gameBoard);
    }

    @FXML
    private TextArea potionDrankResult;


    @FXML
    private AnchorPane board2;


    @FXML
    private AnchorPane board3;

    @FXML
    private AnchorPane board4;

    @FXML
    private ImageView potionDrankImage;

    @FXML
    private AnchorPane potionDrankBoard;

    private ArrayList<AnchorPane> boards;


    @FXML
    void nextStory(ActionEvent event) {
        for (int i = 0; i < boards.size(); i++) {
            if (boards.get(i).isManaged()){
                int j=i+1;
                if (boards.get(i) == board3){
                    j++;
                }
                switchVisibility(boards.get(i));
                switchVisibility(boards.get(j));
                break;
            }
        }
    }
    void switchVisibility(AnchorPane b){
        boolean isVisible = b.isManaged();
        b.setManaged(!isVisible);
        b.setVisible(!isVisible);
    }

    @FXML
    void potionDrank(ActionEvent event) {
        Random r = new Random();
        boolean chance = r.nextBoolean();
        switchVisibility(potionDrankBoard);
        switchVisibility(board3);
        if (!chance) {
            potionDrankImage.setImage(new Image(getClass().getResource("images/potion3.jpg").toString()));
            potionDrankResult.setText("The potion is a poison potion and you lie ill for minuits!");
//            parentController.dealDamage(30);
        } else {
            System.out.println("The potion is a healing potion and it gives you energy!");
            potionDrankResult.setText("The potion is a healing potion and it gives you energy!");
//                player.takeHealing(30);
        }
    }


    public void slotPressed(ActionEvent e) {

        Button pressedButton = (Button) e.getSource();
        int index = getIndexFromButton(pressedButton);

        if (board[index] == ' ' && boardNotFull(board)) {
            System.out.println("Player's Turn!");
            board[index] = 'X'; // Update the board array
            pressedButton.setText("X"); // Update the button's text

            if (chooseWinner(board) == 1) {
                System.out.println("You win!");
                gameResult.setText("You win!");
                if (score == 3) {
                    endGame();
                }
                score++;

                restartGame();
                return;
            }

            // Computer's move
            int c = computerChoice(board, 'O', 'X');
            board[c] = 'O';
            if (boardNotFull(board))
                buttonArray.get(c + 1).setText("O");

            if (chooseWinner(board) == 0) {
                System.out.println("Computer wins!");
                gameResult.setText("Computer wins!");
                updateResultWithPause();
                restartGame();
            }
        }

        if (remainingMoves(board) == 1) {
            int winningMove = findWinningMove(board);
            if (winningMove == -1) {
                gameResult.setText("No winning move available. Skipping...");
                System.out.println("No winning move available. Skipping...");
                restartGame();
            }
        }
    }

    private void endGame() {

    }

    private void updateResultWithPause() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            gameResult.setText("");
        });
        pause.play();
    }


//    public void goToNextDoor(ActionEvent e) {
//        parentController.toNextGame();
//    }

    private void restartGame() {
        Arrays.fill(board, ' ');
        for (var i : buttonArray.entrySet()) {
            i.getValue().setText(" ");
        }
    }

    public int computerChoice(char[] board, char computer, char player) {
        Random random = new Random();
        int useStrategy = random.nextInt(9);

        if (useStrategy < 7) {
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = computer;
                    if (chooseWinner(board) == 1) {
                        board[i] = ' ';
                        return i;
                    }
                    board[i] = ' ';
                }
            }

            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = player;
                    if (chooseWinner(board) == 1) {
                        board[i] = ' ';
                        return i;
                    }
                    board[i] = ' ';
                }
            }

            if (board[4] == ' ') {
                return 4;
            }

            int[] corners = {0, 2, 6, 8};
            for (int corner : corners) {
                if (board[corner] == ' ') {
                    return corner;
                }
            }

            int[] edges = {1, 3, 5, 7};
            for (int edge : edges) {
                if (board[edge] == ' ') {
                    return edge;
                }
            }

            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    return i;
                }
            }
        }
        while (true) {
            int randomIndex = random.nextInt(9);
            if (board[randomIndex] == ' ') {
                return randomIndex;
            }
        }
    }

    private int chooseWinner(char[] n) {
        int[][] winningConditions = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] condition : winningConditions) {
            if (n[condition[0]] == 'X' && n[condition[1]] == 'X' && n[condition[2]] == 'X') {
                return 1;
            }
            if (n[condition[0]] == 'O' && n[condition[1]] == 'O' && n[condition[2]] == 'O') {
                return 0;
            }
        }
        return -1;
    }

    private int remainingMoves(char[] board) {
        int count = 0;
        for (char c : board) {
            if (c == ' ') count++;
        }
        return count;
    }

    private int findWinningMove(char[] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = 'X';
                if (chooseWinner(board) == 1) {
                    board[i] = ' ';
                    return i;
                }
                board[i] = ' ';
            }
        }
        return -1;
    }

    private boolean boardNotFull(char[] n) {
        for (char c : n) {
            if (c == ' ') return true;
        }
        return false;
    }

    private int getIndexFromButton(Button button) {
        for (Map.Entry<Integer, Button> entry : buttonArray.entrySet()) {
            if (entry.getValue() == button) {
                return entry.getKey() - 1; // Map button to board index (1-based to 0-based)
            }
        }
        throw new IllegalArgumentException("Button not found in buttonArray");
    }


//    @Override
//    public void setParentController(GameController parentController) {
//        this.parentController = parentController;
//    }


}