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

    public Cruiser(SpaceShipBaseParameters baseParameters) {
        super(baseParameters);

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
    public SpaceShip attack(SpaceShip target) {

        int damage = getFirePower(game.getTechnologies());

        if(damage <= currentShieldPoints){
            currentShieldPoints -= damage;
        }else{
            currentHullPoints -= damage - currentShieldPoints;
            currentShieldPoints = 0;
        }

        return target;
    }


}
