package model.technologies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class AttackTechnologyTest {
    private final AttackTechnology attackTechnology = new AttackTechnology(10000, 10000);

    @Test
    void shouldReturnUpgradeMetalCost(){
        attackTechnology.setLevel(5);
        int result = attackTechnology.upgradeMetalCost();

        assertThat(result).isEqualTo(320000);
    }

    @Test
    void shouldReturnUpgradeAlloysCost(){
        attackTechnology.setLevel(7);
        int result = attackTechnology.upgradeAlloysCost();

        assertThat(result).isEqualTo(1280000);
    }

    @Test
    void shouldIncrementAttackTechnologyLevelAndSetProperties(){
        attackTechnology.setLevel(4);
        attackTechnology.upgrade();

        assertThat(attackTechnology.getLevel()).isEqualTo(5);
        assertThat(attackTechnology.getLevelProperty().get()).isEqualTo("ATTACK TECHNOLOGY level 5");
        assertThat(attackTechnology.getCostProperty().get()).isEqualTo("Upgrade cost: 320000 metal, 320000 alloys");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void shouldReturnCorresctValueOfModifier(int level){
        attackTechnology.setLevel(level);

        assertThat(attackTechnology.getModifier()).isIn(1.0, 1.1, 1.2);
    }

}