package model.combat;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.spaceShips.SpaceShip;
import model.spaceShips.SpaceShipFactory;
import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BattleCreator {

    private final Integer turn;
    private final Fleet defendingFleet;
    private SpaceShipFactory factory = new SpaceShipFactory();


    public Battle createBattle(){

        return new Battle(generateFleet(), defendingFleet);
    }

    private Fleet generateFleet(){
        Technologies technologies = Technologies.builder()
                .hullTechnology(new HullTechnology(0,0))
                .shieldTechnology(new ShieldTechnology(0,0))
                .attackTechnology(new AttackTechnology(0,0))
                .build();

        technologies.getHullTechnology().setLevel(turn / 100);
        technologies.getShieldTechnology().setLevel(turn / 100);
        technologies.getAttackTechnology().setLevel(turn / 100);

        List<SpaceShip> spaceShips = new ArrayList<>();
        for (int i = 1; i <= turn; i++) {
            if(i % 10 == 0){
                spaceShips.add(factory.createFighter());
            }
            if(i % 66 == 0){
                spaceShips.add(factory.createCruiser());
            }
            if(i % 125 == 0){
                spaceShips.add(factory.createDestroyer());
            }
            if(i % 225 == 0 ){
                spaceShips.add(factory.createBomber());
            }
        }


        return new Fleet(spaceShips, technologies);
    }

}
