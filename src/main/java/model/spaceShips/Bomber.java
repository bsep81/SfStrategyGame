package model.spaceShips;

import lombok.Data;

@Data
public class Bomber implements SpaceShip {

    private SpaceShipBaseParameters baseParameters;
    private Integer hullPoints;
    private final Integer PRODUCTION_POINTS = 75;

    public Bomber(SpaceShipBaseParameters baseParameters) {
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
