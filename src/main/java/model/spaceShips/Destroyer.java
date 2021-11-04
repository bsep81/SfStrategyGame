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
public class Destroyer extends SpaceShip {

    private static final Integer PRODUCTION_POINTS = 60;
    public static final Integer METAL_COST = 60000;
    public static final Integer ALLOYS_COST = 15000;
    private Game game = Game.getInstance();

    public Destroyer(SpaceShipBaseParameters baseParameters) {
        super(baseParameters);

    }

    @Override
    public Integer getProductionPoints() {
        return PRODUCTION_POINTS;
    }

    @Override
    public String getInfo(Technologies technologies) {
        return "DESTROYER" +
                "\n\n hull - " +
                getMaxHull(technologies) +
                "\n\nshield - " +
                getShield(technologies) +
                "\n\nattack - " +
                getFirePower(technologies);
    }

    @Override
    public SpaceShip attack(SpaceShip target) {
        double damageMultiplier = 1;
        if(target.getClass().equals(Cruiser.class)){
            damageMultiplier = 1.3;
        }
        int damage = (int)(damageMultiplier * getFirePower(game.getTechnologies()));

        if(damage <= currentShieldPoints){
            currentShieldPoints -= damage;
        }else{
            currentHullPoints -= damage - currentShieldPoints;
            currentShieldPoints = 0;
        }

        return target;
    }

}
