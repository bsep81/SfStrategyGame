package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.Game;
import model.combat.Battle;
import model.combat.BattleCreator;
import model.combat.Fleet;
import model.spaceShips.SpaceShip;

import java.util.List;

public class MainPaneController {

    @FXML
    private Label metalLabel;

    @FXML
    private Label alloysLabel;

    @FXML
    private Button nextTurnButton;

    @FXML
    private Tab battleTab;

    @FXML
    private TabPane mainTabPane;



    private final Game game = Game.getInstance();




    @FXML
    void initialize(){
        nextTurnButton.setOnAction(event -> nextTurn());
        ControllerMediator.getInstance().registerMainController(this);
        initializeOthers();
    }


    private void initializeOthers() {
        alloysLabel.textProperty().bind(game.getColony().getAlloysProperty());
        metalLabel.textProperty().bind(game.getColony().getMetalProperty());
    }

    private void nextTurn() {

        game.getColony().produceResources();
        List<SpaceShip> producedSpaceShips = game.getColony().getShipyard().manufacture();
        if (!producedSpaceShips.isEmpty()) {
            game.getColony().getSpaceShips().addAll(producedSpaceShips);

        }
        game.setTurn(game.getTurn() + 1 );
        game.getTurnProperty().set(game.getTurn());

        if(game.getTurn() % 10 == 0){
            mainTabPane.getSelectionModel().select(battleTab);
            Fleet defendingFleet = new Fleet(game.getColony().getSpaceShips(), game.getTechnologies());
            BattleCreator creator = new BattleCreator(game.getTurn(), defendingFleet);
            Battle battle = creator.createBattle();
            ControllerMediator.getInstance().battleControllerAction(battle);
        }
    }
}
