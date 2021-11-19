package controller;

import com.fasterxml.jackson.databind.SerializationFeature;
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

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainPaneController {

    @FXML
    private Label metalLabel;

    @FXML
    private Label alloysLabel;

    @FXML
    private Label turnLabel;

    @FXML
    private Button nextTurnButton;

    @FXML
    private Tab battleTab;

    @FXML
    private TabPane mainTabPane;

    private final ObjectMapper mapper = new ObjectMapper();



    private final Game game = Game.getInstance();




    @FXML
    void initialize(){
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        nextTurnButton.setOnAction(event -> nextTurn());
        ControllerMediator.getInstance().registerMainController(this);
        initializeOthers();
    }


    private void initializeOthers() {
        alloysLabel.textProperty().bind(game.getColony().getAlloysProperty());
        metalLabel.textProperty().bind(game.getColony().getMetalProperty());
        turnLabel.textProperty().bind(game.getTurnProperty());
    }

    private void nextTurn() {

        game.getColony().produceResources();
        List<SpaceShip> producedSpaceShips = game.getColony().getShipyard().manufacture();
        if (!producedSpaceShips.isEmpty()) {
            game.getColony().getSpaceShips().addAll(producedSpaceShips);

        }
        game.setTurn(game.getTurn() + 1 );
        game.getTurnProperty().set("TURN - " + game.getTurn());
        ControllerMediator.getInstance().battleControllerUpdateLabel(100 - (game.getTurn() % 100));

        if(game.getTurn() % 100 == 0){
            mainTabPane.getSelectionModel().select(battleTab);
            Fleet defendingFleet = new Fleet(game.getColony().getSpaceShips(), game.getTechnologies());
            BattleCreator creator = new BattleCreator(game.getTurn(), defendingFleet);
            Battle battle = creator.createBattle();
            ControllerMediator.getInstance().battleControllerAction(battle);
        }

        try {
            mapper.writeValue(new File("save.json"), game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
