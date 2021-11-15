package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.combat.Battle;
import model.combat.Fleet;
import model.spaceShips.SpaceShip;
import model.spaceShips.SpaceShipFactory;
import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattlePaneController {


    @FXML
    private Button startCombatButton;

    @FXML
    private TextArea battleRaportTextArea;
    private final SpaceShipFactory factory = new SpaceShipFactory();





    @FXML
    void initialize() {
        startCombatButton.setOnAction(event -> startBattle());
        ControllerMediator.getInstance().registerBattleController(this);

    }

    public void booom(Battle battle){
        battleRaportTextArea.setText(battle.startCombat());
    }

    private void startBattle() {

        Technologies atkTech = Technologies.builder()
                .hullTechnology(new HullTechnology(0,0))
                .shieldTechnology(new ShieldTechnology(0,0))
                .attackTechnology(new AttackTechnology(0,0))
                .build();
        atkTech.getAttackTechnology().setLevel(1);
        atkTech.getShieldTechnology().setLevel(1);
        atkTech.getHullTechnology().setLevel(1);

        Technologies defTech = Technologies.builder()
                .hullTechnology(new HullTechnology(0,0))
                .shieldTechnology(new ShieldTechnology(0,0))
                .attackTechnology(new AttackTechnology(0,0))
                .build();
        defTech.getAttackTechnology().setLevel(300);
        defTech.getShieldTechnology().setLevel(300);
        defTech.getHullTechnology().setLevel(300);



        Random random = new Random();
        List<SpaceShip> atkShips = new ArrayList<>();
        List<SpaceShip> defShips = new ArrayList<>();

        for(int i = 0; i < 1000000; i++){
            switch (random.nextInt(4)) {
                case 0 -> atkShips.add(factory.createFighter());
                case 1 -> atkShips.add(factory.createCruiser());
                case 2 -> atkShips.add(factory.createDestroyer());
                case 3 -> atkShips.add(factory.createBomber());
            }
        }

        for(int i = 0; i < 100000; i++){
            switch (random.nextInt(5)) {
                case 0, 4 -> defShips.add(factory.createFighter());
                case 1 -> defShips.add(factory.createCruiser());
                case 2 -> defShips.add(factory.createDestroyer());
                case 3 -> defShips.add(factory.createBomber());
            }
        }


        Fleet atkFleet = new Fleet(atkShips, atkTech);
        Fleet defFleet = new Fleet(defShips, defTech);

        Battle battle = new Battle(atkFleet, defFleet);



        battleRaportTextArea.setText(battle.startCombat());

    }
}
