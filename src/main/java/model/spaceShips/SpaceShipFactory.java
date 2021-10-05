package model.spaceShips;

import java.util.List;

public class SpaceShipFactory {

    private static final List<SpaceShipBaseParameters> SPACE_SHIP_BASE_PARAMETERS = List.of(
            new SpaceShipBaseParameters(SpaceShipType.FIGHTER, 50, 10, 40),
            new SpaceShipBaseParameters(SpaceShipType.CRUISER, 200, 20, 70),
            new SpaceShipBaseParameters(SpaceShipType.DESTROYER, 300, 30, 150),
            new SpaceShipBaseParameters(SpaceShipType.BOMBER, 250, 20, 160)
    );

    private static SpaceShipBaseParameters findParametersByKey(final SpaceShipType key) {
        return SPACE_SHIP_BASE_PARAMETERS.stream()
                .filter(parameters -> parameters.getType().equals(key))
                .findFirst()
                .orElseThrow();
    }

    public SpaceShip createFighter(){
        return new Fighter(findParametersByKey(SpaceShipType.FIGHTER));
    }

    public SpaceShip createCruiser(){
        return new Cruiser(findParametersByKey(SpaceShipType.CRUISER));
    }

    public SpaceShip createDestroyer(){
        return new Destroyer(findParametersByKey(SpaceShipType.DESTROYER));
    }

    public SpaceShip createBomber(){
        return new Bomber(findParametersByKey(SpaceShipType.BOMBER));
    }


}
