package model.spaceShips;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpaceShipFactoryTest {

    private final SpaceShipFactory factory = new SpaceShipFactory();

    @Test
    void shouldReturnNewFighter(){
        SpaceShip fighter = new Fighter(new SpaceShipBaseParameters(SpaceShipType.FIGHTER, 50, 10, 40));

        SpaceShip result = factory.createFighter();

        assertThat(result).isEqualTo(fighter);
    }

    @Test
    void shouldReturnNewCruiser(){
        SpaceShip cruiser = new Cruiser(new SpaceShipBaseParameters(SpaceShipType.CRUISER, 200, 20, 70));

        SpaceShip result = factory.createCruiser();

        assertThat(result).isEqualTo(cruiser);
    }

    @Test
    void shouldReturnNewDestroyer(){
        SpaceShip destroyer = new Destroyer(new SpaceShipBaseParameters(SpaceShipType.DESTROYER, 300, 30, 150));

        SpaceShip result = factory.createDestroyer();

        assertThat(result).isEqualTo(destroyer);
    }

    @Test
    void shouldReturnNewBomber(){
        SpaceShip bomber = new Bomber(new SpaceShipBaseParameters(SpaceShipType.BOMBER, 250, 20, 160));

        SpaceShip result = factory.createBomber();

        assertThat(result).isEqualTo(bomber);
    }

}