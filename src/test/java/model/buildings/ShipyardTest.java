package model.buildings;


import model.spaceShips.SpaceShip;
import model.spaceShips.SpaceShipFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipyardTest {

    Shipyard shipyard = new Shipyard(20000, 10000);
    SpaceShipFactory factory = new SpaceShipFactory();

    @Test
    void shouldReturnUpgradeMetalCost(){
        shipyard.setLevel(5);
        int result = shipyard.upgradeMetalCost();

        assertThat(result).isEqualTo(640000);
    }

    @Test
    void shouldReturnUpgradeAlloysCost(){
        shipyard.setLevel(7);
        int result = shipyard.upgradeAlloysCost();

        assertThat(result).isEqualTo(1280000);
    }

    @Test
    void shouldReturnMaxManufacturingPoints(){
        shipyard.setLevel(8);

        int result = shipyard.getMaxManufacturingPoints();

        assertThat(result).isEqualTo(40);
    }

    @Test
    void shouldAddSpaceShipToQueue(){
        shipyard.addProduction(factory.createBomber());

        SpaceShip result = factory.createBomber();

        assertThat(shipyard.getSpaceShipsProductionQueue())
                .hasSize(1)
                .containsExactly(result);
    }

    @Test
    void shouldReturnListOfShips(){
        shipyard.setLevel(7);
        shipyard.getSpaceShipsProductionQueue().add(factory.createFighter());
        shipyard.getSpaceShipsProductionQueue().add(factory.createCruiser());
        shipyard.getSpaceShipsProductionQueue().add(factory.createFighter());
        shipyard.getSpaceShipsProductionQueue().add(factory.createFighter());

        List<SpaceShip> results = shipyard.manufacture();

        assertThat(results).isNotEmpty()
                .hasSize(3);
    }

    @Test
    void shouldIncrementShipyardLevelAndSetProperties(){
        shipyard.setLevel(4);
        shipyard.upgrade();

        assertThat(shipyard.getLevel()).isEqualTo(5);
        assertThat(shipyard.getLevelProperty().get()).isEqualTo("Shipyard level 5");
        assertThat(shipyard.getCostProperty().get()).isEqualTo("Upgrade cost: 640000 metal, 320000 alloys");

    }

}