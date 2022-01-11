package model.technologies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class HullTechnologyTest {

    private final HullTechnology hullTechnology = new HullTechnology(10000, 10000);
    
    @Test
    void shouldReturnUpgradeMetalCost(){
        hullTechnology.setLevel(5);
        int result = hullTechnology.upgradeMetalCost();

        assertThat(result).isEqualTo(320000);
    }

    @Test
    void shouldReturnUpgradeAlloysCost(){
        hullTechnology.setLevel(7);
        int result = hullTechnology.upgradeAlloysCost();

        assertThat(result).isEqualTo(1280000);
    }

    @Test
    void shouldIncrementHullTechnologyLevelAndSetProperties(){
        hullTechnology.setLevel(4);
        hullTechnology.upgrade();

        assertThat(hullTechnology.getLevel()).isEqualTo(5);
        assertThat(hullTechnology.getLevelProperty().get()).isEqualTo("HULL TECHNOLOGY level 5");
        assertThat(hullTechnology.getCostProperty().get()).isEqualTo("Upgrade cost: 320000 metal, 320000 alloys");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void shouldReturnCorresctValueOfModifier(int level){
        hullTechnology.setLevel(level);

        assertThat(hullTechnology.getModifier()).isIn(1.0, 1.1, 1.2);
    }

}