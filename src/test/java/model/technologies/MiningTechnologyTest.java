package model.technologies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class MiningTechnologyTest {

    private final MiningTechnology miningTechnology = new MiningTechnology(10000, 10000);

    @Test
    void shouldReturnUpgradeMetalCost(){
        miningTechnology.setLevel(5);
        int result = miningTechnology.upgradeMetalCost();

        assertThat(result).isEqualTo(320000);
    }

    @Test
    void shouldReturnUpgradeAlloysCost(){
        miningTechnology.setLevel(7);
        int result = miningTechnology.upgradeAlloysCost();

        assertThat(result).isEqualTo(1280000);
    }

    @Test
    void shouldIncrementAlloysTechnologyLevelAndSetProperties(){
        miningTechnology.setLevel(4);
        miningTechnology.upgrade();

        assertThat(miningTechnology.getLevel()).isEqualTo(5);
        assertThat(miningTechnology.getLevelProperty().get()).isEqualTo("MINING TECHNOLOGY level 5");
        assertThat(miningTechnology.getCostProperty().get()).isEqualTo("Upgrade cost: 320000 metal, 320000 alloys");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void shouldReturnCorresctValueOfModifier(int level){
        miningTechnology.setLevel(level);

        assertThat(miningTechnology.getModifier()).isIn(1.0, 1.1);
    }

}