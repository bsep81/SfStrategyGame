package model.combat;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.spaceShips.SpaceShip;
import model.technologies.Technologies;

import java.util.List;

@Data
@AllArgsConstructor
public class Fleet {

    private List<SpaceShip> spaceShips;
    private Technologies technologies;


}
