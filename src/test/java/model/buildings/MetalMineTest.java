package model.buildings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MetalMineTest {

    MetalMine metalMine = new MetalMine(500, 0);

    @Test
    void shouldReturnProductionPerTurnWhenLevelEquals0(){
        int result = metalMine.currentProduction();

        assertThat(result).isZero();
    }

    @Test
    void shouldReturnProductionPerTurnWhenLevelHigherThan0(){
        metalMine.setLevel(3);

        int result = metalMine.currentProduction();

        assertThat(result).isEqualTo(409);
    }

    @Test
    void shouldReturnUpgradeMetalCost(){
        metalMine.setLevel(5);
        int result = metalMine.upgradeMetalCost();

        assertThat(result).isEqualTo(16000);
    }

    @Test
    void shouldReturnUpgradeAlloysCost(){
        metalMine.setLevel(7);
        int result = metalMine.upgradeAlloysCost();

        assertThat(result).isZero();
    }

    @Test
    void shouldIncrementMetalMineLevelAndSetProperties(){
        metalMine.setLevel(4);
        metalMine.upgrade();

        assertThat(metalMine.getLevel()).isEqualTo(5);
        assertThat(metalMine.getLevelProperty().get()).isEqualTo("Metal mine level 5");
        assertThat(metalMine.getCostProperty().get()).isEqualTo("Upgrade cost: 16000 metal, 0 alloys");
        assertThat(metalMine.getProductionProperty().get()).isEqualTo("Metal production: 1048");
    }
}