package model.spaceShips;

import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FighterTest {

    SpaceShipFactory factory = new SpaceShipFactory();

    SpaceShip fighter = factory.createFighter();

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
    void shouldReturnInfoAboutFighterIncludingHullShieldAndAttackTakingTechnologiesIntoAccount(){

        String info = "FIGHTER\nMetal - 1500\nAlloys - 500\n\n hull - 50\nshield - 11\nattack - 48";

        String result = fighter.getInfo(technologies);

        assertThat(result).isEqualTo(info);
    }

    @Test
    void shouldDealDamageToSpaceShipWhenDamageLesserThanShieldPoints(){

        SpaceShip target = factory.createFighter();
        target.setCurrentShieldPoints(50);

        fighter.attack(target, technologies);

        assertThat(target.getCurrentShieldPoints()).isEqualTo(2);
    }

    @Test
    void shouldDealDamageToSpaceShipWhenDamageHigherThanShieldPoints(){

        SpaceShip target = factory.createFighter();
        target.setCurrentShieldPoints(10);
        target.setCurrentHullPoints(50);

        fighter.attack(target, technologies);

        assertThat(target.getCurrentShieldPoints()).isZero();
        assertThat(target.getCurrentHullPoints()).isEqualTo(12);
    }

    @Test
    void shouldDealBiggerDamageWhenTargetIsABomber(){
        SpaceShip bomber = factory.createBomber();
        bomber.setCurrentShieldPoints(100);
        SpaceShip destroyer = factory.createDestroyer();
        destroyer.setCurrentShieldPoints(100);

        fighter.attack(bomber, technologies);
        fighter.attack(destroyer, technologies);
        int bomberDamage = 100 - bomber.getCurrentShieldPoints();
        int destroyerDamage = 100 - destroyer.getCurrentShieldPoints();

        assertThat(bomberDamage).isEqualTo(destroyerDamage * 2);
    }
}