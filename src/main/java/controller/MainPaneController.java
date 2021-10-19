package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Game;
import model.spaceShips.SpaceShip;

import java.util.List;

public class MainPaneController {

    @FXML
    private Label metalLabel;

    @FXML
    private Label alloysLabel;

    @FXML
    private Button nextTurnButton;

    private Game game = Game.getInstance();


    @FXML
    void initialize(){
        nextTurnButton.setOnAction(event -> nextTurn());
        initializeOthers();
    }


    private void initializeOthers() {
        alloysLabel.textProperty().bind(game.getColony().getAlloysProperty());
        metalLabel.textProperty().bind(game.getColony().getMetalProperty());
    }

    private void nextTurn() {
        game.setTurn(game.getTurn() + 1 );
        game.getTurnProperty().set(game.getTurn());
        game.getColony().produceResources();
        List<SpaceShip> producedSpaceShips = game.getColony().getShipyard().manufacture();
        if (!producedSpaceShips.isEmpty()) {
            game.getColony().getSpaceShips().addAll(producedSpaceShips);
        }
    }
}
