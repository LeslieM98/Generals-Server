package me.leslie.generals.repository;

import me.leslie.generals.exception.data.DeletionFailedException;
import me.leslie.generals.exception.data.FetchFailedException;
import me.leslie.generals.game.entity.Troop;
import me.leslie.generals.game.entity.Troop.TroopBuilder;
import me.leslie.generals.game.repository.TroopRepository;
import me.leslie.generals.utility.*;
import org.jooq.lambda.Seq;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TroopRepositoryTest {
    DataBase database;
    TroopRepository repository;

    @BeforeEach
    void setup() {
        try {
            database = DataBase.get();
            repository = new TroopRepository(database);
        } catch (Exception e) {
            fail(e);
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            database.getConnection().close();
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void insertingAndQueryingCorrectData() {
        TroopBuilder tb = Troop.builder()
                .currentHealth(100)
                .maxHealth(120)
                .position(new Vector2D(120.2, 11.2))
                .movementSpeed(new MovementSpeed(12.0, 13.0, 6245.0))
                .combatRange(new CombatRange(1534.0, 1364.0))
                .viewDistance(new ViewDistance(121235.3, 125.3, 51.3));

        Troop localTroop = tb.id(1).build();
        Troop createdTroop = repository.createTroop(tb);
        Troop queriedTroop = repository.getTroop(createdTroop.getID());

        assertTrue(Utils.deepEquality(localTroop, createdTroop));
        assertTrue(Utils.deepEquality(localTroop, queriedTroop));
    }

    @Test
    void getAllData() {
        List<Troop> created = Utils.initializeTroops(repository);
        List<Troop> queried = repository.getTroops();
        assertEquals(created.size(), queried.size());
        Seq.ofType(created.stream().sorted((x, y) -> (int) (x.getID() - y.getID())), Troop.class)
                .zip(queried.stream().sorted((x, y) -> (int) (x.getID() - y.getID())))
                .forEach(x -> assertTrue(Utils.deepEquality(x.v1(), x.v2())));
    }

    @Test
    void deleteData() {
        List<Troop> initialData = Utils.initializeTroops(repository);
        Troop deleted = initialData.get(3);
        repository.deleteTroop(deleted.getID());
        List<Troop> allQueried = repository.getTroops();

        assertEquals(initialData.size() - 1, allQueried.size());
        assertFalse(allQueried.contains(deleted));
    }

    @Test
    void getSingle() {
        List<Troop> initialData = Utils.initializeTroops(repository);
        Troop toQuery = initialData.get(3);
        Troop recieved = repository.getTroop(toQuery.getID());
        List<Troop> allData = repository.getTroops();

        assertTrue(Utils.deepEquality(toQuery, recieved));
        allData.contains(toQuery);
    }

    @Test
    void getUnknownTroop() {
        try {
            repository.getTroop(111);
        } catch (FetchFailedException e) {
            assertTrue(e.getMessage().toLowerCase().contains("troop"));
        }
    }

    @Test
    void updateTroup() {
        TroopBuilder tb = Troop.builder()
                .currentHealth(100)
                .maxHealth(100)
                .position(new Vector2D(1.0, 1.0))
                .movementSpeed(new MovementSpeed(1.0, 2.0, 3.0))
                .combatRange(new CombatRange(1.0, 2.0))
                .viewDistance(new ViewDistance(1.0, 2.0, 1.0));

        Troop initial = repository.createTroop(tb);
        Troop updated = repository.updateTroop(initial.copy()
                .currentHealth(90)
                .maxHealth(110)
                .position(new Vector2D(2.0, 3.0))
                .movementSpeed(new MovementSpeed(2.0, 3.0, 4.0))
                .combatRange(new CombatRange(2.0, 3.0))
                .viewDistance(new ViewDistance(2.0, 3.0, 4.0))
                .build());

        List<Troop> queried = repository.getTroops();

        assertEquals(1, queried.size());
        assertEquals(initial, updated);
        assertFalse(Utils.deepEquality(initial, updated));
        assertTrue(Utils.deepEquality(updated, queried.get(0)));
    }

    @Test
    void getNonExistingTroop() {
        try {
            repository.getTroop(444);
        } catch (FetchFailedException e) {
            if (!e.getMessage().toLowerCase().contains("troop")) {
                fail(e);
            }
        }
    }

    @Test
    void deleteUnknownTroop() {
        try {
            repository.deleteTroop(444);
        } catch (DeletionFailedException e) {
            if (!e.getMessage().toLowerCase().contains("troop")) {
                fail(e);
            }
        }
    }

    @Test
    void updateUnknownTroop() {
        repository.updateTroop(Troop.builder()
                .id(200)
                .currentHealth(90)
                .maxHealth(110)
                .position(new Vector2D(2.0, 3.0))
                .movementSpeed(new MovementSpeed(2.0, 3.0, 4.0))
                .combatRange(new CombatRange(2.0, 3.0))
                .viewDistance(new ViewDistance(2.0, 3.0, 4.0))
                .build());
    }

    @Test
    void getSomeTroops() {
        List<Troop> initialized = Utils.initializeTroops(repository);
        List<Troop> fetched = repository.getTroops(List.of(1L, 2L, 3L));
        Troop t1 = Troop.builder()
                .id(1L)
                .currentHealth(100)
                .maxHealth(120)
                .position(new Vector2D(120.2, 11.2))
                .movementSpeed(new MovementSpeed(12.0, 13.0, 6245.0))
                .combatRange(new CombatRange(1534.0, 1364.0))
                .viewDistance(new ViewDistance(121235.3, 125.3, 51.3)).build();

        Troop t2 = Troop.builder()
                .id(2L)
                .currentHealth(1)
                .maxHealth(1240)
                .position(new Vector2D(15320.2, 13441.2))
                .movementSpeed(new MovementSpeed(1762.0, 13.0, 6245.0))
                .combatRange(new CombatRange(1538634.0, 1364.0))
                .viewDistance(new ViewDistance(12125635.3, 125.3, 5451.3)).build();

        Troop t3 = Troop.builder()
                .id(3L)
                .currentHealth(16700)
                .maxHealth(15620)
                .position(new Vector2D(12820.2, 1561.2))
                .movementSpeed(new MovementSpeed(15672.0, 13.0, 686245.0))
                .combatRange(new CombatRange(1534.0, 1364.0))
                .viewDistance(new ViewDistance(12121735.3, 12705.3, 5184.3)).build();

        assertEquals(3, fetched.size());
        assertTrue(Utils.deepEquality(t1, fetched.get(0)));
        assertTrue(Utils.deepEquality(t2, fetched.get(1)));
        assertTrue(Utils.deepEquality(t3, fetched.get(2)));

        assertTrue(initialized.containsAll(fetched));

    }
}
