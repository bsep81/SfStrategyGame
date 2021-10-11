package model.spaceShips;

import lombok.Data;

import java.util.PrimitiveIterator;

@Data
public class Destroyer implements SpaceShip {

    private SpaceShipBaseParameters baseParameters;
    private Integer hullPoints;
    private final Integer PRODUCTION_POINTS = 60;

    public Destroyer(SpaceShipBaseParameters baseParameters) {
        this.baseParameters = baseParameters;
    }

    @Override
    public Integer getFirePower() {
        return null;
    }

    @Override
    public Integer getShield() {
        return null;
    }

    @Override
    public Integer getProductionPoints() {
        return PRODUCTION_POINTS;
    }


}
