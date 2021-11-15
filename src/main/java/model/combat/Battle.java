package model.combat;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.Game;
import model.spaceShips.SpaceShip;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class Battle {

    private final Fleet attackingFleet;
    private final Fleet defendingFleet;
    private Random random = new Random();
    private int roundCounter = 0;
    private Game game = Game.getInstance();


    public String startCombat() {
        StringBuilder battleRaport = new StringBuilder();
        prepareShips();

        while (!attackingFleet.getSpaceShips().isEmpty() && !defendingFleet.getSpaceShips().isEmpty()) {
            battleRaport.append(runNextRound());
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
            Alert alert = new Alert(Alert.AlertType.NONE, "Your fleet was destroyed.\n You managed to escape and found a place for a new colony.", ButtonType.OK);
            alert.showAndWait();
            game.setColony(game.resetColony());

        }

        game.getColony().setSpaceShips(defendingFleet.getSpaceShips());


        return battleRaport.toString();
    }


    private String runNextRound() {


        resetShields();


        StringBuilder roundRaport = new StringBuilder();
        roundRaport.append("ROUND ")
                .append(++roundCounter);

        int defendingFleetSize = defendingFleet.getSpaceShips().size();
        int attackingFleetSize = attackingFleet.getSpaceShips().size();

        for (SpaceShip atacker : attackingFleet.getSpaceShips()) {
            int shipIndex = random.nextInt(defendingFleetSize);

            defendingFleet.getSpaceShips().get(shipIndex)
                    .setCurrentHullPoints(atacker.attack(defendingFleet.getSpaceShips().get(shipIndex), attackingFleet.getTechnologies()).getCurrentHullPoints());
        }


        for (SpaceShip deffender : defendingFleet.getSpaceShips()) {
            int shipIndex = random.nextInt(attackingFleetSize);

            attackingFleet.getSpaceShips().get(shipIndex)
                    .setCurrentHullPoints(deffender.attack(attackingFleet.getSpaceShips().get(shipIndex), defendingFleet.getTechnologies()).getCurrentHullPoints());
        }

        roundRaport.append("\n\nAtacker shot ")
                .append(attackingFleetSize)
                .append(" times, destroying ")
                .append(getDestroyedSpaceshipsCount(defendingFleet.getSpaceShips()))
                .append(" ships.\n")
                .append("Deffender shot ")
                .append(defendingFleetSize)
                .append(" times, destroying ")
                .append(getDestroyedSpaceshipsCount(attackingFleet.getSpaceShips()))
                .append(" ships.\n\n");


        attackingFleet.setSpaceShips(attackingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getCurrentHullPoints() > 0).collect(Collectors.toList()));
        defendingFleet.setSpaceShips(defendingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getCurrentHullPoints() > 0).collect(Collectors.toList()));

        return roundRaport.toString();
    }


    private int getDestroyedSpaceshipsCount(List<SpaceShip> fleet) {
        int destroyedSpaceshipsCount = 0;
        for (SpaceShip spaceShip : fleet) {
            if (spaceShip.getCurrentHullPoints() <= 0) {
                destroyedSpaceshipsCount++;
            }
        }
        return destroyedSpaceshipsCount;
    }

    private void prepareShips() {
        for (SpaceShip spaceShip : attackingFleet.getSpaceShips()) {
            spaceShip.setCurrentHullPoints(spaceShip.getMaxHull(attackingFleet.getTechnologies()));
        }
        for (SpaceShip spaceShip : defendingFleet.getSpaceShips()) {
            spaceShip.setCurrentHullPoints(spaceShip.getMaxHull(defendingFleet.getTechnologies()));
        }
    }

    private void resetShields() {
        for (SpaceShip spaceShip : attackingFleet.getSpaceShips()) {
            spaceShip.setCurrentShieldPoints(spaceShip.getShield(attackingFleet.getTechnologies()));
        }
        for (SpaceShip spaceShip : defendingFleet.getSpaceShips()) {
            spaceShip.setCurrentShieldPoints(spaceShip.getShield(defendingFleet.getTechnologies()));
        }
    }


}
