package model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;
import model.buildings.Alloyworks;
import model.buildings.Laboratory;
import model.buildings.MetalMine;
import model.buildings.Shipyard;

import java.util.ArrayList;

@Data
public class Game {

    private static final Game INSTANCE = new Game();
    private Game(){}
    public static Game getInstance(){
        return INSTANCE;
    }

    private Integer turn = 0;
    private IntegerProperty turnProperty = new SimpleIntegerProperty();

    private final Colony colony = Colony.builder()
            .metal(1500000)
            .alloys(500000)
            .metalProperty(new SimpleStringProperty("METAL - 1500"))
            .alloysProperty(new SimpleStringProperty("ALLOYS - 500"))
            .metalMine(new MetalMine(500, 0))
            .alloyworks(new Alloyworks(1000, 500))
            .shipyard(new Shipyard(5000, 5000))
            .laboratory(new Laboratory(10000, 10000))
            .spaceShips(new ArrayList<>())
            .build();


}
