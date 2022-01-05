package service.combat;

import model.combat.Fleet;
import model.spaceShips.SpaceShip;
import model.spaceShips.SpaceShipFactory;
import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class BattleServiceTest {

    BattleService battleService = new BattleService();
    SpaceShipFactory factory = new SpaceShipFactory();

    Technologies technologies = Technologies.builder()
            .attackTechnology(new AttackTechnology(0,0))
            .shieldTechnology(new ShieldTechnology(0,0))
            .hullTechnology(new HullTechnology(0,0))
            .build();

    List<SpaceShip> defendingSpaceShips = new ArrayList<>();
    List<SpaceShip> attackingSpaceShips = new ArrayList<>();
    Fleet defendingFleet;
    Fleet attackingFleet;




    @BeforeEach
    void setUp(){
        technologies.getAttackTechnology().setLevel(2);
        technologies.getShieldTechnology().setLevel(1);
        technologies.getHullTechnology().setLevel(0);

        defendingSpaceShips = List.of(factory.createFighter(),
                factory.createCruiser(),
                factory.createDestroyer(),
                factory.createBomber());

        attackingSpaceShips = List.of(factory.createFighter(),
                factory.createFighter(),
                factory.createFighter());

        defendingFleet = new Fleet(defendingSpaceShips, technologies);
        attackingFleet = new Fleet(attackingSpaceShips, technologies);
    }

    @Test
    void shouldReturnNumberOfDestroyedShips(){

        for (SpaceShip spaceShip : defendingSpaceShips) {
            spaceShip.setCurrentHullPoints(spaceShip.getMaxHull(defendingFleet.getTechnologies()));
        }
        defendingSpaceShips.get(0).setCurrentHullPoints(0);
        defendingSpaceShips.get(2).setCurrentHullPoints(-3);

        int result = battleService.getDestroyedSpaceshipsCount(defendingSpaceShips);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldPrepareShipsForBattleBySettingTheirHullPointsToMaximum(){
        battleService.prepareShips(attackingFleet, defendingFleet);

        for (SpaceShip spaceShip : attackingFleet.getSpaceShips()) {
            assertThat(spaceShip.getCurrentHullPoints()).isEqualTo(spaceShip.getMaxHull(technologies));
        }
    }

    @Test
    void shouldResetShieldPointsToMaximum(){
        battleService.resetShields(attackingFleet, defendingFleet);

        for (SpaceShip spaceShip : attackingFleet.getSpaceShips()) {
            assertThat(spaceShip.getCurrentShieldPoints()).isEqualTo(spaceShip.getShield(technologies));
        }
    }

    @Test
    void shouldPrepareFormattedInfoAboutBothFleets(){
        String result = battleService.getFleetsInfo(attackingFleet, defendingFleet);

        String info = "Attacker\t\t\t\t\tDefender\n3\t\tFIGHTER\t\t\t1\n0\t\tCRUISER\t\t\t1\n0\t\tDESTROYER\t\t1\n0\t\tBOMBER\t\t\t1\n";

        assertThat(result).isEqualTo(info);

    }


}