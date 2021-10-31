package model.technologies;

import javafx.beans.property.SimpleStringProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class HullTechnology extends Technology{

    private SimpleStringProperty levelProperty = new SimpleStringProperty("HULL TECHNOLOGY level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");


    public HullTechnology(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }

    @Override
    public void upgrade() {
            level++;
            levelProperty.set("HULL TECHNOLOGY level " + level);
            costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");

    }
}
