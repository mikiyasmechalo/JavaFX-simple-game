module com.example.test1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.test1 to javafx.fxml;
    exports com.example.test1;
    exports com.example.test1.miniGames;
    opens com.example.test1.miniGames to javafx.fxml;
}