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
public class Bomber extends SpaceShip {


    private static final Integer PRODUCTION_POINTS = 75;
    public static final Integer METAL_COST = 85000;
    public static final Integer ALLOYS_COST = 30000;
    private Game game = Game.getInstance();

    public Bomber(SpaceShipBaseParameters baseParameters, String serialNumber) {
        super(baseParameters, serialNumber);

    }

    @Override
    public Integer getProductionPoints() {
        return PRODUCTION_POINTS;
    }

    @Override
    public String getInfo(Technologies technologies) {
        return "BOMBER" +
                "\nMetal - " +
                METAL_COST +
                "\nAlloys - " +
                ALLOYS_COST +
                "\n\n hull - " +
                getMaxHull(technologies) +
                "\nshield - " +
                getShield(technologies) +
                "\nattack - " +
                getFirePower(technologies);
    }

    @Override
    public SpaceShip attack(SpaceShip target, Technologies technologies) {
        double damageMultiplier = 1;
        if(target.getClass().equals(Fighter.class)){
            damageMultiplier = 0.5;
        }
        int damage = (int)(damageMultiplier * getFirePower(technologies));

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
