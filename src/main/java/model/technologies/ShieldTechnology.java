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
public class ShieldTechnology extends Technology{

    public static final String DESCRIPTION = "Every level of shield technology increases shield power of your space ships by 10%";
    private SimpleStringProperty levelProperty = new SimpleStringProperty("Shield technology level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");


    public ShieldTechnology(int initialMetalCost, int initialAlloysCost) {
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
        levelProperty.set("SHIELD TECHNOLOGY level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
    }
}
