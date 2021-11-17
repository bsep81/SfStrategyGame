package model.combat;

import controller.ControllerMediator;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.Game;
import model.spaceShips.Bomber;
import model.spaceShips.Cruiser;
import model.spaceShips.Destroyer;
import model.spaceShips.Fighter;
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

        battleRaport.append(getFleetsInfo());

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
            game.setColony(game.resetColony());
            ControllerMediator.getInstance().buildingsControllerInitialize();
            ControllerMediator.getInstance().mainControllerInitialize();
            Alert alert = new Alert(Alert.AlertType.NONE, "Your fleet was destroyed.\n You managed to escape and found a place for a new colony.", ButtonType.OK);
            alert.showAndWait();

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

        roundRaport.append(getFleetsInfo());

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

    private String getFleetsInfo(){
        StringBuilder info = new StringBuilder();

        long atackingFighterCount = attackingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Fighter.class)).count();
        long atackingCruiserCount = attackingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Cruiser.class)).count();
        long atackingDestroyerCount = attackingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Destroyer.class)).count();
        long atackingBomberCount = attackingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Bomber.class)).count();

        long defendingFighterCount = defendingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Fighter.class)).count();
        long defendingCruiserCount = defendingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Cruiser.class)).count();
        long defendingDestroyerCount = defendingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Destroyer.class)).count();
        long defendingBomberCount = defendingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Bomber.class)).count();


        info.append("Atacker\t\t\t\t\tDeffender\n")
                .append(atackingFighterCount)
                .append("\t\tFIGHTER\t\t\t")
                .append(defendingFighterCount)
                .append("\n")
                .append(atackingCruiserCount)
                .append("\t\tCRUISER\t\t\t")
                .append(defendingCruiserCount)
                .append("\n")
                .append(atackingDestroyerCount)
                .append("\t\tDESTROYER\t\t")
                .append(defendingDestroyerCount)
                .append("\n")
                .append(atackingBomberCount)
                .append("\t\tBOMBER\t\t\t")
                .append(defendingBomberCount)
                .append("\n");

        return info.toString();
    }


}
