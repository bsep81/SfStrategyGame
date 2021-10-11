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
    void shouldReturnListOfShips(){
        shipyard.level = 7;
        shipyard.getSpaceShipsProductionQueue().add(factory.createFighter());
        shipyard.getSpaceShipsProductionQueue().add(factory.createCruiser());

        shipyard.getSpaceShipsProductionQueue().add(factory.createFighter());
        shipyard.getSpaceShipsProductionQueue().add(factory.createFighter());

        List<SpaceShip> results = shipyard.manufacture();

        assertThat(results).isNotEmpty()
                .hasSize(3);


    }

}