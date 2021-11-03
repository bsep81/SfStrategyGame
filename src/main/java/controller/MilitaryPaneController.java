package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import model.Game;
import model.spaceShips.Bomber;
import model.spaceShips.Cruiser;
import model.spaceShips.Destroyer;
import model.spaceShips.Fighter;
import model.spaceShips.SpaceShipType;

public class MilitaryPaneController {

    @FXML
    private ListView<String> productionListView;

    @FXML
    private RadioButton figterRadio;

    @FXML
    private ToggleGroup production;

    @FXML
    private RadioButton cruiserRadio;

    @FXML
    private RadioButton destroyerRadio;

    @FXML
    private RadioButton bomberRadio;

    @FXML
    private TextArea spaceShipInfoTextArea;

    @FXML
    private Button addProductionButton;

    @FXML
    private TextArea fleetInfoTextArea;

    private final Game game = Game.getInstance();


    @FXML
    void initialize() {
        figterRadio.setSelected(true);
        spaceShipInfoTextArea.setText(game.getColony().getShipyard().getFactory().createFighter().getInfo(game.getTechnologies()));

        figterRadio.setOnAction(event -> spaceShipInfoTextArea.setText(game.getColony().getShipyard().getFactory().createFighter().getInfo(game.getTechnologies())));
        cruiserRadio.setOnAction(event -> spaceShipInfoTextArea.setText(game.getColony().getShipyard().getFactory().createCruiser().getInfo(game.getTechnologies())));
        destroyerRadio.setOnAction(event -> spaceShipInfoTextArea.setText(game.getColony().getShipyard().getFactory().createDestroyer().getInfo(game.getTechnologies())));
        bomberRadio.setOnAction(event -> spaceShipInfoTextArea.setText(game.getColony().getShipyard().getFactory().createBomber().getInfo(game.getTechnologies())));


        fleetInfoTextArea.setText(game.getColony().getFleetInfo());

        game.getTurnProperty()
                .addListener((observable, oldValue, newValue) -> {
                    productionListView.setItems(getProductionList());
                    fleetInfoTextArea.setText(game.getColony().getFleetInfo());

                });

        addProductionButton.setOnAction(event -> {
            addChosenProduction();
            productionListView.setItems(getProductionList());
        });


    }

    private void addChosenProduction() {
        if (production.getSelectedToggle().equals(figterRadio) && canAffordSpaceShip(SpaceShipType.FIGHTER)) {
            game.getColony().getShipyard().addProduction(game.getColony().getShipyard().getFactory().createFighter());
            game.getColony().payMetal(Fighter.METAL_COST);
            game.getColony().payAlloys(Fighter.ALLOYS_COST);
        }
        if (production.getSelectedToggle().equals(cruiserRadio) && canAffordSpaceShip(SpaceShipType.CRUISER)) {
            game.getColony().getShipyard().addProduction(game.getColony().getShipyard().getFactory().createCruiser());
            game.getColony().payMetal(Cruiser.METAL_COST);
            game.getColony().payAlloys(Cruiser.ALLOYS_COST);
        }
        if (production.getSelectedToggle().equals(destroyerRadio) && canAffordSpaceShip(SpaceShipType.DESTROYER)) {
            game.getColony().getShipyard().addProduction(game.getColony().getShipyard().getFactory().createDestroyer());
            game.getColony().payMetal(Destroyer.METAL_COST);
            game.getColony().payAlloys(Destroyer.ALLOYS_COST);
        }
        if (production.getSelectedToggle().equals(bomberRadio) && canAffordSpaceShip(SpaceShipType.BOMBER)) {
            game.getColony().getShipyard().addProduction(game.getColony().getShipyard().getFactory().createBomber());
            game.getColony().payMetal(Bomber.METAL_COST);
            game.getColony().payAlloys(Bomber.ALLOYS_COST);
        }

        game.getColony().setMetalProperty();
        game.getColony().setAlloysProperty();
    }


    private boolean canAffordSpaceShip(SpaceShipType type) {
        switch (type) {
            case FIGHTER -> {
                return game.getColony().getMetal() >= Fighter.METAL_COST && game.getColony().getAlloys() >= Fighter.ALLOYS_COST;
            }
            case CRUISER -> {
                return game.getColony().getMetal() >= Cruiser.METAL_COST && game.getColony().getAlloys() >= Cruiser.ALLOYS_COST;
            }
            case DESTROYER -> {
                return game.getColony().getMetal() >= Destroyer.METAL_COST && game.getColony().getAlloys() >= Destroyer.ALLOYS_COST;
            }
            case BOMBER -> {
                return game.getColony().getMetal() >= Bomber.METAL_COST && game.getColony().getAlloys() >= Bomber.ALLOYS_COST;
            }
            default -> {
                return false;
            }
        }
    }

    private ObservableList<String> getProductionList() {
        ObservableList<String> productionList = FXCollections.observableArrayList();

        game.getColony().getShipyard().getSpaceShipsProductionQueue()
                .forEach(spaceShip -> productionList.add(spaceShip.getClass().getName().substring(17)));

        return productionList;
    }


}
