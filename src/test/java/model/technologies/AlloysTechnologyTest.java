package model.technologies;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class AlloysTechnologyTest {

    AlloysTechnology alloysTechnology = new AlloysTechnology(20000, 10000);

    @Test
    void shouldReturnUpgradeMetalCost(){
        alloysTechnology.setLevel(5);
        int result = alloysTechnology.upgradeMetalCost();

        assertThat(result).isEqualTo(640000);
    }

    @Test
    void shouldReturnUpgradeAlloysCost(){
        alloysTechnology.setLevel(7);
        int result = alloysTechnology.upgradeAlloysCost();

        assertThat(result).isEqualTo(1280000);
    }

    @Test
    void shouldIncrementAlloysTechnologyLevelAndSetProperties(){
        alloysTechnology.setLevel(4);
        alloysTechnology.upgrade();

        assertThat(alloysTechnology.getLevel()).isEqualTo(5);
        assertThat(alloysTechnology.getLevelProperty().get()).isEqualTo("ALLOYS TECHNOLOGY level 5");
        assertThat(alloysTechnology.getCostProperty().get()).isEqualTo("Upgrade cost: 640000 metal, 320000 alloys");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void shouldReturnCorresctValueOfModifier(int level){
        alloysTechnology.setLevel(level);

        assertThat(alloysTechnology.getModifier()).isIn(1.0, 1.16, 1.3456);
    }

}