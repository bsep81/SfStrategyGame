package mapper;

import model.Game;
import service.gameSaving.GamePOJO;

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
        gamePOJO.setSpaceShips(game.getColony().getSpaceShips());
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
        game.getColony().setSpaceShips(gamePOJO.getSpaceShips());
        game.getTechnologies().getHullTechnology().setLevel(gamePOJO.getHullTechnologyLevel());
        game.getTechnologies().getShieldTechnology().setLevel(gamePOJO.getShieldTechnologyLevel());
        game.getTechnologies().getAttackTechnology().setLevel(gamePOJO.getAttackTechnologyLevel());
        game.getTechnologies().getMiningTechnology().setLevel(gamePOJO.getMiningTechnologyLevel());
        game.getTechnologies().getAlloysTechnology().setLevel(gamePOJO.getAlloysTechnologyLevel());
    }
}
