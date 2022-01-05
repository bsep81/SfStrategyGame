package model.combat;

import model.spaceShips.SpaceShip;
import service.combat.BattleService;

import java.util.Random;
import java.util.stream.Collectors;

public class BattleRound {

    private int roundCounter = 0;
    private final Random random;
    private final BattleService battleService = new BattleService();

    public BattleRound(Random random) {
        this.random = random;
    }

    public String runNextRound(Fleet attackingFleet, Fleet defendingFleet) {


        battleService.resetShields(attackingFleet, defendingFleet);


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

        roundRaport.append("\n\nAttacker shot ")
                .append(attackingFleetSize)
                .append(" times, destroying ")
                .append(battleService.getDestroyedSpaceshipsCount(defendingFleet.getSpaceShips()))
                .append(" ships.\n")
                .append("Defender shot ")
                .append(defendingFleetSize)
                .append(" times, destroying ")
                .append(battleService.getDestroyedSpaceshipsCount(attackingFleet.getSpaceShips()))
                .append(" ships.\n\n");


        attackingFleet.setSpaceShips(attackingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getCurrentHullPoints() > 0).collect(Collectors.toList()));
        defendingFleet.setSpaceShips(defendingFleet.getSpaceShips().stream().filter(spaceShip -> spaceShip.getCurrentHullPoints() > 0).collect(Collectors.toList()));

        roundRaport.append(battleService.getFleetsInfo(attackingFleet, defendingFleet));

        return roundRaport.toString();
    }
}
