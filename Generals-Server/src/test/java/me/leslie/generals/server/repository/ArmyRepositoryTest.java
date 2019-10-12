package me.leslie.generals.server.repository;

import me.leslie.generals.core.CombatRange;
import me.leslie.generals.core.MovementSpeed;
import me.leslie.generals.core.Vector2D;
import me.leslie.generals.core.ViewDistance;
import me.leslie.generals.core.entity.Army;
import me.leslie.generals.core.entity.Troop;
import me.leslie.generals.server.persistance.DataBase;
import me.leslie.generals.server.repository.exception.CreationFailedException;
import org.jooq.lambda.Seq;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static me.leslie.generals.server.repository.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArmyRepositoryTest {
    private DataBase database;
    private TroopRepository troopRepository;
    private ArmyRepository armyRepository;


    @BeforeEach
    void setup() {
        try {
            database = DataBase.get();
            troopRepository = new TroopRepository(database);
            armyRepository = new ArmyRepository(database, troopRepository);
        } catch (Exception e) {
            fail(e);
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            database.getConnection().prepareStatement("DELETE FROM ARMY");
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void createArmyWithoutTroops() {
        Troop t = troopRepository.createTroop(Troop.builder()
                .currentHealth(100)
                .maxHealth(120)
                .position(new Vector2D(120.2, 11.2))
                .movementSpeed(new MovementSpeed(12.0, 13.0, 6245.0))
                .combatRange(new CombatRange(1534.0, 1364.0))
                .viewDistance(new ViewDistance(121235.3, 125.3, 51.3)));
        assertThrows(CreationFailedException.class, () -> armyRepository.createArmy(Army.builder().hq(t).troops(List.of())));
    }

    @Test
    void hqIsOneOfTheTroops() {
        Troop t = troopRepository.createTroop(Troop.builder()
                .currentHealth(100)
                .maxHealth(120)
                .position(new Vector2D(120.2, 11.2))
                .movementSpeed(new MovementSpeed(12.0, 13.0, 6245.0))
                .combatRange(new CombatRange(1534.0, 1364.0))
                .viewDistance(new ViewDistance(121235.3, 125.3, 51.3)));

        assertThrows(CreationFailedException.class, () -> armyRepository.createArmy(Army.builder().hq(t).troops(List.of(t))));
    }

    @Test
    void createArmyAndGetArmy() {
        List<Troop> initializedTroops = initializeTroops(troopRepository);
        Army localCreated = Army.builder().hq(initializedTroops.get(0)).troops(initializedTroops.subList(2, initializedTroops.size())).build();
        Army repoCreated = armyRepository.createArmy(localCreated.copy());
        Army fetched = armyRepository.get(localCreated.getID());

        assertTrue(deepEquality(localCreated, repoCreated));
        assertTrue(deepEquality(localCreated, fetched));
    }

    @Test
    void deleteArmy() {
        List<Army> armies = initializeArmies(armyRepository);
        Army toDelete = armies.get(0);
        armyRepository.deleteArmy(armies.get(0));
        List<Army> fetched = armyRepository.get();

        assertEquals(armies.size() - 1, fetched.size());
        assertFalse(fetched.contains(toDelete));
    }

    @Test
    void troopInTwoArmies() {
        List<Troop> troops = initializeTroops(troopRepository);
        armyRepository.createArmy(Army.builder().hq(troops.get(0)).troops(troops.subList(1, 2)));

        assertThrows(CreationFailedException.class, () -> armyRepository.createArmy(Army.builder().hq(troops.get(troops.size() - 1)).troops(troops.subList(1, 2))));
    }

    @Test
    void deleteTroupFromArmy() {
        List<Army> armies = initializeArmies(armyRepository);
        Troop toRemove = armies.get(1).getTroops().get(0);
        Army fetched = armyRepository.removeFromArmy(armies.get(1), List.of(toRemove));
        assertFalse(fetched.getTroops().contains(toRemove));
        assertFalse(armyRepository.get(armies.get(1).getID()).getTroops().contains(toRemove));
        assertNotNull(troopRepository.getTroop(toRemove.getID()));
    }

    @Test
    void addTroupToArmy() {
        List<Army> armies = initializeArmies(armyRepository);
        Troop toAdd = troopRepository.createTroop(Troop.builder()
                .currentHealth(1010)
                .maxHealth(12560)
                .position(new Vector2D(12620.2, 1751.2))
                .movementSpeed(new MovementSpeed(12.0, 13.0, 6245.0))
                .combatRange(new CombatRange(1751534.0, 1364.0))
                .viewDistance(new ViewDistance(12168235.3, 12557.3, 5186.3)));

        Army initial = armies.get(0);
        Army created = armyRepository.addToArmy(initial, List.of(toAdd));
        Army fetched = armyRepository.get(initial.getID());

        assertTrue(deepEquality(created, fetched));
        assertTrue(created.getTroops().contains(toAdd));
    }

    @Test
    void testGetCertainTroops() {
        List<Army> armies = initializeArmies(armyRepository);
        List<Army> allFetched = armyRepository.get(armies.stream().map(Army::getID).collect(Collectors.toList()));
        List<Army> onlyFirst = armyRepository.get(List.of(armies.get(0).getID()));
        List<Army> onlySecond = armyRepository.get(List.of(armies.get(1).getID()));

        assertTrue(Seq.ofType(armies.stream().sorted(Comparator.comparingInt(x -> (int) x.getID())), Army.class)
                .zip(allFetched.stream().sorted(Comparator.comparingInt(x -> (int) x.getID())))
                .map(x -> deepEquality(x.v1(), x.v2()))
                .reduce(true, (x, y) -> x && y));

        assertTrue(deepEquality(onlyFirst.get(0), armies.get(0)));
        assertTrue(deepEquality(onlySecond.get(0), armies.get(1)));
    }

    @Test
    void updateTroops() {
        List<Army> armies = initializeArmies(armyRepository);
        Troop notRemoved = armies.get(1).getTroops().get(0);
        Troop removed = armies.get(1).getTroops().get(1);
        Army toUpdate = armies.get(1).copy().troops(List.of(notRemoved)).build();
        Army created = armyRepository.updateArmy(toUpdate);
        Army fetched = armyRepository.get(toUpdate.getID());

        assertTrue(deepEquality(toUpdate, created));
        assertTrue(deepEquality(toUpdate, fetched));

        assertTrue(deepEquality(troopRepository.getTroop(removed.getID()), removed));

        toUpdate = toUpdate.copy().troops(List.of(removed, notRemoved)).build();
        created = armyRepository.updateArmy(toUpdate);
        fetched = armyRepository.get(toUpdate.getID());

        assertTrue(deepEquality(toUpdate, created));
        assertTrue(deepEquality(toUpdate, fetched));

        assertTrue(deepEquality(troopRepository.getTroop(removed.getID()), removed));
    }
}
