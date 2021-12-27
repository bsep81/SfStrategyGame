package model.combat;

import controller.ControllerMediator;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.Game;
import service.combat.BattleService;


@Data
@RequiredArgsConstructor
public class Battle {

    private final Fleet attackingFleet;
    private final Fleet defendingFleet;


    private Game game = Game.getInstance();
    private BattleService battleService = new BattleService();
    private BattleRound battleRound = new BattleRound();


    public String startCombat() {
        StringBuilder battleRaport = new StringBuilder();
        battleService.prepareShips(attackingFleet, defendingFleet);

        battleRaport.append(battleService.getFleetsInfo(attackingFleet,defendingFleet));

        while (!attackingFleet.getSpaceShips().isEmpty() && !defendingFleet.getSpaceShips().isEmpty()) {
            battleRaport.append(battleRound.runNextRound(attackingFleet,defendingFleet));
        }

        if(attackingFleet.getSpaceShips().isEmpty()){
            battleRaport.append("Defender won with ")
                    .append(defendingFleet.getSpaceShips().size())
                    .append(" ships left");
        }else if(defendingFleet.getSpaceShips().isEmpty()){
            battleRaport.append("Atacker won with ")
                    .append(attackingFleet.getSpaceShips().size())
                    .append(" ships left");
        }else{
            battleRaport.append("Atacker - ")
                    .append(attackingFleet.getSpaceShips().size())
                    .append("\nDeffender - ")
                    .append(defendingFleet.getSpaceShips().size());
        }

        if(defendingFleet.getSpaceShips().isEmpty()){
            game.setColony(game.resetColony());
            ControllerMediator.getInstance().buildingsControllerInitialize();
            ControllerMediator.getInstance().mainControllerInitialize();
            Alert alert = new Alert(Alert.AlertType.NONE, "Your fleet was destroyed.\n You managed to escape and found a place for a new colony.", ButtonType.OK);
            alert.showAndWait();

        }

        game.getColony().setSpaceShips(defendingFleet.getSpaceShips());


        return battleRaport.toString();
    }





}
