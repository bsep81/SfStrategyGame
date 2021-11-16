package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;
import model.buildings.Alloyworks;
import model.buildings.Laboratory;
import model.buildings.MetalMine;
import model.buildings.Shipyard;
import model.technologies.AlloysTechnology;
import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.MiningTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;

import java.util.ArrayList;

@Data
public class Game {

    private static final Game INSTANCE = new Game();
    private Game(){}
    public static Game getInstance(){

        return INSTANCE;
    }

    private Integer turn = 1;
    private StringProperty turnProperty = new SimpleStringProperty("TURN - 1");

    private Colony colony = resetColony();

    private final Technologies technologies = Technologies.builder()
            .hullTechnology(new HullTechnology(5000, 0))
            .shieldTechnology(new ShieldTechnology(2500, 5000))
            .attackTechnology(new AttackTechnology(7500, 2500))
            .miningTechnology(new MiningTechnology(10000, 20000))
            .alloysTechnology(new AlloysTechnology(20000, 10000))
            .build();

    public Colony resetColony(){

        turn = 1;

        return Colony.builder()
                .metal(1500)
                .alloys(500)
                .metalProperty(new SimpleStringProperty("METAL - 1500"))
                .alloysProperty(new SimpleStringProperty("ALLOYS - 500"))
                .metalMine(new MetalMine(500, 0))
                .alloyworks(new Alloyworks(1000, 500))
                .shipyard(new Shipyard(5000, 5000))
                .laboratory(new Laboratory(10000, 10000))
                .spaceShips(new ArrayList<>())
                .build();
    }



}
