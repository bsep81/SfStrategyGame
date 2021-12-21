package model.spaceShips;

import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CruiserTest {

    SpaceShipFactory factory = new SpaceShipFactory();

    SpaceShip cruiser = factory.createCruiser();

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
    void shouldReturnInfoAboutCruiserIncludingHullShieldAndAttackTakingTechnologiesIntoAccount(){

        String info = "CRUISER\nMetal - 20000\nAlloys - 7000\n\n hull - 200\nshield - 22\nattack - 84";

        String result = cruiser.getInfo(technologies);

        assertThat(result).isEqualTo(info);
    }

    @Test
    void shouldDealDamageToSpaceShipWhenDamageLesserThanShieldPoints(){

        SpaceShip target = factory.createCruiser();
        target.setCurrentShieldPoints(90);

        cruiser.attack(target, technologies);

        assertThat(target.getCurrentShieldPoints()).isEqualTo(6);
    }

    @Test
    void shouldDealDamageToSpaceShipWhenDamageHigherThanShieldPoints(){

        SpaceShip target = factory.createFighter();
        target.setCurrentShieldPoints(50);
        target.setCurrentHullPoints(50);

        cruiser.attack(target, technologies);

        assertThat(target.getCurrentShieldPoints()).isZero();
        assertThat(target.getCurrentHullPoints()).isEqualTo(16);
    }


}