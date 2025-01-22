package com.example.test1.miniGames;

import com.example.test1.GameController;
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

import java.util.*;

public class Door1Controller implements IDoorModel {
    public GameController parentController;
    @FXML
    private ImageView hangmanImage;

    @FXML
    public VBox board1;

    @FXML
    public VBox board3;

    @FXML
    private VBox board2;

    @FXML
    private Label result;

    @FXML
    private Label hangmanProgress;

    @FXML
    private TextField charInput;

    @FXML
    public Button nextGameButton;

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

    @FXML
    void guessMade(ActionEvent event) {
        if(charInput.getText().strip().equalsIgnoreCase("")) return;

        boolean found = false;

        char pGuess = charInput.getText().toCharArray()[0];
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (checkLetter(i, pGuess)) {
                parentController.playerScored();
                result.setText("Correct Guess!");
                guesses.set(i, pGuess);
                updateProgress();
                found = true;
                break;
            }
        }

        if(!found){
            result.setText("Wrong!");
            updateHangMan();
            chances--;
            numWrongGuesses++;
        }
        if (chances < 1){
            resetGame();
            updateProgress();
        }
        checkFinished();
        charInput.clear();
    }
    private String wordToGuess;
    private final ArrayList<Character> guesses;
    private int numWrongGuesses;
    private final ArrayList<String> hangmanImageLinks;
    private int hangmanImageIndex;

    int chances = 10;


    public Door1Controller() {
        this.wordToGuess = randomWord();
        this.guesses = new ArrayList<>();
        for (int i = 0; i < wordToGuess.length(); i++) {
            guesses.add('_');
        }
        this.numWrongGuesses = 0;
        this.hangmanImageLinks = new ArrayList<>();

        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman1.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman2.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman3.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman4.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman5.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman6.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman7.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman8.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman9.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman10.jpg");
        hangmanImageLinks.add("file:///C:\\Users\\mikiy\\Documents\\VsCode\\Java\\Test1\\src\\main\\resources\\com\\example\\test1\\images\\hangman\\Hangman11.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman2.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman3.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman4.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman5.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman6.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman7.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman8.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman9.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman10.jpg");
//        hangmanImageLinks.add("images/hangman/Hangman11.jpg");
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman2.jpg")).toExternalForm());
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman3.jpg")).toExternalForm());
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman4.jpg")).toExternalForm());
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman5.jpg")).toExternalForm());
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman6.jpg")).toExternalForm());
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman7.jpg")).toExternalForm());
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman8.jpg")).toExternalForm());
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman9.jpg")).toExternalForm());
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman10.jpg")).toExternalForm());
//        hangmanImageLinks.add(Objects.requireNonNull(getClass().getResource("images/hangman/Hangman11.jpg")).toExternalForm());
    }

    private String randomWord() {
        String[] words = {"door", "tablet", "princess", "king", "solomon", "zarathor", "dragon", "talen", "Amulet"};
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index];
    }

    private boolean checkLetter(int index, char guessedLetter) {
        return (guesses.get(index) == '_') && (wordToGuess.charAt(index) == guessedLetter);
    }

    private void checkFinished() {
        boolean finished = true;
        for (char c : guesses) {
            if (c == '_') {
                finished = false;
                break;
            }
        }

        if (finished) {
            result.setFont(new Font("Arial", 16));
            result.setText("Congratulations! \n You found all the letters\n You made " + numWrongGuesses + " wrong guesses.");
            showNextGameButton();
            System.out.println("\nCongratulations! You found all the letters!");
            System.out.println("You made " + numWrongGuesses + " wrong guesses.");
        }
    }

    private void updateHangMan() {
        hangmanImage.setImage(new Image(hangmanImageLinks.get(hangmanImageIndex++)));
    }

    public void initialize(){
        board2.setVisible(false);
        board2.setManaged(false);
        board3.setVisible(false);
        board3.setManaged(false);

        nextGameButton.setVisible(false);
        nextGameButton.setManaged(false);

        updateProgress();
        updateHangMan();
    }

    private void updateProgress() {
        StringBuilder guess = new StringBuilder();
        for (Character character : guesses) {
            guess.append(character).append(" ");
        }
        hangmanProgress.setText(guess.toString());
    }
    private void resetGame() {
        parentController.damagePlayer(50);
        result.setText("Dead, Restarting with new word.");
        wordToGuess = randomWord();
        guesses.clear();
        for (int i = 0; i < wordToGuess.length(); i++)
            guesses.add('_');

        numWrongGuesses = 0;
        chances = 10;
        hangmanImageIndex = 0;
        updateHangMan();

    }


    void showNextGameButton(){
        nextGameButton.setVisible(true);
        nextGameButton.setManaged(true);
    }


    @Override
    public void setParentController(GameController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void goToNextDoor(ActionEvent e) {
        parentController.toNextGame();
    }


//    public void slotPressed(ActionEvent e){
//
//        if(t==0) {
//            t++;
//            init();
//        }
//        if(((Button)e.getSource()).getText().toCharArray()[0] != 'X' && ((Button)e.getSource()).getText().toCharArray()[0] != 'O' && playerTurn & !boardFull(board)){
//            System.out.println("Players Turn!");
//            System.out.println(Integer.parseInt(((Button)e.getSource()).getText()));
//            int pressed = Integer.parseInt(((Button)e.getSource()).getText());
//            if (pressed < 10) {
//                slotPressed1(e);
//                System.out.println(pressed + "pressed");
//                board[pressed-1] = 'X';
//
//            }
//        }
//
//        if(!playerTurn){
//            System.out.println("Computers Turn!");
//            int c = computerChoice(board, 'O', 'X');
//            System.out.println("c in char : " + (char)(c + '0'));
//            buttonArray.get(c+1).setText("O");
//            board[c] = 'O';
//            playerTurn = true;
//        }
//        if (chooseWinner(board) == 1 || chooseWinner(board) == 0) {
//            System.out.println("Winner is " + chooseWinner(board));
////            if(score == 3){
////                goToNextDoor();
////            }
//            restartGame();
//            return;
//        }
//        for(char i: board)
//            System.out.println(i);
//
//        if (remainingMoves(board) == 1) {
//            int winningMove = findWinningMove(board);
//            if (winningMove == -1) {
//                System.out.println("No winning move available. Skipping...");
//                restartGame();
//            }
//        }
//    }

}
