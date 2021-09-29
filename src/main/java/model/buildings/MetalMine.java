package model.buildings;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetalMine implements ProductionBuilding{

    int level;
    final int initialMetalCost;
    final int initialAlloysCost;
    final int baseProduction;

    @Override
    public int produceResources() {
        if(level == 0) {
            return 0;
        }
        return (int) (baseProduction * Math.pow(1.6, level));
    }

    @Override
    public int upgradeMetalCost() {
        return (int) (initialMetalCost * Math.pow(2, level));
    }

    @Override
    public int upgradeAlloysCost() {
        return (int) (initialAlloysCost * Math.pow(2, level));
    }
}
