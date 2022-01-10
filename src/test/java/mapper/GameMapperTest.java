package mapper;

import model.Game;
import model.spaceShips.SpaceShip;
import model.spaceShips.SpaceShipFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.gameSaving.GamePOJO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

class GameMapperTest {

    private final GameMapper mapper = new GameMapper();
    private final Game game = Game.getInstance();
    private final GamePOJO gamePOJO = new GamePOJO();
    private final SpaceShipFactory factory = new SpaceShipFactory();


    @BeforeEach
    void setup(){

        Queue<Character> characters = new LinkedList<>();
        characters.add('C');
        characters.add('F');

        gamePOJO.setTurn(10);
        gamePOJO.setMetal(30000);
        gamePOJO.setAlloys(40000);
        gamePOJO.setMetalMineLevel(2);
        gamePOJO.setAlloyworksLevel(3);
        gamePOJO.setShipyardLevel(1);
        gamePOJO.setLaboratoryLevel(2);
        gamePOJO.setFightersCount(2);
        gamePOJO.setCruisersCount(1);
        gamePOJO.setDestroyersCount(1);
        gamePOJO.setBombersCount(1);
        gamePOJO.setHullTechnologyLevel(2);
        gamePOJO.setShieldTechnologyLevel(2);
        gamePOJO.setAttackTechnologyLevel(2);
        gamePOJO.setMiningTechnologyLevel(3);
        gamePOJO.setAlloysTechnologyLevel(3);
        gamePOJO.setSpaceShipsProductionQueue(characters);
        gamePOJO.setCurrentProduction('D');
        gamePOJO.setProductionProgress(7);

    }

    @Test
    void shouldMapGameToGamePOJO(){

        Queue<SpaceShip> spaceShipsProductionQueue = new LinkedList<>();
        spaceShipsProductionQueue.add(factory.createCruiser());
        spaceShipsProductionQueue.add(factory.createFighter());

        game.setTurn(10);
        game.getColony().setMetal(30000);
        game.getColony().setAlloys(40000);
        game.getColony().getMetalMine().setLevel(2);
        game.getColony().getAlloyworks().setLevel(3);
        game.getColony().getShipyard().setLevel(1);
        game.getColony().getLaboratory().setLevel(2);
        game.getColony().setSpaceShips(List.of(factory.createFighter(), factory.createFighter(), factory.createCruiser(), factory.createDestroyer(), factory.createBomber()));
        game.getTechnologies().getHullTechnology().setLevel(2);
        game.getTechnologies().getShieldTechnology().setLevel(2);
        game.getTechnologies().getAttackTechnology().setLevel(2);
        game.getTechnologies().getMiningTechnology().setLevel(3);
        game.getTechnologies().getAlloysTechnology().setLevel(3);
        game.getColony().getShipyard().setSpaceShipsProductionQueue(spaceShipsProductionQueue);
        game.getColony().getShipyard().setCurrentProduction(factory.createDestroyer());
        game.getColony().getShipyard().setProductionProgress(7);

        GamePOJO result = mapper.mapGameToPOJO();

        assertThat(result).isEqualTo(gamePOJO);
    }

    @Test
    void shouldMapGamePOJOToGame(){

        Queue<SpaceShip> spaceShipsProductionQueue = new LinkedList<>();
        spaceShipsProductionQueue.add(factory.createCruiser());
        spaceShipsProductionQueue.add(factory.createFighter());

        List<SpaceShip> spaceShips = List.of(factory.createFighter(),
                factory.createFighter(), factory.createCruiser(), factory.createDestroyer(), factory.createBomber());

        SpaceShip production = factory.createDestroyer();

        game.setTurn(1);
        game.getColony().setMetal(0);
        game.getColony().setAlloys(0);
        game.getColony().getMetalMine().setLevel(0);
        game.getColony().getAlloyworks().setLevel(0);
        game.getColony().getShipyard().setLevel(0);
        game.getColony().getLaboratory().setLevel(0);
        game.getColony().setSpaceShips(new ArrayList<>());
        game.getTechnologies().getHullTechnology().setLevel(0);
        game.getTechnologies().getShieldTechnology().setLevel(0);
        game.getTechnologies().getAttackTechnology().setLevel(0);
        game.getTechnologies().getMiningTechnology().setLevel(0);
        game.getTechnologies().getAlloysTechnology().setLevel(0);
        game.getColony().getShipyard().setSpaceShipsProductionQueue(new LinkedList<>());
        game.getColony().getShipyard().setCurrentProduction(null);
        game.getColony().getShipyard().setProductionProgress(0);

        mapper.mapPOJOToGame(gamePOJO);

        assertThat(game.getTurn()).isEqualTo(10);
        assertThat(game.getColony().getMetal()).isEqualTo(30000);
        assertThat(game.getColony().getAlloys()).isEqualTo(40000);
        assertThat(game.getColony().getMetalMine().getLevel()).isEqualTo(2);
        assertThat(game.getColony().getAlloyworks().getLevel()).isEqualTo(3);
        assertThat(game.getColony().getShipyard().getLevel()).isEqualTo(1);
        assertThat(game.getColony().getLaboratory().getLevel()).isEqualTo(2);
        assertThat(game.getColony().getSpaceShips()).isEqualTo(spaceShips);
        assertThat(game.getTechnologies().getAttackTechnology().getLevel()).isEqualTo(2);
        assertThat(game.getTechnologies().getHullTechnology().getLevel()).isEqualTo(2);
        assertThat(game.getTechnologies().getShieldTechnology().getLevel()).isEqualTo(2);
        assertThat(game.getTechnologies().getMiningTechnology().getLevel()).isEqualTo(3);
        assertThat(game.getTechnologies().getAlloysTechnology().getLevel()).isEqualTo(3);
        assertThat(game.getColony().getShipyard().getSpaceShipsProductionQueue()).isEqualTo(spaceShipsProductionQueue);
        assertThat(game.getColony().getShipyard().getCurrentProduction()).isEqualTo(production);
        assertThat(game.getColony().getShipyard().getProductionProgress()).isEqualTo(7);

    }



}