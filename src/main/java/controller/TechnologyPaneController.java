package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Game;
import model.technologies.AlloysTechnology;
import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.MiningTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technology;

public class TechnologyPaneController {

    @FXML
    private Label hullLabel;

    @FXML
    private Button hullTechnologyUpgradeButton;

    @FXML
    private Label hullTechnologyCostLabel;

    @FXML
    private Label hullTechnologyDescriptionLabel;

    @FXML
    private Label shieldLabel;

    @FXML
    private Button shieldTechnologyUpgradeButton;

    @FXML
    private Label shieldTechnologyCostLabel;

    @FXML
    private Label shieldTechnologyDescriptionLabel;

    @FXML
    private Label attackLabel;

    @FXML
    private Button attackTechnologyUpgradeButton;

    @FXML
    private Label attackTechnologyCostLabel;

    @FXML
    private Label attackTechnologyDescriptionLabel;

    @FXML
    private Label miningLabel;

    @FXML
    private Button miningTechnologyUpgradeButton;

    @FXML
    private Label miningTechnologyCostLabel;

    @FXML
    private Label miningTechnologyDescriptionLabel;

    @FXML
    private Label alloysLabel;

    @FXML
    private Button alloysTechnologyUpgradeButton;

    @FXML
    private Label alloysTechnologyCostLabel;

    @FXML
    private Label alloysTechnologyDescriptionLabel;

    private final Game game = Game.getInstance();

    @FXML
    void initialize() {

        initializeHullTechnology();
        initializeShieldTechnology();
        initializeAttackTechnology();
        initializeMiningTechnology();
        initializeAlloysTechnology();

    }


    private void initializeHullTechnology() {
        hullLabel.textProperty().bind(game.getTechnologies().getHullTechnology().getLevelProperty());
        hullTechnologyCostLabel.textProperty().bind(game.getTechnologies().getHullTechnology().getCostProperty());
        hullTechnologyUpgradeButton.setOnAction(event -> upgrade(game.getTechnologies().getHullTechnology()));
        hullTechnologyDescriptionLabel.setText(HullTechnology.DESCRIPTION);
    }

    private void initializeShieldTechnology() {
        shieldLabel.textProperty().bind(game.getTechnologies().getShieldTechnology().getLevelProperty());
        shieldTechnologyCostLabel.textProperty().bind(game.getTechnologies().getShieldTechnology().getCostProperty());
        shieldTechnologyUpgradeButton.setOnAction(event -> upgrade(game.getTechnologies().getShieldTechnology()));
        shieldTechnologyDescriptionLabel.setText(ShieldTechnology.DESCRIPTION);
    }

    private void initializeAttackTechnology() {
        attackLabel.textProperty().bind(game.getTechnologies().getAttackTechnology().getLevelProperty());
        attackTechnologyCostLabel.textProperty().bind(game.getTechnologies().getAttackTechnology().getCostProperty());
        attackTechnologyUpgradeButton.setOnAction(event -> upgrade(game.getTechnologies().getAttackTechnology()));
        attackTechnologyDescriptionLabel.setText(AttackTechnology.DESCRIPTION);
    }

    private void initializeMiningTechnology() {
        miningLabel.textProperty().bind(game.getTechnologies().getMiningTechnology().getLevelProperty());
        miningTechnologyCostLabel.textProperty().bind(game.getTechnologies().getMiningTechnology().getCostProperty());
        miningTechnologyUpgradeButton.setOnAction(event -> upgrade(game.getTechnologies().getMiningTechnology()));
        miningTechnologyDescriptionLabel.setText(MiningTechnology.DESCRIPTION);
    }

    private void initializeAlloysTechnology() {
        alloysLabel.textProperty().bind(game.getTechnologies().getAlloysTechnology().getLevelProperty());
        alloysTechnologyCostLabel.textProperty().bind(game.getTechnologies().getAlloysTechnology().getCostProperty());
        alloysTechnologyUpgradeButton.setOnAction(event -> upgrade(game.getTechnologies().getAlloysTechnology()));
        alloysTechnologyDescriptionLabel.setText(AlloysTechnology.DESCRIPTION);
    }

    private void upgrade(Technology technology) {
        if (technology.canAffordUpgrade(game.getColony().getMetal(), game.getColony().getAlloys()) &&
                technology.getLevel() < game.getColony().getLaboratory().getLevel()) {
            game.getColony().payMetal(technology.upgradeMetalCost());
            game.getColony().payAlloys(technology.upgradeAlloysCost());
            technology.upgrade();

        }
    }


}
