//package com.example.test1.models;
//import com.example.test1.GameController;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//public class DoorsController {
//
//    public void handleDoorClick(int doorId) {
//        Door door = GameManager.getInstance().getNarrativeManager().getDoorById(doorId);
//
//        if (door != null) {
//            navigateToMiniGame(door);
//        }
//    }
//
//    private void navigateToMiniGame(Door door) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/game.fxml"));
//            Parent root = loader.load();
//
//            GameController controller = loader.getController();
//            controller.initializeGame(door.getMiniGame(), door.getNarrative());
//
//            Stage stage = (Stage) /* any button */ door.getScene().getWindow();
//            stage.setScene(new Scene(root));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
////
////public class DoorsController {
////    private NarrativeManager narrativeManager = new NarrativeManager();
////
////    @FXML
////    private Button door1;
////
////    @FXML
////    private Button door2;
////
////    public void handleDoor1() {
////        handleDoorClick(1);
////    }
////
////    public void handleDoor2() {
////        handleDoorClick(2);
////    }
////
////    private void handleDoorClick(int doorId) {
////        Door door = narrativeManager.getDoorById(doorId);
////        if (door != null) {
////            navigateToMiniGame(door);
////        }
////    }
////
////    private void navigateToMiniGame(Door door) {
////        try {
////            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/game.fxml"));
////            Parent root = loader.load();
////
////            GameController gameController = loader.getController();
////            gameController.initializeGame(door.getMiniGame(), door.getNarrative());
////
////            Stage stage = (Stage) door1.getScene().getWindow(); // Use any button's scene
////            stage.setScene(new Scene(root));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////}
////
