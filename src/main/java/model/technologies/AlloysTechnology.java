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
public class AlloysTechnology extends Technology{

    public static final String DESCRIPTION = "Every level of alloys technology increases metal mine production";
    private SimpleStringProperty levelProperty = new SimpleStringProperty("ALLOYS TECHNOLOGY level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");


    public AlloysTechnology(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }

    @Override
    public double getModifier() {
        return Math.pow(1.1, level);
    }

    @Override
    public void upgrade() {
        level++;
        levelProperty.set("ALLOYS TECHNOLOGY level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
    }
}