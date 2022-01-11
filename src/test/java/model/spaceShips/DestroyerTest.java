package model.spaceShips;

import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DestroyerTest {

    private final SpaceShipFactory factory = new SpaceShipFactory();

    private final SpaceShip destroyer = factory.createDestroyer();

    private final Technologies technologies = Technologies.builder()
            .attackTechnology(new AttackTechnology(0,0))
            .shieldTechnology(new ShieldTechnology(0,0))
            .hullTechnology(new HullTechnology(0,0))
            .build();

    @BeforeEach
    void setUp(){
        technologies.getAttackTechnology().setLevel(2);
        technologies.getShieldTechnology().setLevel(1);
        technologies.getHullTechnology().setLevel(0);
    }

    @Test
    void shouldReturnInfoAboutDestroyerIncludingHullShieldAndAttackTakingTechnologiesIntoAccount(){

        String info = "DESTROYER\nMetal - 60000\nAlloys - 15000\n\n hull - 300\nshield - 33\nattack - 180";

        String result = destroyer.getInfo(technologies);

        assertThat(result).isEqualTo(info);
    }

    @Test
    void shouldDealDamageToSpaceShipWhenDamageLesserThanShieldPoints(){

        SpaceShip target = factory.createDestroyer();
        target.setCurrentShieldPoints(250);

        destroyer.attack(target, technologies);

        assertThat(target.getCurrentShieldPoints()).isEqualTo(70);
    }

    @Test
    void shouldDealDamageToSpaceShipWhenDamageHigherThanShieldPoints(){

        SpaceShip target = factory.createBomber();
        target.setCurrentShieldPoints(40);
        target.setCurrentHullPoints(150);

        destroyer.attack(target, technologies);

        assertThat(target.getCurrentShieldPoints()).isZero();
        assertThat(target.getCurrentHullPoints()).isEqualTo(10);
    }

    @Test
    void shouldDealBiggerDamageWhenTargetIsACruiser(){
        SpaceShip cruiser = factory.createCruiser();
        cruiser.setCurrentShieldPoints(300);
        SpaceShip bomber = factory.createBomber();
        bomber.setCurrentShieldPoints(300);

        destroyer.attack(cruiser, technologies);
        destroyer.attack(bomber, technologies);
        int cruiserDamage = 300 - cruiser.getCurrentShieldPoints();
        int bomberDamage = 300 - bomber.getCurrentShieldPoints();

        assertThat(cruiserDamage).isEqualTo((int)(bomberDamage * 1.3));
    }
}