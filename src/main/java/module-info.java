module SfStrategyGame {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires static lombok;


    exports application to javafx.graphics;
    exports controller to javafx.fxml;
    opens controller to javafx.fxml;


}

