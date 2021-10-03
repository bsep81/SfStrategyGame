package model.buildings;

import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data
public class Alloyworks implements ProductionBuilding{

    private int level = 0;
    private SimpleStringProperty levelProperty = new SimpleStringProperty("Alloyworks level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
    private SimpleStringProperty productionProperty = new SimpleStringProperty("Alloys production: " + currentProduction());
    private static final int INITIAL_METAL_COST = 500;
    private static final int INITIAL_ALLOYS_COST = 500;
    private static final int BASE_PRODUCTION = 90;

    @Override
    public int currentProduction() {
        if(level == 0) {
            return 0;
        }
        return (int) (BASE_PRODUCTION * Math.pow(1.5, level));
    }

    @Override
    public int produceResources() {
        return currentProduction();
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
        levelProperty.set("Alloyworks level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
        productionProperty.set("Alloys production: " + currentProduction());
    }
}
