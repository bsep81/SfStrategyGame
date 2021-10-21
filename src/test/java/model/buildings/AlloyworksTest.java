package model.buildings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AlloyworksTest {

    Alloyworks alloyworks = new Alloyworks(1000, 500);

    @Test
    void shouldReturnProductionPerTurnWhenLevelEquals0(){
        int result = alloyworks.currentProduction();

        assertThat(result).isZero();
    }

    @Test
    void shouldReturnProductionPerTurnWhenLevelHigherThan0(){
        alloyworks.setLevel(3);

        int result = alloyworks.currentProduction();

        assertThat(result).isEqualTo(303);
    }

    @Test
    void shouldReturnUpgradeMetalCost(){
        alloyworks.setLevel(5);
        int result = alloyworks.upgradeMetalCost();

        assertThat(result).isEqualTo(32000);
    }

    @Test
    void shouldReturnUpgradeAlloysCost(){
        alloyworks.setLevel(7);
        int result = alloyworks.upgradeAlloysCost();

        assertThat(result).isEqualTo(64000);
    }

    @Test
    void shouldIncrementAlloyworksLevelAndSetProperties(){
        alloyworks.setLevel(4);
        alloyworks.upgrade();

        assertThat(alloyworks.getLevel()).isEqualTo(5);
        assertThat(alloyworks.getLevelProperty().get()).isEqualTo("Alloyworks level 5");
        assertThat(alloyworks.getCostProperty().get()).isEqualTo("Upgrade cost: 32000 metal, 16000 alloys");
        assertThat(alloyworks.getProductionProperty().get()).isEqualTo("Alloys production: 683");
    }



}