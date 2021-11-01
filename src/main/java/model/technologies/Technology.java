package model.technologies;

import model.UpgradableFeature;

public abstract class Technology extends UpgradableFeature {

    protected Technology(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }
    public abstract double getModifier();

}
