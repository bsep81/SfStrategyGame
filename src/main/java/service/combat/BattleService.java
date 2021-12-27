package service.combat;

import model.combat.Fleet;
import model.spaceShips.Bomber;
import model.spaceShips.Cruiser;
import model.spaceShips.Destroyer;
import model.spaceShips.Fighter;
import model.spaceShips.SpaceShip;

import java.util.List;

public class BattleService {






    public int getDestroyedSpaceshipsCount(List<SpaceShip> fleet) {
        int destroyedSpaceshipsCount = 0;
        for (SpaceShip spaceShip : fleet) {
            if (spaceShip.getCurrentHullPoints() <= 0) {
                destroyedSpaceshipsCount++;
            }
        }
        return destroyedSpaceshipsCount;
    }

    public void prepareShips(Fleet attackingFleet, Fleet defendingFleet) {
        for (SpaceShip spaceShip : attackingFleet.getSpaceShips()) {
            spaceShip.setCurrentHullPoints(spaceShip.getMaxHull(attackingFleet.getTechnologies()));
        }
        for (SpaceShip spaceShip : defendingFleet.getSpaceShips()) {
            spaceShip.setCurrentHullPoints(spaceShip.getMaxHull(defendingFleet.getTechnologies()));
        }
    }

    public void resetShields(Fleet attackingFleet, Fleet defendingFleet) {
        for (SpaceShip spaceShip : attackingFleet.getSpaceShips()) {
            spaceShip.setCurrentShieldPoints(spaceShip.getShield(attackingFleet.getTechnologies()));
        }
        for (SpaceShip spaceShip : defendingFleet.getSpaceShips()) {
            spaceShip.setCurrentShieldPoints(spaceShip.getShield(defendingFleet.getTechnologies()));
        }
    }

    public String getFleetsInfo(Fleet attackingFleet, Fleet defendingFleet){
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
