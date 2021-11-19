package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.combat.Battle;

public class BattlePaneController {




    @FXML
    private TextArea battleRaportTextArea;

    @FXML
    private Label turnsToBattleLabel;

    @FXML
    void initialize() {

        ControllerMediator.getInstance().registerBattleController(this);

    }

    public void startBattle(Battle battle){
        battleRaportTextArea.setText(battle.startCombat());
    }

    public void updateLabel(int turnsToBattle){
        turnsToBattleLabel.setText(String.valueOf(turnsToBattle));
    }


}
