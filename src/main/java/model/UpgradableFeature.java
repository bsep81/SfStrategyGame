package model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public abstract class UpgradableFeature {

    protected int level = 0;
    protected final int INITIAL_METAL_COST;
    protected final int INITIAL_ALLOYS_COST;
    protected Game game = Game.getInstance();



    public int upgradeMetalCost(){
        return (int) (INITIAL_METAL_COST * Math.pow(2, level));
    }

    public int upgradeAlloysCost(){
        return (int) (INITIAL_ALLOYS_COST * Math.pow(2, level));
    }

    public boolean canAffordUpgrade(Integer metal, Integer alloys){
        return upgradeMetalCost() <= metal && upgradeAlloysCost() <= alloys;
    }

    public void setLevel(int newLevel){
        level = newLevel;
        updateProperties();
    }

    public abstract void upgrade();
    public abstract void updateProperties();
}
