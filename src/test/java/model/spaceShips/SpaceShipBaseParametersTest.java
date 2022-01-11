package model.spaceShips;

import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpaceShipBaseParametersTest {

    private final SpaceShipBaseParameters baseParameters = new SpaceShipBaseParameters(SpaceShipType.FIGHTER, 50, 10, 40);
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
    void shouldReturnAttackPoints(){
        int result = baseParameters.getAttackPower(technologies);

        assertThat(result).isEqualTo(48);
    }

    @Test
    void shouldReturnMaxShieldPoints(){
        int result = baseParameters.getShieldPoints(technologies);

        assertThat(result).isEqualTo(11);
    }

    @Test
    void shouldReturnMaxHullPoints(){
        int result = baseParameters.getHullPoints(technologies);

        assertThat(result).isEqualTo(50);
    }

}