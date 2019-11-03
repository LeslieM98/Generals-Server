package me.leslie.generals.server.repository;

import me.leslie.generals.core.entity.interfaces.IArmyComposition;
import me.leslie.generals.core.entity.interfaces.ITroop;
import me.leslie.generals.core.entity.pojos.ArmyComposition;
import me.leslie.generals.server.persistence.Database;
import me.leslie.generals.server.persistence.jooq.tables.daos.ArmyDao;
import me.leslie.generals.server.persistence.jooq.tables.daos.TroopDao;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ArmyRepositoryTest {
    private Database database;
    private TroopRepository troopRepository;
    private ArmyRepository armyRepository;


    @BeforeEach
    void setup() {
        try {
            database = Database.get();
            troopRepository = new TroopRepository(database);
            armyRepository = new ArmyRepository(database);
        } catch (Exception e) {
            fail(e);
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            ArmyDao armyJooq = new ArmyDao(new DefaultConfiguration().set(database.getConnection()).set(SQLDialect.SQLITE));
            armyJooq.delete(armyJooq.findAll());
            TroopDao troopJooq = new TroopDao(new DefaultConfiguration().set(database.getConnection()).set(SQLDialect.SQLITE));
            troopJooq.delete(troopJooq.findAll());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void createArmyAndGetArmy() {
        List<? extends ITroop> initializedTroops = Utils.initializeTroops(troopRepository);
        IArmyComposition localCreated = new ArmyComposition(initializedTroops.get(0), initializedTroops.subList(2, initializedTroops.size()));
        IArmyComposition repoCreated = armyRepository.updateRelation(localCreated);
        IArmyComposition fetched = armyRepository.get(localCreated.getHQ().getId()).get();

        assertEquals(localCreated, repoCreated);
        assertEquals(localCreated, fetched);
    }
}
/*

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
        assertNotNull(troopRepository.getTroop(toRemove.getId()));
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

        assertTrue(deepEquality(troopRepository.getTroop(removed.getId()), removed));

        toUpdate = toUpdate.copy().troops(List.of(removed, notRemoved)).build();
        created = armyRepository.updateArmy(toUpdate);
        fetched = armyRepository.get(toUpdate.getID());

        assertTrue(deepEquality(toUpdate, created));
        assertTrue(deepEquality(toUpdate, fetched));

        assertTrue(deepEquality(troopRepository.getTroop(removed.getId()), removed));
    }
}
*/
