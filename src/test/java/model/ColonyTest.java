package model;

import javafx.beans.property.SimpleStringProperty;
import model.buildings.Alloyworks;
import model.buildings.MetalMine;
import model.buildings.Shipyard;
import model.spaceShips.SpaceShipFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ColonyTest {

    private final Colony colony = Colony.builder()
            .metal(1500)
            .alloys(500)
            .metalProperty(new SimpleStringProperty("METAL - 1500"))
            .alloysProperty(new SimpleStringProperty("ALLOYS - 500"))
            .metalMine(new MetalMine(500, 0))
            .alloyworks(new Alloyworks(1000, 500))
            .shipyard(new Shipyard(5000, 5000))
            .spaceShips(new ArrayList<>())
            .build();

    @Test
    void shouldSetMetalProperty(){
        colony.setMetal(12345);

        colony.setMetalProperty();

        assertThat(colony.getMetalProperty().get()).isEqualTo(("METAL - 12345"));
    }

    @Test
    void shouldSetAlloysProperty(){
        colony.setAlloys(54321);

        colony.setAlloysProperty();

        assertThat(colony.getAlloysProperty().get()).isEqualTo(("ALLOYS - 54321"));
    }

    @Test
    void shouldSubtractMetal(){
        colony.setMetal(10000);

        colony.payMetal(2500);

        assertThat(colony.getMetal()).isEqualTo(7500);
    }

    @Test
    void shouldSubtractAlloys(){
        colony.setAlloys(5000);

        colony.payAlloys(2000);

        assertThat(colony.getAlloys()).isEqualTo(3000);
    }

    @Test
    void shoulIncreaseValuesOfMetalAndAlloys(){
        colony.setMetal(10000);
        colony.setAlloys(7500);
        colony.getMetalMine().setLevel(3);
        colony.getAlloyworks().setLevel(4);

        colony.produceResources();

        assertThat(colony.getMetal()).isEqualTo(10409);
        assertThat(colony.getAlloys()).isEqualTo(7955);
    }

    @Test
    void shouldReturnInformationAboutFleet(){
        SpaceShipFactory factory = new SpaceShipFactory();
        colony.getSpaceShips().add(factory.createFighter());
        colony.getSpaceShips().add(factory.createFighter());
        colony.getSpaceShips().add(factory.createFighter());
        colony.getSpaceShips().add(factory.createCruiser());
        colony.getSpaceShips().add(factory.createDestroyer());
        colony.getSpaceShips().add(factory.createBomber());

        String result = colony.getFleetInfo();
        String info = "FIGHTER   - 3\n\nCRUISER   - 1\n\nDESTROYER - 1\n\nBOMBER    - 1";

        assertThat(result).isEqualTo(info);
    }
}