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
public class HullTechnology extends Technology {

    public static final String DESCRIPTION = "Every level of hull technology increases hull strength of your space ships by 10%";
    private SimpleStringProperty levelProperty = new SimpleStringProperty("HULL TECHNOLOGY level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");


    public HullTechnology(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }

    @Override
    public double getModifier() {
        return 1 + 0.1 * level;
    }

    @Override
    public void upgrade() {
        level++;
        updateProperties();
    }

    @Override
    public void updateProperties() {
        levelProperty.set("HULL TECHNOLOGY level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
    }
}
