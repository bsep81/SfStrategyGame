package model.buildings;

public interface ProductionBuilding extends Building{

    int currentProduction();
    int produceResources();
}
