package model.spaceShips;

import lombok.Data;

@Data
public class Fighter implements SpaceShip{

    private SpaceShipBaseParameters baseParameters;
    private Integer hullPoints;
    private static final Integer PRODUCTION_POINTS = 4;
    public static final Integer METAL_COST = 1500;
    public static final Integer ALLOYS_COST = 500;

    public Fighter(SpaceShipBaseParameters baseParameters) {
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
