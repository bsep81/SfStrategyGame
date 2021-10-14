package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class MilitaryPaneController {

    @FXML
    private ListView<?> productionListView;

    @FXML
    private RadioButton figterRadio;

    @FXML
    private ToggleGroup spaceship;

    @FXML
    private RadioButton cruiserRadio;

    @FXML
    private RadioButton destroyerRadio;

    @FXML
    private RadioButton bomberRadio;

    @FXML
    private Button addProductionButton;

    @FXML
    void initialize() {

    }
}
