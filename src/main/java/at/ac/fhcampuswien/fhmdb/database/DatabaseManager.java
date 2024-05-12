package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    public static final String URL = "jdbc:postgresql://localhost:5432/fhmdb";
    public static final String user = "admin";
    public static final String pass = "pass";

    private static ConnectionSource connectionSource;
    private static DatabaseManager instance;

    private final Dao<MovieEntity, Long> watchlistMovieDao;

    private DatabaseManager() throws DatabaseException {
        try {
            createConnectionSource();
            watchlistMovieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
            createTables();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // get singleton database instance
    public static DatabaseManager getInstance() throws DatabaseException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public static ConnectionSource getConnectionSource() throws DatabaseException {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    private static void createConnectionSource() throws DatabaseException {
        try {
            connectionSource = new JdbcConnectionSource(URL, user, pass);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    // close db connection
    public static void closeConnectionSource() throws DatabaseException {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (Exception e) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, "Error while closing database connection", e);                throw new DatabaseException(e.getMessage());
            }
        }
    }

    // creates tables in database
    private static void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
    }

    // removes tables from database
    private static void dropTables() throws SQLException {
        TableUtils.dropTable(connectionSource, MovieEntity.class, true);
    }

    public Dao<MovieEntity, Long> getWatchlistDao() {
        return watchlistMovieDao;
    }
}
