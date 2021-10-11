package model.buildings;

import javafx.beans.property.SimpleStringProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Alloyworks extends Building implements ResourceBuilding {

    private SimpleStringProperty levelProperty = new SimpleStringProperty("Alloyworks level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
    private SimpleStringProperty productionProperty = new SimpleStringProperty("Alloys production: " + currentProduction());

    private static final int BASE_PRODUCTION = 90;

    public Alloyworks(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }


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
    public void upgrade() {
        level++;
        levelProperty.set("Alloyworks level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
        productionProperty.set("Alloys production: " + currentProduction());
    }
}
