package me.leslie.generals.server.repository;

import lombok.*;
import me.leslie.generals.core.entity.Army;
import me.leslie.generals.core.entity.Army.ArmyBuilder;
import me.leslie.generals.core.entity.Troop;
import me.leslie.generals.server.persistence.Database;
import me.leslie.generals.server.repository.exception.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ArmyRepository {

    private static final String TROOP_ROW = "troop";
    private static final String HQ_ROW = "hq";
    private static final String TABLE = "ARMY";

    @NonNull
    private final Database dataBase;

    @NonNull
    private final TroopRepository troopRepository;


    public Army createArmy(ArmyBuilder army) {
        Connection connection = dataBase.getConnection();
        String query = String.format("INSERT INTO %s (%s, %s) VALUES(?, ?)", TABLE, HQ_ROW, TROOP_ROW);
        try (PreparedStatement sql = connection.prepareStatement(query)) {
            Army instance = army.build();
            for (var troop : instance.getTroops()) {

                sql.setLong(1, instance.getHq().getId());
                sql.setLong(2, troop.getId());
                sql.addBatch();
            }

            sql.executeBatch();

            return instance;
        } catch (Exception e) {
            throw new CreationFailedException("Could not Create Army", e);
        }

    }

    public Army updateArmy(Army army) {
        try {
            deleteArmy(army);
            return createArmy(army.copy());
        } catch (DataException e) {
            throw new UpdateFailedException("Could not update Army", e);
        }
    }

    public void deleteArmy(Army army) {
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(String.format("DELETE FROM %s WHERE hq = ?", TABLE))) {
            sql.setLong(1, army.getHq().getId());
            sql.execute();
        } catch (SQLException e) {
            throw new DeletionFailedException("Could not delete Troop", e);
        }
    }

    public Army get(long id) {
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s = ?", TABLE, HQ_ROW))) {
            sql.setLong(1, id);
            try (ResultSet results = sql.executeQuery()) {
                Troop hq = null;
                List<Long> troops = new ArrayList<>();

                while (results.next()) {
                    if (hq == null) {
                        hq = troopRepository.getTroop(results.getLong(HQ_ROW));
                    }
                    troops.add(results.getLong(TROOP_ROW));
                }

                if (hq == null) {
                    return null;
                }
                return Army.builder().hq(hq).troops(troopRepository.getTroops(troops)).build();
            }
        } catch (SQLException e) {
            throw new UpdateFailedException("Could not get Army", e);
        }
    }

    public List<Army> get() {
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(String.format("SELECT * FROM %s", TABLE))) {
            try (ResultSet results = sql.executeQuery()) {
                Map<Long, Set<Long>> armies = new HashMap<>();

                while (results.next()) {
                    long hq = results.getLong(HQ_ROW);
                    armies.putIfAbsent(hq, new HashSet<>());
                    armies.get(hq).add(results.getLong(TROOP_ROW));
                }

                return armies.entrySet().stream()
                        .map(x -> Army.builder().hq(troopRepository.getTroop(x.getKey())).troops(troopRepository.getTroops(x.getValue())).build())
                        .collect(Collectors.toList());
            }
        } catch (SQLException e) {
            throw new UpdateFailedException("Could not get Armies", e);
        }
    }

    public List<Army> get(Collection<Long> ids) {
        Connection connection = dataBase.getConnection();
        StringBuilder idString = new StringBuilder();
        ids.forEach(idString.append((idString.length() == 0) ? "" : ", ")::append);
        try (PreparedStatement sql = connection.prepareStatement(String.format("Select * FROM %s WHERE %s in (%s)", TABLE, HQ_ROW, idString.toString()))) {
            try (ResultSet results = sql.executeQuery()) {
                Map<Long, Set<Long>> armies = new HashMap<>();

                while (results.next()) {
                    long hq = results.getLong(HQ_ROW);
                    armies.putIfAbsent(hq, new HashSet<>());
                    armies.get(hq).add(results.getLong(TROOP_ROW));
                }

                return armies.entrySet().stream()
                        .map(x -> Army.builder().hq(troopRepository.getTroop(x.getKey())).troops(troopRepository.getTroops(x.getValue())).build())
                        .collect(Collectors.toList());
            }
        } catch (SQLException e) {
            throw new FetchFailedException("Could not get Armies", e);
        }
    }

    public Army removeFromArmy(Army army, Collection<Troop> troops) {
        List<Troop> updatedTroops = new ArrayList<>(army.getTroops());
        updatedTroops.removeAll(troops);
        return updateArmy(army.copy().troops(updatedTroops).build());
    }

    public Army addToArmy(Army army, Collection<Troop> troop) {
        List<Troop> troops = new ArrayList<>();
        troops.addAll(army.getTroops());
        troops.addAll(troop);
        return updateArmy(army.copy().troops(troops).build());
    }

}
