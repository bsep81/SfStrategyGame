package model.spaceShips;

import lombok.Data;

@Data
public class Bomber implements SpaceShip {

    private SpaceShipBaseParameters baseParameters;
    private Integer hullPoints;

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
}
