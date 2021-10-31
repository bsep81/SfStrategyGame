package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Game;
import model.technologies.Technology;

public class TechnologyController {

    @FXML
    private Label hullLabel;

    @FXML
    private Button hullTechnologyUpgradeButton;

    @FXML
    private Label hullTechnologyCostLabel;

    @FXML
    private Label shieldLabel;

    @FXML
    private Button shieldTechnologyUpgradeButton;

    @FXML
    private Label shieldTechnologyCostLabel;

    private final Game game = Game.getInstance();

    @FXML
    void initialize(){
        initializeHullTechnology();
        initializeShieldTechnology();
    }

    private void initializeHullTechnology() {
        hullLabel.textProperty().bind(game.getTechnologies().getHullTechnology().getLevelProperty());
        hullTechnologyCostLabel.textProperty().bind(game.getTechnologies().getHullTechnology().getCostProperty());
        hullTechnologyUpgradeButton.setOnAction(event -> upgrade(game.getTechnologies().getHullTechnology()));
    }

    private void initializeShieldTechnology() {
        shieldLabel.textProperty().bind(game.getTechnologies().getShieldTechnology().getLevelProperty());
        shieldTechnologyCostLabel.textProperty().bind(game.getTechnologies().getShieldTechnology().getCostProperty());
        shieldTechnologyUpgradeButton.setOnAction(event -> upgrade(game.getTechnologies().getShieldTechnology()));
    }

    private void upgrade(Technology technology) {
        if(technology.canAffordUpgrade(game.getColony().getMetal(), game.getColony().getAlloys()) &&
                technology.getLevel() < game.getColony().getLaboratory().getLevel()){
            game.getColony().payMetal(technology.upgradeMetalCost());
            game.getColony().payAlloys(technology.upgradeAlloysCost());
            technology.upgrade();

        }
    }


}
