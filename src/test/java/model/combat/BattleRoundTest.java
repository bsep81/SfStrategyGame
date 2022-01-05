package model.combat;

import model.spaceShips.SpaceShip;
import model.spaceShips.SpaceShipFactory;
import model.technologies.AttackTechnology;
import model.technologies.HullTechnology;
import model.technologies.ShieldTechnology;
import model.technologies.Technologies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.combat.BattleService;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BattleRoundTest {

    @Mock
    private Random random;

    @InjectMocks
    private BattleRound battleRound;

    private final BattleService battleService = new BattleService();

    private final SpaceShipFactory factory = new SpaceShipFactory();

    private final Technologies technologies = Technologies.builder()
            .attackTechnology(new AttackTechnology(0,0))
            .shieldTechnology(new ShieldTechnology(0,0))
            .hullTechnology(new HullTechnology(0,0))
            .build();

    private Fleet defendingFleet;
    private Fleet attackingFleet;




    @BeforeEach
    void setUp(){
        technologies.getAttackTechnology().setLevel(2);
        technologies.getShieldTechnology().setLevel(1);
        technologies.getHullTechnology().setLevel(0);

        List<SpaceShip> defendingSpaceShips = List.of(factory.createFighter(),
                factory.createCruiser(),
                factory.createDestroyer(),
                factory.createBomber());

        List<SpaceShip> attackingSpaceShips = List.of(factory.createFighter(),
                factory.createFighter(),
                factory.createFighter());

        defendingFleet = new Fleet(defendingSpaceShips, technologies);
        attackingFleet = new Fleet(attackingSpaceShips, technologies);

        battleService.resetShields(attackingFleet, defendingFleet);
        battleService.prepareShips(attackingFleet, defendingFleet);
    }

    @Test
    void shouldReturnBattleRoundRaport(){


        String raport = """
                ROUND 1

                Attacker shot 3 times, destroying 0 ships.
                Defender shot 4 times, destroying 1 ships.

                Attacker\t\t\t\t\tDefender
                2\t\tFIGHTER\t\t\t1
                0\t\tCRUISER\t\t\t1
                0\t\tDESTROYER\t\t1
                0\t\tBOMBER\t\t\t1
                """;
        int defendingFleetSize = defendingFleet.getSpaceShips().size();
        int attackingFleetSize = attackingFleet.getSpaceShips().size();

        when(random.nextInt(defendingFleetSize)).thenReturn(1);
        when(random.nextInt(attackingFleetSize)).thenReturn(0);


        String result = battleRound.runNextRound(attackingFleet, defendingFleet);


        assertThat(result).isEqualTo(raport);
    }


}