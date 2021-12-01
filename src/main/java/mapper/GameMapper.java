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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        gamePOJO.setSpaceShipsProductionQueue(spaceShipQueueToCharacterQueue(game.getColony().getShipyard().getSpaceShipsProductionQueue()));
        gamePOJO.setCurrentProduction(spaceShipToCharacter(game.getColony().getShipyard().getCurrentProduction()));
        gamePOJO.setProductionProgress(game.getColony().getShipyard().getProductionProgress());

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
        game.getColony().getShipyard().setSpaceShipsProductionQueue(characterQueueToSpaceShipQueue(gamePOJO.getSpaceShipsProductionQueue()));
        game.getColony().getShipyard().setCurrentProduction(characterToSpaceShip(gamePOJO.getCurrentProduction()));
        game.getColony().getShipyard().setProductionProgress(gamePOJO.getProductionProgress());
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

    private Character spaceShipToCharacter(SpaceShip spaceShip){
        if(spaceShip != null){
            return spaceShip.getClass().getSimpleName().charAt(0);
        }
        return null;
    }

    private Queue<Character> spaceShipQueueToCharacterQueue(Queue<SpaceShip> spaceShips){
        Queue<Character> characters = new LinkedList<>();
        spaceShips.forEach(spaceShip -> characters.add(spaceShipToCharacter(spaceShip)));
        return characters;
    }

    private SpaceShip characterToSpaceShip(Character character){
        SpaceShipFactory factory = new SpaceShipFactory();
        if(character == null){
            return null;
        }
        return switch (character) {
            case 'F' -> factory.createFighter();
            case 'C' -> factory.createCruiser();
            case 'D' -> factory.createDestroyer();
            case 'B' -> factory.createBomber();
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }

    private Queue<SpaceShip> characterQueueToSpaceShipQueue(Queue<Character> characters){
        Queue<SpaceShip> spaceShips = new LinkedList<>();
        if(characters == null){
            return null;
        }
        characters.forEach(character -> spaceShips.add(characterToSpaceShip(character)));
        return spaceShips;
    }


}
