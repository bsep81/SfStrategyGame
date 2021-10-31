package model.buildings;


import model.UpgradableFeature;


public abstract class Building extends UpgradableFeature {


    protected Building(int initialMetelCost, int initialAlloysCost) {
        super(initialMetelCost, initialAlloysCost);
    }
}
