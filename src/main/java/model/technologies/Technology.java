package model.technologies;

import model.Game;
import model.UpgradableFeature;

public abstract class Technology extends UpgradableFeature {




    public Technology(int initialMetalCost, int initialAlloysCost) {
        super(initialMetalCost, initialAlloysCost);
    }


}
