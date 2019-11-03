package me.leslie.generals.server.repository;

import me.leslie.generals.core.entity.interfaces.ITroop;
import me.leslie.generals.core.entity.pojos.Troop;
import me.leslie.generals.server.persistence.Database;
import me.leslie.generals.server.persistence.jooq.tables.daos.TroopDao;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.lambda.Seq;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TroopRepositoryTest {


    private Database database;
    private TroopRepository repository;


    @BeforeEach
    void setup() {
        try {
            database = Database.get();
            repository = new TroopRepository(database);
        } catch (Exception e) {
            fail(e);
        }
    }

    @AfterEach
    void cleanUp() {
        try {

            TroopDao jooq = new TroopDao(new DefaultConfiguration().set(database.getConnection()).set(SQLDialect.SQLITE));
            jooq.delete(jooq.findAll());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void insertingAndQueryingCorrectData() {
        Troop localTroop = new Troop(0, 100, 120, 120.2, 11.2, 12.0, 13.0, 6245.0, 1534.0, 13644.0, 121235.3, 125.3, 51.3);

        ITroop createdTroop = repository.create(localTroop);
        localTroop = new Troop(localTroop);
        localTroop.setId(1);
        ITroop queriedTroop = repository.get(localTroop.getId()).orElse(null);

        assertEquals(localTroop, createdTroop);
        assertEquals(localTroop, queriedTroop);
    }

    @Test
    void getAllData() {
        List<ITroop> created = Utils.initializeTroops(repository);
        List<? extends ITroop> queried = repository.get();
        assertEquals(created.size(), queried.size());
        Seq.ofType(created.stream().sorted(Comparator.comparingInt(ITroop::getId)), ITroop.class)
                .zip(queried.stream().sorted(Comparator.comparingInt(ITroop::getId)))
                .forEach(x -> assertEquals(x.v1, x.v2));
    }

    @Test
    void deleteData() {
        List<ITroop> initialData = Utils.initializeTroops(repository);
        ITroop deleted = initialData.get(3);
        repository.delete(deleted.getId());
        List<? extends ITroop> allQueried = repository.get();

        assertEquals(initialData.size() - 1, allQueried.size());
        assertFalse(allQueried.contains(deleted));
    }

    @Test
    void getSingle() {
        List<ITroop> initialData = Utils.initializeTroops(repository);
        ITroop toQuery = initialData.get(3);
        Optional<ITroop> received = repository.get(toQuery.getId());
        List<? extends ITroop> allData = repository.get();

        assertEquals(toQuery, received.orElse(null));
        assertTrue(allData.contains(toQuery));
    }

    @Test
    void getUnknownTroop() {
        assertFalse(repository.get(111).isPresent());
    }

    @Test
    void updateTroop() {
        ITroop troop = new Troop(-200, 100, 100, 1.0, 1.0, 1.0, 2.0, 3.0, 1.0, 2.0, 1.0, 2.0, 1.0);

        ITroop initial = repository.create(troop);
        Troop copy = new Troop(initial);
        copy.setCurrentHealth(90);
        copy.setMaxHealth(110);
        copy.setPosX(2.0);
        copy.setPosY(3.0);
        copy.setNormalSpeed(2.0);
        copy.setStreetSpeed(3.0);
        copy.setDifficultTerrainSpeed(4.0);
        copy.setCloseCombatRange(2.0);
        copy.setRangedCombatRange(3.0);
        copy.setNormalViewDistance(2.0);
        copy.setDisadvantagedViewDistance(3.0);
        copy.setAdvantagedViewDistance(4.0);

        Optional<ITroop> updated = repository.update(copy);

        List<? extends ITroop> queried = repository.get();

        assertTrue(updated.isPresent());
        assertEquals(1, queried.size());
        assertNotEquals(initial, updated.get());
        assertEquals(updated.get(), queried.get(0));
    }

    @Test
    void updateUnknownTroop() {
        ITroop troop = new Troop(-200, 100, 100, 1.0, 1.0, 1.0, 2.0, 3.0, 1.0, 2.0, 1.0, 2.0, 1.0);
        assertFalse(repository.update(troop).isPresent());
    }

    @Test
    void deleteUnknownTroop() {
        assertFalse(repository.delete(444));
    }

    @Test
    void getSomeTroops() {
        List<ITroop> initialized = Utils.initializeTroops(repository);
        List<? extends ITroop> fetched = repository.get(List.of(1, 2, 3));

        // ITroop t1 = new Troop(1,100,120,120.2, 11.2,12.0, 13.0, 6245.0,1534.0, 1364.0,121235.3, 125.3, 51.3);
        // ITroop t2 = new Troop(2, 1, 1240, 15320.2, 13441.2, 1762.0, 13.0, 6245.0, 1538634.0, 1364.0, 12125635.3, 125.3, 5451.3);
        // ITroop t3 = new Troop(3, 16700, 15620, 12820.2, 1561.2, 15672.0, 13.0, 686245.0, 1534.0, 1364.0, 12121735.3, 12705.3, 5184.3)

        assertEquals(3, fetched.size());
        assertEquals(initialized.get(0), fetched.get(0));
        assertEquals(initialized.get(1), fetched.get(1));
        assertEquals(initialized.get(2), fetched.get(2));
        assertTrue(initialized.containsAll(fetched));
    }

    @Test
    void createDoesNotMutate() {
        ITroop initial = new Troop(1, 100, 120, 120.2, 11.2, 12.0, 13.0, 6245.0, 1534.0, 1364.0, 121235.3, 125.3, 51.3);
        ITroop copy = new Troop(initial);
        repository.create(copy);
        assertEquals(initial, copy);
    }

    @Test
    void updateDoesNotMutate() {
        ITroop initial = new Troop(1, 100, 120, 120.2, 11.2, 12.0, 13.0, 6245.0, 1534.0, 1364.0, 121235.3, 125.3, 51.3);
        ITroop copy = new Troop(initial);
        repository.create(copy);
        repository.update(copy);
        assertEquals(initial, copy);
    }
}
