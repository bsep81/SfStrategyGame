package model.buildings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LaboratoryTest {

    Laboratory laboratory = new Laboratory(10000, 10000);

    @Test
    void shouldReturnUpgradeMetalCost(){
        laboratory.setLevel(3);
        int result = laboratory.upgradeMetalCost();

        assertThat(result).isEqualTo(80000);
    }

    @Test
    void shouldReturnUpgradeAlloysCost(){
        laboratory.setLevel(4);
        int result = laboratory.upgradeAlloysCost();

        assertThat(result).isEqualTo(160000);
    }

    @Test
    void shouldIncrementLaboratoryLevelAndSetProperties(){
        laboratory.setLevel(4);
        laboratory.upgrade();

        assertThat(laboratory.getLevel()).isEqualTo(5);
        assertThat(laboratory.getLevelProperty().get()).isEqualTo("Laboratory level 5");
        assertThat(laboratory.getCostProperty().get()).isEqualTo("Upgrade cost: 320000 metal, 320000 alloys");
    }

}