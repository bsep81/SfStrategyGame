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
public class AttackTechnology extends Technology{

    public static final String DESCRIPTION = "Every level of attack technology increases attack power of your space ships by 10%";
    private SimpleStringProperty levelProperty = new SimpleStringProperty("ATTACK TECHNOLOGY level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");


    public AttackTechnology(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }

    @Override
    public double getModifier() {
        return 1 + 0.1 * level;
    }

    @Override
    public void upgrade() {
        level++;
        levelProperty.set("ATTACK TECHNOLOGY level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");

    }
}