package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.combat.Battle;

public class BattlePaneController {




    @FXML
    private TextArea battleRaportTextArea;

    @FXML
    void initialize() {

        ControllerMediator.getInstance().registerBattleController(this);

    }

    public void startBattle(Battle battle){
        battleRaportTextArea.setText(battle.startCombat());
    }


}
