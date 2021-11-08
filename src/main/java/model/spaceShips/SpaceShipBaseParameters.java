package model.spaceShips;

import lombok.Data;
import model.technologies.Technologies;

@Data
public class SpaceShipBaseParameters {

    private SpaceShipType type;
    private Integer baseHullPoints;
    private Integer baseShieldPoints;
    private Integer baseAttackPower;



    public SpaceShipBaseParameters(SpaceShipType spaceShipType, Integer baseHullPoints, Integer baseShieldPoints, Integer baseAttackPower) {
        this.type = spaceShipType;
        this.baseHullPoints = baseHullPoints;
        this.baseShieldPoints = baseShieldPoints;
        this.baseAttackPower = baseAttackPower;
    }

    public int getHullPoints(Technologies technologies){
        return (int) (baseHullPoints * technologies.getHullTechnology().getModifier());
    }

    public int getShieldPoints(Technologies technologies){
        return (int) (baseShieldPoints * technologies.getShieldTechnology().getModifier());
    }

    public int getAttackPower(Technologies technologies){
        return (int) (baseAttackPower * technologies.getAttackTechnology().getModifier());
    }
}
