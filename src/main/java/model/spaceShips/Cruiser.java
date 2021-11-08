package model.spaceShips;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Game;
import model.technologies.Technologies;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Cruiser extends SpaceShip {

    private static final Integer PRODUCTION_POINTS = 27;
    public static final Integer METAL_COST = 20000;
    public static final Integer ALLOYS_COST = 7000;
    private Game game = Game.getInstance();

    public Cruiser(SpaceShipBaseParameters baseParameters, String serialNumber) {
        super(baseParameters, serialNumber);

    }

    @Override
    public Integer getProductionPoints() {
        return PRODUCTION_POINTS;
    }

    @Override
    public String getInfo(Technologies technologies) {
        return "CRUISER" +
                "\n\n hull - " +
                getMaxHull(technologies) +
                "\n\nshield - " +
                getShield(technologies) +
                "\n\nattack - " +
                getFirePower(technologies);
    }

    @Override
    public SpaceShip attack(SpaceShip target, Technologies technologies) {

        int damage = getFirePower(technologies);

        if(damage <= target.currentShieldPoints){
            target.currentShieldPoints -= damage;
            if(target.currentShieldPoints < 0){
                target.currentShieldPoints = 0;
            }
        }else{
            target.currentHullPoints -= damage - target.currentShieldPoints;
            target.currentShieldPoints = 0;
        }

        return target;
    }


}
