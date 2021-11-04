package model.combat;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.Game;
import model.spaceShips.SpaceShip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@RequiredArgsConstructor
public class Battle {

    private final List<SpaceShip> attackingFleet;
    private final List<SpaceShip> defendingFleet;
    private Random random = new Random();
    private int roundCounter = 0;
    private Game game = Game.getInstance();


    public String startCombat() {
        StringBuilder battleRaport = new StringBuilder();

        while (!attackingFleet.isEmpty() && !defendingFleet.isEmpty()) {
            battleRaport.append(runNextRound());

        }

        return battleRaport.toString();
    }


    private String runNextRound() {

        resetShields();


        StringBuilder roundRaport = new StringBuilder();
        roundRaport.append("ROUND ")
                .append(++roundCounter);


        int fleetSize = defendingFleet.size();
        for (SpaceShip atacker : attackingFleet) {
            int shipIndex = random.nextInt(fleetSize);

            defendingFleet.get(shipIndex)
                    .setCurrentHullPoints(atacker.attack(defendingFleet.get(shipIndex)).getCurrentHullPoints());

        }

        roundRaport.append("\n\nAtacker shot ")
                .append(attackingFleet.size())
                .append(" times, destroying ")
                .append(getDestroyedSpaceships(defendingFleet))
                .append(" ships.\n");

        fleetSize = attackingFleet.size();
        for (SpaceShip deffender : defendingFleet) {
            int shipIndex = random.nextInt(fleetSize);

            attackingFleet.get(shipIndex)
                    .setCurrentHullPoints(deffender.attack(attackingFleet.get(shipIndex)).getCurrentHullPoints());
        }

        roundRaport.append("Deffender shot ")
                .append(defendingFleet.size())
                .append(" times, destroying ")
                .append(getDestroyedSpaceships(attackingFleet))
                .append(" ships.\n\n");

        attackingFleet.removeAll(getDestroyedSpaceships(attackingFleet));
        defendingFleet.removeAll(getDestroyedSpaceships(defendingFleet));


        return roundRaport.toString();
    }


    private List<SpaceShip> getDestroyedSpaceships(List<SpaceShip> fleet) {
        List<SpaceShip> destroyedSpaceships = new ArrayList<>();
        for (SpaceShip spaceShip : fleet) {
            if (spaceShip.getCurrentHullPoints() <= 0) {
                destroyedSpaceships.add(spaceShip);
            }
        }

        return destroyedSpaceships;
    }

    private void resetShields() {
        for(SpaceShip spaceShip: attackingFleet){
            spaceShip.setCurrentShieldPoints(spaceShip.getShield(Game.getInstance().getTechnologies()));
        }
        for(SpaceShip spaceShip: defendingFleet){
            spaceShip.setCurrentShieldPoints(spaceShip.getShield(Game.getInstance().getTechnologies()));
        }
    }


}
