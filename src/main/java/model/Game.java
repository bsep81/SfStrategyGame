package model;


import lombok.Data;
import model.buildings.Alloyworks;
import model.buildings.MetalMine;
import model.buildings.Shipyard;

@Data
public class Game {

    private static final Game INSTANCE = new Game();

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
            .build();


}
