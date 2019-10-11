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

    private long nextID() throws SQLException {
        PreparedStatement sql = dataBase.getConnection().prepareStatement("SELECT id FROM TROOP ORDER BY id DESC LIMIT 1");
        ResultSet results = sql.executeQuery();
        long nextID = 1;
        if (!results.isClosed()) {
            results.next();
            nextID = results.getLong(1) + 1;
            results.close();
        }
        return nextID;
    }

    public Troop createTroop(TroopBuilder troopBuilder) {


        Connection connection = dataBase.getConnection();
        String query = "INSERT INTO TROOP (current_health, max_health, pos_x, pos_y, normal_speed, street_speed, difficult_terrain_speed, close_combat_range, ranged_combat_range, normal_view_distance, disadvantaged_view_distance, advantaged_view_distance) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement sql = connection.prepareStatement(query)) {
            Troop troop = troopBuilder
                    .id(nextID())
                    .build();

            sql.setInt(1, troop.getCurrentHealth());
            sql.setInt(2, troop.getMaxHealth());
            sql.setDouble(3, troop.getPos().getX());
            sql.setDouble(4, troop.getPos().getY());
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
        String query = "UPDATE TROOP SET current_health = ?, max_health = ?, pos_x = ?, pos_y = ?, normal_speed = ?, street_speed = ?, difficult_terrain_speed = ?, close_combat_range = ?, ranged_combat_range = ?, normal_view_distance = ?, disadvantaged_view_distance = ?, advantaged_view_distance = ? WHERE id = ?";
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(query)) {
            sql.setInt(1, troop.getCurrentHealth());
            sql.setInt(2, troop.getMaxHealth());
            sql.setDouble(3, troop.getPos().getX());
            sql.setDouble(4, troop.getPos().getY());
            sql.setDouble(5, troop.getMovementSpeed().getNormal());
            sql.setDouble(6, troop.getMovementSpeed().getStreet());
            sql.setDouble(7, troop.getMovementSpeed().getDifficultTerrain());
            sql.setDouble(8, troop.getCombatRange().getClose());
            sql.setDouble(9, troop.getCombatRange().getRanged());
            sql.setDouble(10, troop.getViewDistance().getNormal());
            sql.setDouble(11, troop.getViewDistance().getDisadvantaged());
            sql.setDouble(12, troop.getViewDistance().getAdvantaged());

            sql.setLong(13, troop.getID());

            sql.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateFailedException("Could not update Troop", e);
        }

        return troop;
    }

    public boolean deleteTroop(long id) {
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement("DELETE FROM TROOP WHERE id = ?")) {
            sql.setLong(1, id);
            sql.execute();
            return true;
        } catch (SQLException e) {
            throw new DeletionFailedException("Could not delete Troop", e);
        }
    }

    public Troop getTroop(long id) {
        String query = "SELECT current_health, max_health, pos_x, pos_y, normal_speed, street_speed, difficult_terrain_speed, close_combat_range, ranged_combat_range, normal_view_distance, disadvantaged_view_distance, advantaged_view_distance FROM TROOP WHERE id = ? ";
        try (PreparedStatement sql = dataBase.getConnection().prepareStatement(query)) {
            sql.setLong(1, id);
            ResultSet results = sql.executeQuery();
            results.next();
            return Troop.builder()
                    .id(id)
                    .currentHealth(results.getInt("current_health"))
                    .maxHealth(results.getInt("max_health"))
                    .position(new Vector2D(results.getDouble("pos_x"), results.getDouble("pos_y")))
                    .movementSpeed(new MovementSpeed(
                            results.getDouble("normal_speed"),
                            results.getDouble("street_speed"),
                            results.getDouble("difficult_terrain_speed")
                    ))
                    .combatRange(new CombatRange(
                            results.getDouble("close_combat_range"),
                            results.getDouble("ranged_combat_range")
                    ))
                    .viewDistance(new ViewDistance(
                            results.getDouble("normal_view_distance"),
                            results.getDouble("disadvantaged_view_distance"),
                            results.getDouble("advantaged_view_distance")
                    ))
                    .build();
        } catch (SQLException e) {
            throw new FetchFailedException("Could not get Troop", e);
        }

    }

    public List<Troop> getTroops() {
        String query = "SELECT * FROM TROOP";
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(query)) {
            List<Troop> troops = new ArrayList<>();
            ResultSet results = sql.executeQuery();
            while (results.next()) {
                troops.add(Troop.builder()
                        .id(results.getLong("id"))
                        .currentHealth(results.getInt("current_health"))
                        .maxHealth(results.getInt("max_health"))
                        .position(new Vector2D(results.getDouble("pos_x"), results.getDouble("pos_y")))
                        .movementSpeed(new MovementSpeed(
                                results.getDouble("normal_speed"),
                                results.getDouble("street_speed"),
                                results.getDouble("difficult_terrain_speed")
                        ))
                        .combatRange(new CombatRange(
                                results.getDouble("close_combat_range"),
                                results.getDouble("ranged_combat_range")
                        ))
                        .viewDistance(new ViewDistance(
                                results.getDouble("normal_view_distance"),
                                results.getDouble("disadvantaged_view_distance"),
                                results.getDouble("advantaged_view_distance")
                        ))
                        .build()
                );
            }
            results.close();
            return Collections.unmodifiableList(troops);
        } catch (SQLException e) {
            throw new FetchFailedException("Could not get Troops", e);
        }
    }

    public List<Troop> getTroops(Collection<Long> ids) {
        StringBuilder idString = new StringBuilder();
        for (long id : ids) {
            idString.append((idString.length() == 0) ? "" : ", ").append(id);
        }
        String query = String.format("SELECT * FROM TROOP WHERE id IN (%s)", idString.toString());
        Connection connection = dataBase.getConnection();
        try (PreparedStatement sql = connection.prepareStatement(query)) {
            ResultSet results = sql.executeQuery();
            List<Troop> troops = new ArrayList<>();
            while (results.next()) {
                troops.add(Troop.builder()
                        .id(results.getLong("id"))
                        .currentHealth(results.getInt("current_health"))
                        .maxHealth(results.getInt("max_health"))
                        .position(new Vector2D(results.getDouble("pos_x"), results.getDouble("pos_y")))
                        .movementSpeed(new MovementSpeed(
                                results.getDouble("normal_speed"),
                                results.getDouble("street_speed"),
                                results.getDouble("difficult_terrain_speed")
                        ))
                        .combatRange(new CombatRange(
                                results.getDouble("close_combat_range"),
                                results.getDouble("ranged_combat_range")
                        ))
                        .viewDistance(new ViewDistance(
                                results.getDouble("normal_view_distance"),
                                results.getDouble("disadvantaged_view_distance"),
                                results.getDouble("advantaged_view_distance")
                        ))
                        .build()
                );
            }
            return Collections.unmodifiableList(troops);
        } catch (SQLException e) {
            throw new FetchFailedException("Could not get Troops", e);
        }
    }
}
