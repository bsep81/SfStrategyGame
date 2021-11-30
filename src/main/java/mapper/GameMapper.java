package mapper;

import model.Game;
import model.spaceShips.Bomber;
import model.spaceShips.Cruiser;
import model.spaceShips.Destroyer;
import model.spaceShips.Fighter;
import model.spaceShips.SpaceShip;
import model.spaceShips.SpaceShipFactory;
import service.gameSaving.GamePOJO;

import java.util.ArrayList;
import java.util.List;

public class GameMapper {

    private final Game game = Game.getInstance();

    public GamePOJO mapGameToPOJO() {
        GamePOJO gamePOJO = new GamePOJO();

        gamePOJO.setTurn(game.getTurn());
        gamePOJO.setMetal(game.getColony().getMetal());
        gamePOJO.setAlloys(game.getColony().getAlloys());
        gamePOJO.setMetalMineLevel(game.getColony().getMetalMine().getLevel());
        gamePOJO.setAlloyworksLevel(game.getColony().getAlloyworks().getLevel());
        gamePOJO.setShipyardLevel(game.getColony().getShipyard().getLevel());
        gamePOJO.setLaboratoryLevel(game.getColony().getLaboratory().getLevel());
        gamePOJO.setFightersCount(game.getColony().getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Fighter.class)).count());
        gamePOJO.setCruisersCount(game.getColony().getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Cruiser.class)).count());
        gamePOJO.setDestroyersCount(game.getColony().getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Destroyer.class)).count());
        gamePOJO.setBombersCount(game.getColony().getSpaceShips().stream().filter(spaceShip -> spaceShip.getClass().equals(Bomber.class)).count());
        gamePOJO.setHullTechnologyLevel(game.getTechnologies().getHullTechnology().getLevel());
        gamePOJO.setShieldTechnologyLevel(game.getTechnologies().getShieldTechnology().getLevel());
        gamePOJO.setAttackTechnologyLevel(game.getTechnologies().getAttackTechnology().getLevel());
        gamePOJO.setMiningTechnologyLevel(game.getTechnologies().getMiningTechnology().getLevel());
        gamePOJO.setAlloysTechnologyLevel(game.getTechnologies().getAlloysTechnology().getLevel());

        return gamePOJO;
    }

    public void mapPOJOToGame(GamePOJO gamePOJO) {
        game.setTurn(gamePOJO.getTurn());
        game.getColony().setMetal(gamePOJO.getMetal());
        game.getColony().setAlloys(gamePOJO.getAlloys());
        game.getColony().getMetalMine().setLevel(gamePOJO.getMetalMineLevel());
        game.getColony().getAlloyworks().setLevel(gamePOJO.getAlloyworksLevel());
        game.getColony().getShipyard().setLevel(gamePOJO.getShipyardLevel());
        game.getColony().getLaboratory().setLevel(gamePOJO.getLaboratoryLevel());
        game.getColony().setSpaceShips(mapSpaceShips(gamePOJO));
        game.getTechnologies().getHullTechnology().setLevel(gamePOJO.getHullTechnologyLevel());
        game.getTechnologies().getShieldTechnology().setLevel(gamePOJO.getShieldTechnologyLevel());
        game.getTechnologies().getAttackTechnology().setLevel(gamePOJO.getAttackTechnologyLevel());
        game.getTechnologies().getMiningTechnology().setLevel(gamePOJO.getMiningTechnologyLevel());
        game.getTechnologies().getAlloysTechnology().setLevel(gamePOJO.getAlloysTechnologyLevel());
    }

    private List<SpaceShip> mapSpaceShips(GamePOJO gamePOJO){
        List<SpaceShip> spaceShips = new ArrayList<>();
        SpaceShipFactory factory = new SpaceShipFactory();

        for(int i = 0; i < gamePOJO.getFightersCount(); i++){
            spaceShips.add(factory.createFighter());
        }

        for(int i = 0; i < gamePOJO.getCruisersCount(); i++){
            spaceShips.add(factory.createCruiser());
        }

        for(int i = 0; i < gamePOJO.getDestroyersCount(); i++){
            spaceShips.add(factory.createDestroyer());
        }

        for(int i = 0; i < gamePOJO.getBombersCount(); i++){
            spaceShips.add(factory.createBomber());
        }

        return spaceShips;
    }
}
