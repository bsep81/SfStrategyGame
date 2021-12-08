package model.spaceShips;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.technologies.Technologies;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Fighter extends SpaceShip {


    private static final Integer PRODUCTION_POINTS = 4;
    public static final Integer METAL_COST = 1500;
    public static final Integer ALLOYS_COST = 500;

    public Fighter(SpaceShipBaseParameters baseParameters) {
        super(baseParameters);

    }

    @Override
    public Integer getProductionPoints() {
        return PRODUCTION_POINTS;
    }

    @Override
    public String getInfo(Technologies technologies) {
        return "FIGHTER" +
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
        int damageMultiplier = 1;
        if(target.getClass().equals(Bomber.class)){
            damageMultiplier = 2;
        }
        int damage = damageMultiplier * getFirePower(technologies);

        return dealDamage(target, damage);
    }


}
