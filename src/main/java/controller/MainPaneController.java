package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Colony;
import model.buildings.Alloyworks;
import model.buildings.Building;
import model.buildings.MetalMine;

public class MainPaneController {

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
    private Button nextTurnButton;

    private final Colony colony = Colony.builder()
            .metal(500)
            .alloys(500)
            .metalMine(new MetalMine())
            .alloyworks(new Alloyworks())
            .build();

    @FXML
    void initialize() {
        nextTurnButton.setOnAction(event -> nextTurn());

        metalLabel.textProperty().bind(colony.getMetalProperty());
        metalMineCostLabel.textProperty().bind(colony.getMetalMine().getCostProperty());
        metalMineBuyButton.setOnAction(event -> colony.setMetalMine((MetalMine) upgrade(colony.getMetalMine())));
        metalMineDescriptionLabel.textProperty().bind(colony.getMetalMine().getProductionProperty());
        metalMineLabel.textProperty().bind(colony.getMetalMine().getLevelProperty());

        alloysLabel.textProperty().bind(colony.getAlloysProperty());
        alloyworksCostLabel.textProperty().bind(colony.getAlloyworks().getCostProperty());
        alloyworksBuyButton.setOnAction(event -> colony.setAlloyworks((Alloyworks) upgrade(colony.getAlloyworks())));
        alloyworksDescriptionLabel.textProperty().bind(colony.getAlloyworks().getProductionProperty());
        alloyworksLabel.textProperty().bind(colony.getAlloyworks().getLevelProperty());

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
