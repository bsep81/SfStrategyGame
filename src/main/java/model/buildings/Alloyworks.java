package model.buildings;

import lombok.Data;

@Data
public class Alloyworks implements ProductionBuilding{

    private int level = 0;
    private static final int INITIAL_METAL_COST = 500;
    private static final int INITIAL_ALLOYS_COST = 500;
    private static final int BASE_PRODUCTION = 90;

    @Override
    public int produceResources() {
        if(level == 0) {
            return 0;
        }
        return (int) (BASE_PRODUCTION * Math.pow(1.5, level));
    }

    @Override
    public int upgradeMetalCost() {
        return (int) (INITIAL_METAL_COST * Math.pow(2, level));
    }

    @Override
    public int upgradeAlloysCost() {
        return (int) (INITIAL_ALLOYS_COST * Math.pow(2, level));
    }

    @Override
    public void upgrade() {
        level++;
    }
}
