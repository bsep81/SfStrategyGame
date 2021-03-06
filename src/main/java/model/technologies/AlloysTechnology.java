package model.technologies;

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
public class AlloysTechnology extends Technology{

    public static final String DESCRIPTION = "Every level of alloys technology increases alloyworks production";
    private SimpleStringProperty levelProperty = new SimpleStringProperty("ALLOYS TECHNOLOGY level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");


    public AlloysTechnology(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }

    @Override
    public double getModifier() {
        return Math.pow(1.16, level);
    }

    @Override
    public void upgrade() {
        level++;
        updateProperties();
    }

    @Override
    public void updateProperties() {
        levelProperty.set("ALLOYS TECHNOLOGY level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");
        Game.getInstance().getColony().getAlloyworks().getProductionProperty().set("Alloys production: " + Game.getInstance().getColony().getAlloyworks().currentProduction());
    }
}