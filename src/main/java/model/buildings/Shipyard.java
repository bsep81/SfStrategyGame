package model.buildings;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.spaceShips.SpaceShip;
import model.spaceShips.SpaceShipFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Shipyard extends Building implements ManufacturingBuilding {

    private SimpleStringProperty levelProperty = new SimpleStringProperty("Shipyard level " + level);
    private SimpleStringProperty costProperty = new SimpleStringProperty("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");

    private Queue<SpaceShip> spaceShipsProductionQueue = new LinkedList<>();
    private SpaceShip currentProduction;
    private SpaceShipFactory factory = new SpaceShipFactory();
    private Integer productionPointsLeft;
    private Integer productionProgress;
    private LongProperty shipsProducedProperty = new SimpleLongProperty();


    public Shipyard(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }


    @Override
    public void upgrade() {
        level++;
        levelProperty.set("Shipyard level " + level);
        costProperty.set("Upgrade cost: " + upgradeMetalCost() + " metal, " + upgradeAlloysCost() + " alloys");

    }

    @Override
    public int resetManufacturingPoints() {
        return BASE_PRODUCTION_POINTS * level;
    }

    public void addProduction(SpaceShip production) {
        spaceShipsProductionQueue.add(production);
    }

    @Override
    public List<SpaceShip> manufacture() {
        productionPointsLeft = resetManufacturingPoints();
        List<SpaceShip> producedShips = new ArrayList<>();
        if (currentProduction == null && !spaceShipsProductionQueue.isEmpty()) {
            currentProduction = spaceShipsProductionQueue.peek();
            productionProgress = 0;
        }
        while (productionPointsLeft > 0 && currentProduction != null) {
            if (productionPointsLeft >= currentProduction.getProductionPoints() - productionProgress) {
                producedShips.add(currentProduction);
                productionPointsLeft -= currentProduction.getProductionPoints() - productionProgress;
                spaceShipsProductionQueue.poll();
                shipsProducedProperty.set(shipsProducedProperty.get() + 1);
                currentProduction = spaceShipsProductionQueue.peek();
                productionProgress = 0;
            }else{
                productionProgress += productionPointsLeft;
                productionPointsLeft = 0;
            }

        }
        return producedShips;
    }


}
