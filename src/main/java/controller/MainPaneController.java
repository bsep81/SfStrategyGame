package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Colony;
import model.buildings.Alloyworks;
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

    private Colony colony = Colony.builder()
            .metal(500)
            .alloys(500)
            .metalMine(new MetalMine())
            .alloyworks(new Alloyworks())
            .build();

    @FXML
    void initialize() {
        nextTurnButton.setOnAction(event -> nextTurn());
        metalLabel.textProperty().bind(colony.getMetalProperty());
        alloysLabel.textProperty().bind(colony.getAlloysProperty());

    }

    private void nextTurn() {

        colony.produceResources();

    }
}
