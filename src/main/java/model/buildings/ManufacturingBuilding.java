package model.buildings;


import java.util.List;

public interface ManufacturingBuilding {

    int BASE_PRODUCTION_POINTS = 5;
    int getMaxManufacturingPoints();

    <E> List<E> manufacture();




}
