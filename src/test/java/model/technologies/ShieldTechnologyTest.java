package model.technologies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ShieldTechnologyTest {

    ShieldTechnology shieldTechnology = new ShieldTechnology(10000, 10000);

    @Test
    void shouldReturnUpgradeMetalCost(){
        shieldTechnology.setLevel(5);
        int result = shieldTechnology.upgradeMetalCost();

        assertThat(result).isEqualTo(320000);
    }

    @Test
    void shouldReturnUpgradeAlloysCost(){
        shieldTechnology.setLevel(7);
        int result = shieldTechnology.upgradeAlloysCost();

        assertThat(result).isEqualTo(1280000);
    }

    @Test
    void shouldIncrementShieldTechnologyLevelAndSetProperties(){
        shieldTechnology.setLevel(4);
        shieldTechnology.upgrade();

        assertThat(shieldTechnology.getLevel()).isEqualTo(5);
        assertThat(shieldTechnology.getLevelProperty().get()).isEqualTo("SHIELD TECHNOLOGY level 5");
        assertThat(shieldTechnology.getCostProperty().get()).isEqualTo("Upgrade cost: 320000 metal, 320000 alloys");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void shouldReturnCorresctValueOfModifier(int level){
        shieldTechnology.setLevel(level);

        assertThat(shieldTechnology.getModifier()).isIn(1.0, 1.1, 1.2);
    }

}