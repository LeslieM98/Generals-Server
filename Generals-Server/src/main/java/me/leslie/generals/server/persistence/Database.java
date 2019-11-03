package me.leslie.generals.server.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.server.persistence.exception.InitializationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@EqualsAndHashCode
@ToString
@Getter
public class Database {
    public static final String DEFAULT_DB_URL = "jdbc:sqlite:";
    @NonNull
    private final String url;
    private final Connection connection;

    private static Database instance;

    public static Database get() {
        if (instance == null) {
            instance = new Database(DEFAULT_DB_URL);
        }
        return instance;
    }

    private Database(String url) {
        try {
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new InitializationException("Could not get Database connection", e);
        }
        initialize();
        this.url = url;
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
                ");";


        final String armySchema = "CREATE TABLE IF NOT EXISTS ARMY(" +
                "hq INTEGER," +
                "troop INTEGER," +
                "FOREIGN KEY(hq) REFERENCES TROOP(id)," +
                "FOREIGN KEY(troop) REFERENCES TROOP(id)," +
                "UNIQUE(hq, troop)," +
                "PRIMARY KEY (hq, troop)" +
                ");";

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
