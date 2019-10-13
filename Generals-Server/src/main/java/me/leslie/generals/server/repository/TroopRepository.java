package me.leslie.generals.server.repository;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import me.leslie.generals.core.CombatRange;
import me.leslie.generals.core.MovementSpeed;
import me.leslie.generals.core.Vector2D;
import me.leslie.generals.core.ViewDistance;
import me.leslie.generals.core.entity.Troop;
import me.leslie.generals.core.entity.Troop.TroopBuilder;
import me.leslie.generals.server.persistance.DataBase;
import me.leslie.generals.server.repository.exception.CreationFailedException;
import me.leslie.generals.server.repository.exception.DeletionFailedException;
import me.leslie.generals.server.repository.exception.FetchFailedException;
import me.leslie.generals.server.repository.exception.UpdateFailedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class TroopRepository {
    @NonNull
    private final DataBase dataBase;

    private static final String TABLE = "TROOP";
    private static final String ID_ROW = "id";
    private static final String CURRENT_HEALTH_ROW = "current_health";
    private static final String MAX_HEALTH_ROW = "max_health";
    private static final String POS_X_ROW = "pos_x";
    private static final String POS_Y_ROW = "pos_y";
    private static final String NORMAL_SPEED_ROW = "normal_speed";
    private static final String STREET_SPEED_ROW = "street_speed";
    private static final String DIFFICULT_TERRAIN_SPEED_ROW = "difficult_terrain_speed";
    private static final String CLOSE_COMBAT_RANGE_ROW = "close_combat_range";
    private static final String RANGED_COMBAT_RANGE_ROW = "ranged_combat_range";
    private static final String NORMAL_VIEW_DISTANCE_ROW = "normal_view_distance";
    private static final String DISADVANTAGED_VIEW_DISTANCE_ROW = "disadvantaged_view_distance";
    private static final String ADVANTAGED_VIEW_DISTANCE_ROW = "advantaged_view_distance";


    private long nextID() throws SQLException {
        try (PreparedStatement sql = dataBase.getConnection().prepareStatement(String.format("SELECT %s FROM %s ORDER BY %s DESC LIMIT %d", ID_ROW, TABLE, ID_ROW, 1))) {
            try (ResultSet results = sql.executeQuery()) {
                long nextID = 1;
                if (!results.isClosed()) {
                    results.next();
                    nextID = results.getLong(1) + 1;
                }
                return nextID;
            }
        }
    }

    public Troop createTroop(TroopBuilder troopBuilder) {


        Connection connection = dataBase.getConnection();
        String query = String.format(
                "INSERT INTO TROOP (%s, %s, %s, %s, %s,%s, %s, %s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                CURRENT_HEALTH_ROW,
                MAX_HEALTH_ROW,
                POS_X_ROW,
                POS_Y_ROW,
                NORMAL_SPEED_ROW,
                STREET_SPEED_ROW,
                DIFFICULT_TERRAIN_SPEED_ROW,
                CLOSE_COMBAT_RANGE_ROW,
                RANGED_COMBAT_RANGE_ROW,
                NORMAL_VIEW_DISTANCE_ROW,
                DISADVANTAGED_VIEW_DISTANCE_ROW,
                ADVANTAGED_VIEW_DISTANCE_ROW
        );
        try (PreparedStatement sql = connection.prepareStatement(query)) {
            Troop troop = troopBuilder
                    .id(nextID())
                    .build();

            sql.setInt(1, troop.getCurrentHealth());
            sql.setInt(2, troop.getMaxHealth());
            sql.setDouble(3, troop.getPosition().getX());
            sql.setDouble(4, troop.getPosition().getY());
            sql.setDouble(5, troop.getMovementSpeed().getNormal());
            sql.setDouble(6, troop.getMovementSpeed().getStreet());
            sql.setDouble(7, troop.getMovementSpeed().getDifficultTerrain());
            sql.setDouble(8, troop.getCombatRange().getClose());
            sql.setDouble(9, troop.getCombatRange().getRanged());
            sql.setDouble(10, troop.getViewDistance().getNormal());
            sql.setDouble(11, troop.getViewDistance().getDisadvantaged());
            sql.setDouble(12, troop.getViewDistance().getAdvantaged());

            sql.execute();

            return troop;
        } catch (SQLException e) {
            throw new CreationFailedException("Could not create Troop", e);
        }

    }

    public Troop updateTroop(Troop troop) {
        String query = String.format(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                TABLE,
                CURRENT_HEALTH_ROW,
                MAX_HEALTH_ROW,
                POS_X_ROW,
                POS_Y_ROW,
                NORMAL_SPEED_ROW,
                STREET_SPEED_ROW,
                DIFFICULT_TERRAIN_SPEED_ROW,
                CLOSE_COMBAT_RANGE_ROW,
                RANGED_COMBAT_RANGE_ROW,
                NORMAL_VIEW_DISTANCE_ROW,
                DISADVANTAGED_VIEW_DISTANCE_ROW,
                ADVANTAGED_VIEW_DISTANCE_ROW,
                ID_ROW
        );
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(query)) {
            sql.setInt(1, troop.getCurrentHealth());
            sql.setInt(2, troop.getMaxHealth());
            sql.setDouble(3, troop.getPosition().getX());
            sql.setDouble(4, troop.getPosition().getY());
            sql.setDouble(5, troop.getMovementSpeed().getNormal());
            sql.setDouble(6, troop.getMovementSpeed().getStreet());
            sql.setDouble(7, troop.getMovementSpeed().getDifficultTerrain());
            sql.setDouble(8, troop.getCombatRange().getClose());
            sql.setDouble(9, troop.getCombatRange().getRanged());
            sql.setDouble(10, troop.getViewDistance().getNormal());
            sql.setDouble(11, troop.getViewDistance().getDisadvantaged());
            sql.setDouble(12, troop.getViewDistance().getAdvantaged());

            sql.setLong(13, troop.getId());

            sql.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateFailedException("Could not update Troop", e);
        }

        return troop;
    }

    public boolean deleteTroop(long id) {
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(String.format("DELETE FROM %s WHERE %s = ?", TABLE, ID_ROW))) {
            sql.setLong(1, id);
            return sql.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DeletionFailedException("Could not delete Troop", e);
        }
    }

    public Troop getTroop(long id) {
        String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s = ? ",
                CURRENT_HEALTH_ROW,
                MAX_HEALTH_ROW,
                POS_X_ROW,
                POS_Y_ROW,
                NORMAL_SPEED_ROW,
                STREET_SPEED_ROW,
                DIFFICULT_TERRAIN_SPEED_ROW,
                CLOSE_COMBAT_RANGE_ROW,
                RANGED_COMBAT_RANGE_ROW,
                NORMAL_VIEW_DISTANCE_ROW,
                DISADVANTAGED_VIEW_DISTANCE_ROW,
                ADVANTAGED_VIEW_DISTANCE_ROW,
                TABLE,
                ID_ROW
        );
        try (PreparedStatement sql = dataBase.getConnection().prepareStatement(query)) {
            sql.setLong(1, id);
            try (ResultSet results = sql.executeQuery()) {
                results.next();
                return Troop.builder()
                        .id(id)
                        .currentHealth(results.getInt(CURRENT_HEALTH_ROW))
                        .maxHealth(results.getInt(MAX_HEALTH_ROW))
                        .position(new Vector2D(results.getDouble(POS_X_ROW), results.getDouble(POS_Y_ROW)))
                        .movementSpeed(new MovementSpeed(
                                results.getDouble(NORMAL_SPEED_ROW),
                                results.getDouble(STREET_SPEED_ROW),
                                results.getDouble(DIFFICULT_TERRAIN_SPEED_ROW)
                        ))
                        .combatRange(new CombatRange(
                                results.getDouble(CLOSE_COMBAT_RANGE_ROW),
                                results.getDouble(RANGED_COMBAT_RANGE_ROW)
                        ))
                        .viewDistance(new ViewDistance(
                                results.getDouble(NORMAL_VIEW_DISTANCE_ROW),
                                results.getDouble(DISADVANTAGED_VIEW_DISTANCE_ROW),
                                results.getDouble(ADVANTAGED_VIEW_DISTANCE_ROW)
                        ))
                        .build();
            }
        } catch (SQLException e) {
            throw new FetchFailedException("Could not get Troop", e);
        }

    }

    public List<Troop> getTroops() {
        String query = String.format("SELECT * FROM %s", TABLE);
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(query)) {
            List<Troop> troops = new ArrayList<>();
            try (ResultSet results = sql.executeQuery()) {
                while (results.next()) {
                    troops.add(Troop.builder()
                            .id(results.getLong(ID_ROW))
                            .currentHealth(results.getInt(CURRENT_HEALTH_ROW))
                            .maxHealth(results.getInt(MAX_HEALTH_ROW))
                            .position(new Vector2D(results.getDouble(POS_X_ROW), results.getDouble(POS_Y_ROW)))
                            .movementSpeed(new MovementSpeed(
                                    results.getDouble(NORMAL_SPEED_ROW),
                                    results.getDouble(STREET_SPEED_ROW),
                                    results.getDouble(DIFFICULT_TERRAIN_SPEED_ROW)
                            ))
                            .combatRange(new CombatRange(
                                    results.getDouble(CLOSE_COMBAT_RANGE_ROW),
                                    results.getDouble(RANGED_COMBAT_RANGE_ROW)
                            ))
                            .viewDistance(new ViewDistance(
                                    results.getDouble(NORMAL_VIEW_DISTANCE_ROW),
                                    results.getDouble(DISADVANTAGED_VIEW_DISTANCE_ROW),
                                    results.getDouble(ADVANTAGED_VIEW_DISTANCE_ROW)
                            ))
                            .build()
                    );
                }
                return Collections.unmodifiableList(troops);
            }
        } catch (SQLException e) {
            throw new FetchFailedException("Could not get Troops", e);
        }
    }

    public List<Troop> getTroops(Collection<Long> ids) {
        StringBuilder idString = new StringBuilder();
        for (long id : ids) {
            idString.append((idString.length() == 0) ? "" : ", ").append(id);
        }
        String query = String.format("SELECT * FROM %s WHERE %s IN (%s)", TABLE, ID_ROW, idString.toString());
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(query)) {
            try (ResultSet results = sql.executeQuery()) {
                List<Troop> troops = new ArrayList<>();
                while (results.next()) {
                    troops.add(Troop.builder()
                            .id(results.getLong(ID_ROW))
                            .currentHealth(results.getInt(CURRENT_HEALTH_ROW))
                            .maxHealth(results.getInt(MAX_HEALTH_ROW))
                            .position(new Vector2D(results.getDouble(POS_X_ROW), results.getDouble(POS_Y_ROW)))
                            .movementSpeed(new MovementSpeed(
                                    results.getDouble(NORMAL_SPEED_ROW),
                                    results.getDouble(STREET_SPEED_ROW),
                                    results.getDouble(DIFFICULT_TERRAIN_SPEED_ROW)
                            ))
                            .combatRange(new CombatRange(
                                    results.getDouble(CLOSE_COMBAT_RANGE_ROW),
                                    results.getDouble(RANGED_COMBAT_RANGE_ROW)
                            ))
                            .viewDistance(new ViewDistance(
                                    results.getDouble(NORMAL_VIEW_DISTANCE_ROW),
                                    results.getDouble(DISADVANTAGED_VIEW_DISTANCE_ROW),
                                    results.getDouble(ADVANTAGED_VIEW_DISTANCE_ROW)
                            ))
                            .build()
                    );
                }
                return Collections.unmodifiableList(troops);
            }
        } catch (SQLException e) {
            throw new FetchFailedException("Could not get Troops", e);
        }
    }
}
