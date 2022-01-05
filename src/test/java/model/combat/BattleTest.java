package model.combat;

import model.Game;
import model.spaceShips.SpaceShip;
import model.spaceShips.SpaceShipFactory;
import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.combat.BattleService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BattleTest {

    private final BattleService battleService = new BattleService();

    private final SpaceShipFactory factory = new SpaceShipFactory();

    private final Technologies technologies = Technologies.builder()
            .attackTechnology(new AttackTechnology(0,0))
            .shieldTechnology(new ShieldTechnology(0,0))
            .hullTechnology(new HullTechnology(0,0))
            .build();

    private Battle battle;

    @BeforeEach
    void setUp(){
        technologies.getAttackTechnology().setLevel(2);
        technologies.getShieldTechnology().setLevel(1);
        technologies.getHullTechnology().setLevel(0);

        List<SpaceShip> defendingSpaceShips = List.of(factory.createDestroyer(),
                factory.createDestroyer(),
                factory.createBomber(),
                factory.createBomber());

        List<SpaceShip> attackingSpaceShips = List.of(factory.createFighter());

        Fleet defendingFleet = new Fleet(defendingSpaceShips, technologies);
        Fleet attackingFleet = new Fleet(attackingSpaceShips, technologies);

        battle = new Battle(attackingFleet, defendingFleet);

        battleService.resetShields(battle.getAttackingFleet(), battle.getDefendingFleet());
        battleService.prepareShips(battle.getAttackingFleet(), battle.getDefendingFleet());



    }

    @Test
    void shouldReturnRaportStatingThatDefenderWonTheBattle(){

        String raport = """
                Attacker\t\t\t\t\tDefender
                1\t\tFIGHTER\t\t\t0
                0\t\tCRUISER\t\t\t0
                0\t\tDESTROYER\t\t2
                0\t\tBOMBER\t\t\t2
                ROUND 1

                Attacker shot 1 times, destroying 0 ships.
                Defender shot 4 times, destroying 1 ships.

                Attacker\t\t\t\t\tDefender
                0\t\tFIGHTER\t\t\t0
                0\t\tCRUISER\t\t\t0
                0\t\tDESTROYER\t\t2
                0\t\tBOMBER\t\t\t2
                Defender won with 4 ships left""";

        String result = battle.startCombat();

        assertThat(result).isEqualTo(raport);
        assertThat(battle.getDefendingFleet().getSpaceShips()).isEqualTo(Game.getInstance().getColony().getSpaceShips());
    }

}