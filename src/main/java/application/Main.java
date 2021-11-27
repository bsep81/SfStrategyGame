package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.Objects;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {



        Pane mainPane = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/mainPane.fxml")));
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        }
}
