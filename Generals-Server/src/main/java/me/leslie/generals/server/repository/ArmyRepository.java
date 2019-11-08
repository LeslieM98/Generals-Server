package me.leslie.generals.server.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.core.entity.interfaces.IArmy;
import me.leslie.generals.core.entity.interfaces.IArmyComposition;
import me.leslie.generals.core.entity.interfaces.ITroop;
import me.leslie.generals.core.entity.pojos.Army;
import me.leslie.generals.core.entity.pojos.ArmyComposition;
import me.leslie.generals.server.persistence.Database;
import me.leslie.generals.server.persistence.jooq.Tables;
import me.leslie.generals.server.persistence.jooq.tables.daos.ArmyDao;
import me.leslie.generals.server.repository.exception.UpdateFailedException;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@ToString
@EqualsAndHashCode
public class ArmyRepository {

    private static final SQLDialect SQL_DIALECT = SQLDialect.SQLITE;
    @NonNull
    private final Database database;

    private final Configuration configuration;
    private final ArmyDao jooqDao;
    private final DSLContext jooq;

    @NonNull
    private final TroopRepository troopRepository;

    public ArmyRepository(Database database) {
        this.database = database;
        troopRepository = new TroopRepository(database);
        configuration = new DefaultConfiguration().set(SQL_DIALECT).set(database.getConnection());
        jooqDao = new ArmyDao(configuration);
        jooq = DSL.using(configuration);
    }

    private Set<Army> transformToRelation(IArmyComposition army) {
        return army.getTroops().stream().map(x -> new Army(army.getHQ().getId(), x.getId())).collect(Collectors.toSet());
    }

    /**
     * @param army
     * @return
     * @throws UpdateFailedException if HQ is not present in repo or any of the given troops are not present in the repo
     */
    public IArmyComposition updateRelation(IArmyComposition army) {
        Collection<Army> relations = transformToRelation(army);
        Collection<Integer> alreadyExisting = troopRepository.get(army.getTroops().stream()
                .map(ITroop::getId)
                .collect(Collectors.toSet()))
                .stream()
                .map(ITroop::getId).collect(Collectors.toSet());

        if (alreadyExisting.contains(army.getHQ().getId())) {
            throw new UpdateFailedException("HQ does not exist in TroopRepo");
        }
        if (alreadyExisting.containsAll(relations)) {
            throw new UpdateFailedException("Some troops are not existing in TroopRepo");
        }

        var troopsExistingInOtherArmies = relations
                .stream()
                .map(IArmy::getTroop)
                .map(jooqDao::fetchByTroop)
                .reduce(new ArrayList<>(), (x, y) -> {
                    x.addAll(y);
                    return x;
                })
                .stream()
                .filter(x -> x.getHq() != army.getHQ().getId()
                ).collect(Collectors.toSet());

        if (!troopsExistingInOtherArmies.isEmpty()) {
            throw new UpdateFailedException(String.format("Troops {%s} already in other Army", troopsExistingInOtherArmies.stream().map(Object::toString).reduce("", (x, y) -> x + ", " + y)));
        }

        jooq.delete(Tables.ARMY).where(Tables.ARMY.HQ.eq(army.getHQ().getId())).execute();
        for (Army x : relations) {
            try {
                jooqDao.insert(x);
            } catch (NullPointerException e) {
                throw new UpdateFailedException("Could not insert " + x.toString(), e);
            }
        }
        return army;
    }

    public void deleteRelations(IArmyComposition army) {
        Set<Army> relations = transformToRelation(army);
        jooqDao.delete(relations.toArray(new Army[0]));
    }

    public Optional<IArmyComposition> get(int hqID) {
        List<? extends IArmy> relations = jooqDao.fetchByHq(hqID);
        if (relations.isEmpty()) {
            return Optional.empty();
        }
        Optional<ITroop> hq = troopRepository.get(hqID);
        if (hq.isEmpty()) {
            return Optional.empty();
        }
        Set<? extends ITroop> troops = new HashSet<>(troopRepository.get(relations.stream()
                .map(IArmy::getTroop)
                .collect(Collectors.toSet())));

        ArmyComposition returned = new ArmyComposition(hq.get(), troops);
        return Optional.of(returned);
    }

    public List<? extends IArmyComposition> get() {
        Set<Integer> hqIds = jooqDao.findAll().stream().map(IArmy::getHq).collect(Collectors.toSet());
        List<IArmyComposition> results = hqIds.stream().map(this::get).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        return results;
    }

    public List<? extends IArmyComposition> get(Collection<Integer> ids) {
        return ids.stream()
                .map(this::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void delete(int hqID) {
        jooq.delete(Tables.ARMY).where(Tables.ARMY.HQ.eq(hqID)).execute();
    }

    public void delete(Collection<Integer> hqIDs) {
        jooq.delete(Tables.ARMY).where(Tables.ARMY.HQ.in(hqIDs)).execute();
    }


}
