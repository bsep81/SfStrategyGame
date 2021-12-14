package model.spaceShips;

import lombok.Data;
import model.technologies.Technologies;

@Data
public abstract class SpaceShip {

    protected Integer currentHullPoints;
    protected Integer currentShieldPoints;

    protected SpaceShipBaseParameters baseParameters;

    protected SpaceShip(SpaceShipBaseParameters baseParameters) {
        this.baseParameters = baseParameters;
    }

    public Integer getFirePower(Technologies technologies) {
        return baseParameters.getAttackPower(technologies);
    }

    public Integer getShield(Technologies technologies) {
        return baseParameters.getShieldPoints(technologies);
    }

    public Integer getMaxHull(Technologies technologies) {
        return this.baseParameters.getHullPoints(technologies);
    }

    public abstract Integer getProductionPoints();

    public abstract String getInfo(Technologies technologies);

    public abstract SpaceShip attack(SpaceShip target, Technologies technologies);

    protected static SpaceShip dealDamage(SpaceShip target, int damage) {
        if(damage <= target.currentShieldPoints){
            target.currentShieldPoints -= damage;
        }else{
            target.currentHullPoints -= damage - target.currentShieldPoints;
            target.currentShieldPoints = 0;
        }

        return target;
    }

}
