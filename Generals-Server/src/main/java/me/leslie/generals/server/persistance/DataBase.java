package me.leslie.generals.server.persistance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.server.persistance.exception.InitializationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@EqualsAndHashCode
@ToString
@Getter
public class DataBase {
    public static final String DEFAULT_DB_URL = "jdbc:sqlite:";
    private static final Map<Path, DataBase> connections = new HashMap<>();
    @NonNull
    private final String url;
    private final Connection connection;
    private Path path;

    private DataBase(String url) throws SQLException {
        this.url = url;
        connection = DriverManager.getConnection(url);
        initialize();
    }

    private DataBase(Path path) throws SQLException {
        this(DEFAULT_DB_URL + path.toString());
        this.path = path;
    }

    private static DataBase get(String url) throws SQLException {
        return new DataBase(url);
    }

    public static DataBase get() throws SQLException {
        return get(DEFAULT_DB_URL);
    }

    public static DataBase get(Path path) throws SQLException {
        if (!connections.containsKey(path)) {
            connections.put(path, new DataBase(path));
        }
        return connections.get(path);
    }

    public static void removeDatabase(Path path) throws SQLException, IOException {
        DataBase db = get(Objects.requireNonNull(path));
        if (!db.getConnection().isClosed()) {
            db.getConnection().close();
        }
        Files.deleteIfExists(path);
    }

    private void initialize() {
        final String troopSchema = "CREATE TABLE IF NOT EXISTS TROOP(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "current_health INTEGER NOT NULL," +
                "max_health INTEGER NOT NULL," +
                "pos_x DOUBLE NOT NULL," +
                "pos_y DOUBLE NOT NULL," +
                "normal_speed DOUBLE NOT NULL," +
                "street_speed DOUBLE NOT NULL," +
                "difficult_terrain_speed DOUBLE NOT NULL," +
                "close_combat_range DOUBLE NOT NULL," +
                "ranged_combat_range DOUBLE NOT NULL," +
                "normal_view_distance DOUBLE NOT NULL," +
                "disadvantaged_view_distance DOUBLE NOT NULL," +
                "advantaged_view_distance DOUBLE NOT NULL" +
                ")";

        final String armySchema = "CREATE TABLE IF NOT EXISTS ARMY(" +
                "hq INTEGER NOT NULL," +
                "troop INTEGER NOT NULL," +
                "FOREIGN KEY(hq) REFERENCES TROOP(id)," +
                "FOREIGN KEY(troop) REFERENCES TROOP(id)," +
                "UNIQUE(troop)," +
                "PRIMARY KEY (hq, troop)," +
                "CHECK (hq != troop)" +
                ")";

        try (PreparedStatement sql = connection.prepareStatement(troopSchema)) {
            sql.execute();


        } catch (SQLException e) {
            throw new InitializationException("Could not initialize Troops", e);
        }

        try (PreparedStatement sql = connection.prepareStatement(armySchema)) {
            sql.execute();
        } catch (SQLException e) {
            throw new InitializationException("Could not initialize Armies", e);
        }
    }
}
