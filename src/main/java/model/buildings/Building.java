package model.buildings;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public abstract class Building {

    protected int level = 0;
    protected final int INITIAL_METAL_COST;
    protected final int INITIAL_ALLOYS_COST;



    public int upgradeMetalCost(){
        return (int) (INITIAL_METAL_COST * Math.pow(2, level));
    }
    public int upgradeAlloysCost(){
        return (int) (INITIAL_ALLOYS_COST * Math.pow(2, level));
    }
    public abstract void upgrade();

}
