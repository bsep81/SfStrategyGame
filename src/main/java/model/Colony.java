package model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Builder;
import lombok.Data;
import model.buildings.Alloyworks;
import model.buildings.MetalMine;

@Data
@Builder
public class Colony {

    private int metal;
    private int alloys;
    private final SimpleStringProperty metalProperty = new SimpleStringProperty();
    private final SimpleStringProperty alloysProperty = new SimpleStringProperty();
    private MetalMine metalMine;
    private Alloyworks alloyworks;


    public void produceResources() {
        metal += metalMine.produceResources();
        alloys += alloyworks.produceResources();
        metalProperty.set("" + metal);
        alloysProperty.setValue("" + alloys);
    }
}
