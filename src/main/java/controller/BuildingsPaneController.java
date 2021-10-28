package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Game;
import model.buildings.Alloyworks;
import model.buildings.Building;
import model.buildings.Laboratory;
import model.buildings.MetalMine;
import model.buildings.Shipyard;

public class BuildingsPaneController {

    @FXML
    private Button metalMineBuyButton;

    @FXML
    private Label metalMineLabel;

    @FXML
    private Label metalMineDescriptionLabel;

    @FXML
    private Label metalMineCostLabel;

    @FXML
    private Button alloyworksBuyButton;

    @FXML
    private Label alloyworksLabel;

    @FXML
    private Label alloyworksDescriptionLabel;

    @FXML
    private Label alloyworksCostLabel;

    @FXML
    private Label shipyardLabel;

    @FXML
    private Button shipyardBuyButton;

    @FXML
    private Label laboratoryCostLabel;

    @FXML
    private Label laboratoryLabel;

    @FXML
    private Button laboratoryBuyButton;

    private final Game game = Game.getInstance();

    @FXML
    void initialize() {

        initializeMetalMine();
        initializeAlloyworks();
        initializeShipyard();
        initializeLaboratory();

    }


    private void initializeAlloyworks() {
        alloyworksCostLabel.textProperty().bind(game.getColony().getAlloyworks().getCostProperty());
        alloyworksBuyButton.setOnAction(event -> game.getColony().setAlloyworks((Alloyworks) upgrade(game.getColony().getAlloyworks())));
        alloyworksDescriptionLabel.textProperty().bind(game.getColony().getAlloyworks().getProductionProperty());
        alloyworksLabel.textProperty().bind(game.getColony().getAlloyworks().getLevelProperty());
    }

    private void initializeMetalMine() {
        metalMineCostLabel.textProperty().bind(game.getColony().getMetalMine().getCostProperty());
        metalMineBuyButton.setOnAction(event -> game.getColony().setMetalMine((MetalMine) upgrade(game.getColony().getMetalMine())));
        metalMineDescriptionLabel.textProperty().bind(game.getColony().getMetalMine().getProductionProperty());
        metalMineLabel.textProperty().bind(game.getColony().getMetalMine().getLevelProperty());
    }

    private void initializeShipyard() {
        laboratoryCostLabel.textProperty().bind(game.getColony().getShipyard().getCostProperty());
        shipyardBuyButton.setOnAction(event -> game.getColony().setShipyard((Shipyard) upgrade(game.getColony().getShipyard())));
        shipyardLabel.textProperty().bind(game.getColony().getShipyard().getLevelProperty());
    }
    private void initializeLaboratory() {
        laboratoryCostLabel.textProperty().bind(game.getColony().getLaboratory().getCostProperty());
        laboratoryBuyButton.setOnAction(event -> game.getColony().setLaboratory((Laboratory) upgrade(game.getColony().getLaboratory())));
        laboratoryLabel.textProperty().bind(game.getColony().getLaboratory().getLevelProperty());
    }

    private Building upgrade(Building building) {
        if (building.upgradeMetalCost() <= game.getColony().getMetal() && building.upgradeAlloysCost() <= game.getColony().getAlloys()) {

            game.getColony().setMetal(game.getColony().getMetal() - building.upgradeMetalCost());
            game.getColony().getMetalProperty().set("METAL - " + game.getColony().getMetal().toString());
            game.getColony().setAlloys(game.getColony().getAlloys() - building.upgradeAlloysCost());
            game.getColony().getAlloysProperty().set("ALLOYS - " + game.getColony().getAlloys().toString());
            building.upgrade();
        }
        return building;
    }


}
