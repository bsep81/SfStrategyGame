package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Colony;
import model.buildings.Alloyworks;
import model.buildings.Building;
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
    private Label metalLabel;

    @FXML
    private Label alloysLabel;

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
    private Label shipyardCostLabel;



    @FXML
    private Button nextTurnButton;

    private final Colony colony = Colony.builder()
            .metal(1500)
            .alloys(500)
            .metalMine(new MetalMine(500, 0))
            .alloyworks(new Alloyworks(1000, 500))
            .shipyard(new Shipyard(5000, 5000))
            .build();

    @FXML
    void initialize() {
        nextTurnButton.setOnAction(event -> nextTurn());
        initializeMetalMine();
        initializeAlloyworks();
        initializeShipyard();
        initializeOthers();

    }




    private void initializeAlloyworks() {

        alloyworksCostLabel.textProperty().bind(colony.getAlloyworks().getCostProperty());
        alloyworksBuyButton.setOnAction(event -> colony.setAlloyworks((Alloyworks) upgrade(colony.getAlloyworks())));
        alloyworksDescriptionLabel.textProperty().bind(colony.getAlloyworks().getProductionProperty());
        alloyworksLabel.textProperty().bind(colony.getAlloyworks().getLevelProperty());
    }

    private void initializeMetalMine() {

        metalMineCostLabel.textProperty().bind(colony.getMetalMine().getCostProperty());
        metalMineBuyButton.setOnAction(event -> colony.setMetalMine((MetalMine) upgrade(colony.getMetalMine())));
        metalMineDescriptionLabel.textProperty().bind(colony.getMetalMine().getProductionProperty());
        metalMineLabel.textProperty().bind(colony.getMetalMine().getLevelProperty());
    }

    private void initializeShipyard() {
        shipyardCostLabel.textProperty().bind(colony.getShipyard().getCostProperty());
        shipyardBuyButton.setOnAction(event -> colony.setShipyard((Shipyard) upgrade(colony.getShipyard())));
        shipyardLabel.textProperty().bind(colony.getShipyard().getLevelProperty());
    }

    private void initializeOthers() {
        alloysLabel.textProperty().bind(colony.getAlloysProperty());
        metalLabel.textProperty().bind(colony.getMetalProperty());
    }

    private Building upgrade(Building building) {
        if(building.upgradeMetalCost() <= colony.getMetal() && building.upgradeAlloysCost() <= colony.getAlloys() ){

            colony.setMetal(colony.getMetal() - building.upgradeMetalCost());
            colony.getMetalProperty().set(colony.getMetal().toString());
            colony.setAlloys(colony.getAlloys() - building.upgradeAlloysCost());
            colony.getAlloysProperty().set(colony.getAlloys().toString());
            building.upgrade();
        }
        return building;
    }

    private void nextTurn() {

        colony.produceResources();

    }
}
