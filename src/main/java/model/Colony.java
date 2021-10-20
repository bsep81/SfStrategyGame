package model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Builder;
import lombok.Data;
import model.buildings.Alloyworks;
import model.buildings.MetalMine;
import model.buildings.Shipyard;
import model.spaceShips.Bomber;
import model.spaceShips.Cruiser;
import model.spaceShips.Destroyer;
import model.spaceShips.Fighter;
import model.spaceShips.SpaceShip;


import java.util.List;

@Data
@Builder
public class Colony {

    private Integer metal;
    private Integer alloys;
    private final SimpleStringProperty metalProperty = new SimpleStringProperty();
    private final SimpleStringProperty alloysProperty = new SimpleStringProperty();
    private MetalMine metalMine;
    private Alloyworks alloyworks;
    private Shipyard shipyard;
    private List<SpaceShip> spaceShips;


    public void produceResources() {
        metal += metalMine.produceResources();
        alloys += alloyworks.produceResources();
        setMetalProperty();
        setAlloysProperty();
    }

    public void payMetal(Integer metalCost){
        metal -= metalCost;
        setMetalProperty();
    }

    public void payAlloys(Integer alloysCost){
        alloys -= alloysCost;
        setAlloysProperty();
    }

    public void setMetalProperty(){
        metalProperty.set("METAL - " + metal.toString());
    }

    public void setAlloysProperty() {
        alloysProperty.set("ALLOYS - " + alloys.toString());
    }

    public String getFleetInfo(){

        StringBuilder sb = new StringBuilder();
        long fighterCount = spaceShips.stream().filter(spaceShip -> spaceShip.getClass().equals(Fighter.class)).count();
        long cruiserCount = spaceShips.stream().filter(spaceShip -> spaceShip.getClass().equals(Cruiser.class)).count();
        long destroyerCount = spaceShips.stream().filter(spaceShip -> spaceShip.getClass().equals(Destroyer.class)).count();
        long bomberCount = spaceShips.stream().filter(spaceShip -> spaceShip.getClass().equals(Bomber.class)).count();

        sb.append("FIGHTER   - ")
                .append(fighterCount)
                .append("\n\nCRUISER   - ")
                .append(cruiserCount)
                .append("\n\nDESTROYER - ")
                .append(destroyerCount)
                .append("\n\nBOMBER    - ")
                .append(bomberCount);




        return sb.toString();
    }
}
