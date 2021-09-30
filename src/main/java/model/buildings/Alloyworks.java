package model.buildings;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Alloyworks implements ProductionBuilding{

    int level;
    final int initialMetalCost;
    final int initialAlloysCost;
    final int baseProduction;

    @Override
    public int produceResources() {
        if(level == 0) {
            return 0;
        }
        return (int) (baseProduction * Math.pow(1.5, level));
    }

    @Override
    public int upgradeMetalCost() {
        return (int) (initialMetalCost * Math.pow(2, level));
    }

    @Override
    public int upgradeAlloysCost() {
        return (int) (initialAlloysCost * Math.pow(2, level));
    }

    @Override
    public void upgrade() {
        level++;
    }
}
