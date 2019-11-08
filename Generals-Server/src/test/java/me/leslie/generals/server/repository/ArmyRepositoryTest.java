package me.leslie.generals.server.repository;

import me.leslie.generals.core.entity.interfaces.IArmyComposition;
import me.leslie.generals.core.entity.interfaces.ITroop;
import me.leslie.generals.core.entity.pojos.ArmyComposition;
import me.leslie.generals.core.entity.pojos.Troop;
import me.leslie.generals.server.persistence.Database;
import me.leslie.generals.server.persistence.jooq.tables.daos.ArmyDao;
import me.leslie.generals.server.persistence.jooq.tables.daos.TroopDao;
import me.leslie.generals.server.repository.exception.UpdateFailedException;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.lambda.Seq;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static me.leslie.generals.server.repository.Utils.initializeArmies;
import static org.junit.jupiter.api.Assertions.*;

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
            ArmyDao armyJooq = new ArmyDao(
                    new DefaultConfiguration().set(database.getConnection()).set(SQLDialect.SQLITE));
            armyJooq.delete(armyJooq.findAll());
            TroopDao troopJooq = new TroopDao(
                    new DefaultConfiguration().set(database.getConnection()).set(SQLDialect.SQLITE));
            troopJooq.delete(troopJooq.findAll());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void createArmyAndGetArmy() {
        List<? extends ITroop> initializedTroops = Utils.initializeTroops(troopRepository);
        IArmyComposition localCreated = new ArmyComposition(initializedTroops.get(0),
                initializedTroops.subList(2, initializedTroops.size()));
        IArmyComposition repoCreated = armyRepository.updateRelation(localCreated);
        IArmyComposition fetched = armyRepository.get(localCreated.getHQ().getId()).get();

        assertEquals(localCreated, repoCreated);
        assertEquals(localCreated, fetched);
    }

    @Test
    void troopInTwoArmies() {
        List<? extends ITroop> troops =
                Utils.initializeTroops(troopRepository);
        armyRepository.updateRelation(new ArmyComposition(troops.get(0), troops.subList(1, 2)));

        assertThrows(UpdateFailedException.class, () -> armyRepository.updateRelation(new ArmyComposition(troops.get(troops.size() - 1), troops.subList(1, 2))));
    }

    @Test
    void deleteTroupFromArmy() {
        List<IArmyComposition> armies =
                initializeArmies(armyRepository);
        IArmyComposition initialArmy = armies.get(1);
        List<? extends ITroop> updatedTroops = new ArrayList<>(initialArmy.getTroops());
        ITroop toRemove = updatedTroops.iterator().next();
        updatedTroops.remove(toRemove);

        IArmyComposition toUpdate = new ArmyComposition(initialArmy.getHQ(), updatedTroops);

        IArmyComposition updated = armyRepository.updateRelation(toUpdate);
        Optional<IArmyComposition> fetched = armyRepository.get(initialArmy.getHQ().getId());

        assertNotEquals(toUpdate, initialArmy);
        assertTrue(fetched.isPresent());
        assertFalse(fetched.get().getTroops().contains(toRemove));
        assertEquals(toUpdate, fetched.get());
        assertEquals(toUpdate, updated);
    }

    @Test
    void addTroupToArmy() {
        List<IArmyComposition> armies = initializeArmies(armyRepository);
        ITroop toAdd = troopRepository.create(new Troop(0, 1010, 12560, 12620.2, 1751.2, 12.0, 13.0, 6245.0, 1751534.0, 1364.0, 12168235.3, 12557.3, 5186.3));

        IArmyComposition initialArmy = armies.get(0);
        List<ITroop> toUpdate = new ArrayList<>(initialArmy.getTroops());
        toUpdate.add(toAdd);

        IArmyComposition updated = armyRepository.updateRelation(new ArmyComposition(initialArmy.getHQ(), toUpdate));
        Optional<IArmyComposition> fetched = armyRepository.get(initialArmy.getHQ().getId());

        assertTrue(fetched.isPresent());
        assertNotEquals(initialArmy, updated);
        assertEquals(updated, fetched.get());
        assertTrue(updated.getTroops().contains(toAdd));
        assertTrue(updated.getTroops().containsAll(toUpdate));
    }

    @Test
    void testGetCertainTroops() {
        List<IArmyComposition> armies = initializeArmies(armyRepository);
        List<? extends IArmyComposition> allFetched = armyRepository.get(armies.stream().map(x -> x.getHQ().getId()).collect(Collectors.toList()));
        List<? extends IArmyComposition> onlyFirst = armyRepository.get(List.of(armies.get(0).getHQ().getId()));
        List<? extends IArmyComposition> onlySecond = armyRepository.get(List.of(armies.get(1).getHQ().getId()));

        assertTrue(Seq.ofType(armies.stream().sorted(Comparator.comparingInt(x -> x.getHQ().getId())), IArmyComposition.class)
                .zip(allFetched.stream().sorted(Comparator.comparingInt(x -> x.getHQ().getId())))
                .map(x -> x.v1().equals(x.v2()))
                .reduce(true, (x, y) -> x && y));

        assertEquals(onlyFirst.get(0), armies.get(0));
        assertEquals(onlySecond.get(0), armies.get(1));
    }

    @Test
    void updateTroops() {
        List<IArmyComposition> armies = initializeArmies(armyRepository);
        ITroop notRemoved = armies.get(1).getTroops().get(0);
        ITroop removed = armies.get(1).getTroops().get(1);
        IArmyComposition toUpdate = new ArmyComposition(armies.get(1).getHQ(), List.of(notRemoved));
        IArmyComposition created = armyRepository.updateRelation(toUpdate);
        Optional<IArmyComposition> fetched = armyRepository.get(toUpdate.getHQ().getId());


        assertTrue(fetched.isPresent());
        assertEquals(toUpdate, created);
        assertEquals(toUpdate, fetched.get());

        assertEquals(troopRepository.get(removed.getId()).get(), removed);

        toUpdate = new ArmyComposition(toUpdate);
        ((ArmyComposition) toUpdate).setTroops(List.of(removed, notRemoved));
        created = armyRepository.updateRelation(toUpdate);
        fetched = armyRepository.get(toUpdate.getHQ().getId());

        assertEquals(toUpdate, created);
        assertEquals(toUpdate, fetched.get());

        assertEquals(troopRepository.get(removed.getId()).get(), removed);
    }

    @Test
    void deleteSingleByComposition() {
        List<IArmyComposition> armies = initializeArmies(armyRepository);
        IArmyComposition deleted = armies.get(0);
        Optional<IArmyComposition> fetched = armyRepository.get(deleted.getHQ().getId());

        assertTrue(armyRepository.get().contains(deleted));
        assertTrue(fetched.isPresent());
        assertEquals(deleted, fetched.get());

        armyRepository.deleteRelations(deleted);
        fetched = armyRepository.get(deleted.getHQ().getId());

        assertTrue(fetched.isEmpty());
        assertFalse(armyRepository.get().contains(deleted));
    }

    @Test
    void deleteSingleById() {
        List<IArmyComposition> armies = initializeArmies(armyRepository);
        IArmyComposition deleted = armies.get(0);
        Optional<IArmyComposition> fetched = armyRepository.get(deleted.getHQ().getId());

        assertTrue(armyRepository.get().contains(deleted));
        assertTrue(fetched.isPresent());
        assertEquals(deleted, fetched.get());

        armyRepository.delete(deleted.getHQ().getId());
        fetched = armyRepository.get(deleted.getHQ().getId());

        assertTrue(fetched.isEmpty());
        assertFalse(armyRepository.get().contains(deleted));
    }

    @Test
    void deleteMultiple() {
        List<IArmyComposition> initialized = initializeArmies(armyRepository);
        List<? extends IArmyComposition> fetched = armyRepository.get();

        assertEquals(initialized.size(), fetched.size());
        assertTrue(fetched.containsAll(initialized));
        armyRepository.delete(initialized.stream().map(x -> x.getHQ().getId()).collect(Collectors.toList()));
        fetched = armyRepository.get();

        assertTrue(fetched.isEmpty());
    }


}

