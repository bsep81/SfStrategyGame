package model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Builder;
import lombok.Data;
import model.buildings.Alloyworks;
import model.buildings.MetalMine;
import model.buildings.Shipyard;

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


    public void produceResources() {
        metal += metalMine.produceResources();
        alloys += alloyworks.produceResources();
        metalProperty.set(metal.toString());
        alloysProperty.setValue(alloys.toString());
    }
}
