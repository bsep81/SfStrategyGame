package model.spaceShips;

import lombok.Data;

@Data
public class SpaceShipBaseParameters {

    private SpaceShipType type;
    private Integer hullPoints;
    private Integer shieldPoints;
    private Integer attackPower;


    public SpaceShipBaseParameters(SpaceShipType spaceShipType, Integer hullPoints, Integer shieldPoints, Integer attackPower) {
        this.type = spaceShipType;
        this.hullPoints = hullPoints;
        this.shieldPoints = shieldPoints;
        this.attackPower = attackPower;
    }
}
