package model.spaceShips;

import lombok.Data;

@Data
public class Fighter implements SpaceShip{

    private SpaceShipBaseParameters baseParameters;
    private Integer hullPoints;

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
}
