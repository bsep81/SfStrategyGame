package model.spaceShips;

import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BomberTest {

    SpaceShipFactory factory = new SpaceShipFactory();

    SpaceShip bomber = factory.createBomber();

    Technologies technologies = Technologies.builder()
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
    void shouldReturnInfoAboutBomberIncludingHullShieldAndAttackTakingTechnologiesIntoAccount(){

        String info = "BOMBER\nMetal - 85000\nAlloys - 30000\n\n hull - 250\nshield - 22\nattack - 192";

        String result = bomber.getInfo(technologies);

        assertThat(result).isEqualTo(info);
    }

    @Test
    void shouldDealDamageToSpaceShipWhenDamageLesserThanShieldPoints(){

        SpaceShip target = factory.createDestroyer();
        target.setCurrentShieldPoints(250);

        bomber.attack(target, technologies);

        assertThat(target.getCurrentShieldPoints()).isEqualTo(58);
    }

    @Test
    void shouldDealDamageToSpaceShipWhenDamageHigherThanShieldPoints(){

        SpaceShip target = factory.createDestroyer();
        target.setCurrentShieldPoints(100);
        target.setCurrentHullPoints(150);

        bomber.attack(target, technologies);

        assertThat(target.getCurrentShieldPoints()).isZero();
        assertThat(target.getCurrentHullPoints()).isEqualTo(58);
    }

    @Test
    void shouldDealLessDamageWhenTargetIsAFighter(){
        SpaceShip fighter = factory.createFighter();
        fighter.setCurrentShieldPoints(200);
        SpaceShip destroyer = factory.createDestroyer();
        destroyer.setCurrentShieldPoints(200);

        bomber.attack(fighter, technologies);
        bomber.attack(destroyer, technologies);
        int fighterDamage = 200 - fighter.getCurrentShieldPoints();
        int destroyerDamage = 200 - destroyer.getCurrentShieldPoints();

        assertThat(fighterDamage).isEqualTo((int)(destroyerDamage * 0.5));
    }
}