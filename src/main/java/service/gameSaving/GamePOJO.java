package service.gameSaving;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePOJO {



    private int turn;
    private int metal;
    private int alloys;
    private int metalMineLevel;
    private int alloyworksLevel;
    private int shipyardLevel;
    private int laboratoryLevel;
    private long fightersCount;
    private long cruisersCount;
    private long destroyersCount;
    private long bombersCount;
    private int hullTechnologyLevel;
    private int shieldTechnologyLevel;
    private int attackTechnologyLevel;
    private int miningTechnologyLevel;
    private int alloysTechnologyLevel;

    //TODO: Spaceship production queue
}
