package me.leslie.generals.server.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import me.leslie.generals.core.entity.interfaces.ITroop;
import me.leslie.generals.core.entity.pojos.Troop;
import me.leslie.generals.server.persistence.Database;
import me.leslie.generals.server.persistence.jooq.tables.daos.TroopDao;
import me.leslie.generals.server.repository.exception.CreationFailedException;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static me.leslie.generals.server.persistence.jooq.Tables.TROOP;

/**
 * TroopJooqRepository
 */
@EqualsAndHashCode
@Getter
public class TroopRepository {

    private static final SQLDialect SQL_DIALECT = SQLDialect.SQLITE;

    @NonNull
    private final Database dataBase;

    private final Configuration configuration;
    private final TroopDao jooqDao;

    public TroopRepository(Database dataBase) {
        this.dataBase = dataBase;
        this.configuration = new DefaultConfiguration().set(dataBase.getConnection()).set(SQL_DIALECT);
        this.jooqDao = new TroopDao(configuration);
    }

    private OptionalInt nextID() {
        DSLContext jooq = DSL.using(dataBase.getConnection(), SQL_DIALECT);
        Set<Integer> results = jooq.select(TROOP.ID).from(TROOP).fetch()
                .stream()
                .map(Record1::component1)
                .collect(Collectors.toUnmodifiableSet());

        return IntStream.iterate(1, i -> i + 1)
                .dropWhile(results::contains)
                .limit(1)
                .reduce(Integer::sum);
    }

    public ITroop create(ITroop troop) {
        Troop mutable = new Troop(troop);
        OptionalInt nextID = nextID();
        if (nextID.isEmpty()) {
            throw new CreationFailedException("Could not fetch next id");
        }
        mutable.setId(nextID.getAsInt());
        jooqDao.insert(mutable);
        return mutable;
    }

    public Optional<ITroop> update(ITroop troop) {
        if (jooqDao.existsById(troop.getId())) {
            jooqDao.update(new Troop(troop));
            return Optional.of(troop);
        }
        return Optional.empty();
    }

    public boolean delete(int id) {
        if (jooqDao.existsById(id)) {
            jooqDao.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ITroop> get(int id) {
        ITroop fetched = jooqDao.fetchOneById(id);
        if (fetched != null) {
            return Optional.of(fetched);
        }
        return Optional.empty();
    }

    public List<? extends ITroop> get() {
        return jooqDao.findAll();
    }

    public List<? extends ITroop> get(Collection<Integer> ids) {
        Integer[] arr = new Integer[ids.size()];
        arr = ids.toArray(arr);
        return jooqDao.fetchById(arr);
    }
}