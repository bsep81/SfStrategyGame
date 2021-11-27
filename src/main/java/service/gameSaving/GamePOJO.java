package service.gameSaving;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.spaceShips.SpaceShip;

import java.util.List;

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
    private List<SpaceShip> spaceShips;
    private int hullTechnologyLevel;
    private int shieldTechnologyLevel;
    private int attackTechnologyLevel;
    private int miningTechnologyLevel;
    private int alloysTechnologyLevel;
}
