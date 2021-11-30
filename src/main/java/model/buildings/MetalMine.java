package model.buildings;


import javafx.beans.property.SimpleStringProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Game;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class MetalMine extends Building implements ResourceBuilding {

    private SimpleStringProperty levelProperty = new SimpleStringProperty("Metal mine level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
    private SimpleStringProperty productionProperty = new SimpleStringProperty("Metal production: " + currentProduction());
    private static final int BASE_PRODUCTION = 100;

    public MetalMine(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }


    public int currentProduction(){
        if(level == 0) {
            return 0;
        }
        return (int) (BASE_PRODUCTION * Math.pow(1.6, level) * Game.getInstance().getTechnologies().getMiningTechnology().getModifier());
    }


    @Override
    public int produceResources() {

        return currentProduction();
    }


    @Override
    public void upgrade() {
        level++;
        updateProperties();
    }

    @Override
    public void updateProperties() {
        levelProperty.set("Metal mine level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
        productionProperty.set("Metal production: " + currentProduction());
    }
}
