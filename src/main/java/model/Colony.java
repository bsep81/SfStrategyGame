package model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Builder;
import lombok.Data;
import model.buildings.Alloyworks;
import model.buildings.MetalMine;
import model.buildings.Shipyard;
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
}
