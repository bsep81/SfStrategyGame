package model.spaceShips;

import lombok.Data;
import model.technologies.Technologies;

@Data
public abstract class SpaceShip {

    protected Integer currentHullPoints;
    protected Integer currentShieldPoints;
    protected String serialNumber;

    protected SpaceShipBaseParameters baseParameters;

    protected SpaceShip(SpaceShipBaseParameters baseParameters, String serialNumber) {
        this.baseParameters = baseParameters;
        this.serialNumber = serialNumber;
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




}
