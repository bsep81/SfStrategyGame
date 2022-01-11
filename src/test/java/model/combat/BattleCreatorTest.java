package model.combat;

import model.spaceShips.Bomber;
import model.spaceShips.Cruiser;
import model.spaceShips.Destroyer;
import model.spaceShips.Fighter;
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

class BattleCreatorTest {

    private final Technologies technologies = Technologies.builder()
            .attackTechnology(new AttackTechnology(0, 0))
            .shieldTechnology(new ShieldTechnology(0, 0))
            .hullTechnology(new HullTechnology(0, 0))
            .build();

    private final SpaceShipFactory factory = new SpaceShipFactory();
    private BattleCreator creator;

    @BeforeEach
    void setUp() {
        technologies.getAttackTechnology().setLevel(2);
        technologies.getShieldTechnology().setLevel(1);
        technologies.getHullTechnology().setLevel(0);

        List<SpaceShip> defendingSpaceShips = List.of(factory.createFighter(),
                factory.createCruiser(),
                factory.createDestroyer(),
                factory.createBomber());

        Fleet defendingFleet = new Fleet(defendingSpaceShips, technologies);
        creator = new BattleCreator(225, defendingFleet);
    }

    @Test
    void shouldGenerateAtackingFleet() {
        Battle battle = creator.createBattle();

        Fleet atackingFleet = battle.getAttackingFleet();
        List<SpaceShip> atackingShips = new ArrayList<>(atackingFleet.getSpaceShips());
        long fighterCount = atackingShips.stream().filter(spaceShip -> spaceShip.getClass().equals(Fighter.class)).count();
        long cruiserCount = atackingShips.stream().filter(spaceShip -> spaceShip.getClass().equals(Cruiser.class)).count();
        long destroyerCount = atackingShips.stream().filter(spaceShip -> spaceShip.getClass().equals(Destroyer.class)).count();
        long bomberCount = atackingShips.stream().filter(spaceShip -> spaceShip.getClass().equals(Bomber.class)).count();

        Technologies technologies = atackingFleet.getTechnologies();

        assertThat(fighterCount).isEqualTo(37);
        assertThat(cruiserCount).isEqualTo(3);
        assertThat(destroyerCount).isEqualTo(1);
        assertThat(bomberCount).isEqualTo(1);
        assertThat(technologies.getAttackTechnology().getLevel()).isEqualTo(2);
        assertThat(technologies.getHullTechnology().getLevel()).isEqualTo(2);
        assertThat(technologies.getShieldTechnology().getLevel()).isEqualTo(2);

    }

}