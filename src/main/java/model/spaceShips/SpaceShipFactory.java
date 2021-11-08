package model.spaceShips;

import java.util.List;

public class SpaceShipFactory {

    private int figterConter = 0;
    private int cruiserConter = 0;
    private int destroyerConter = 0;
    private int bomberConter = 0;
    private String serialNumber;


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
        figterConter++;
        serialNumber = "FTR" + figterConter;
        return new Fighter(findParametersByKey(SpaceShipType.FIGHTER), serialNumber);
    }

    public SpaceShip createCruiser(){
        cruiserConter++;
        serialNumber = "CRU" + cruiserConter;
        return new Cruiser(findParametersByKey(SpaceShipType.CRUISER),serialNumber);
    }

    public SpaceShip createDestroyer(){
        destroyerConter++;
        serialNumber = "DST" + destroyerConter;
        return new Destroyer(findParametersByKey(SpaceShipType.DESTROYER), serialNumber);
    }

    public SpaceShip createBomber(){
        bomberConter++;
        serialNumber = "BMB" + bomberConter;
        return new Bomber(findParametersByKey(SpaceShipType.BOMBER), serialNumber);
    }



}
