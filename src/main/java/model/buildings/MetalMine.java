package model.buildings;



import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data
public class MetalMine implements ProductionBuilding{

    private int level = 0;
    private SimpleStringProperty levelProperty = new SimpleStringProperty("Metal mine level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
    private SimpleStringProperty productionProperty = new SimpleStringProperty("Metal production: " + currentProduction());
    private static final int INITIAL_METAL_COST = 500;
    private static final int INITIAL_ALLOYS_COST = 0;
    private static final int BASE_PRODUCTION = 100;


    public int currentProduction(){
        if(level == 0) {
            return 0;
        }
        return (int) (BASE_PRODUCTION * Math.pow(1.6, level));
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
        levelProperty.set("Metal mine level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
        productionProperty.set("Metal production: " + currentProduction());
    }
}
