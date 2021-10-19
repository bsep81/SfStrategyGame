package model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Data;
import model.buildings.Alloyworks;
import model.buildings.MetalMine;
import model.buildings.Shipyard;

import java.util.ArrayList;

@Data
public class Game {

    private static final Game INSTANCE = new Game();
    private Integer turn = 0;
    private IntegerProperty turnProperty = new SimpleIntegerProperty();

    private Game(){};

    public static Game getInstance(){
        return INSTANCE;
    }


    private final Colony colony = Colony.builder()
            .metal(1500)
            .alloys(500)
            .metalMine(new MetalMine(500, 0))
            .alloyworks(new Alloyworks(1000, 500))
            .shipyard(new Shipyard(5000, 5000))
            .spaceShips(new ArrayList<>())
            .build();


}
