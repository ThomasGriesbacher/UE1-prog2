package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;

public class WatchlistRepository {

    Dao<MovieEntity, Long> dao;

    public WatchlistRepository() throws DatabaseException {
        try {
            this.dao = DatabaseManager.getInstance().getWatchlistDao();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<MovieEntity> readWatchlist() throws DatabaseException {
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error while reading watchlist from the database", e);
            throw new DatabaseException("Error while reading watchlist from the database");
        }
    }

    public void addToWatchlist(MovieEntity movie) throws DatabaseException {
        try {
            // only add movie if it does not exist yet
            long count = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (count == 0) {
                dao.create(movie);
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error while reading watchlist from the database", e);
            throw new DatabaseException("Error while reading watchlist from the database");
        }
    }

    public void removeFromWatchlist(MovieEntity movie) throws DatabaseException {
        try {
            dao.delete(movie);
        } catch (Exception e) {
            throw new DatabaseException("Error while reading watchlist from the database");
        }
    }
    public boolean isOnWatchlist(MovieEntity movie) throws DatabaseException {
        try {
            return !dao.queryForMatching(movie).isEmpty();
        } catch (Exception e) {
            throw new DatabaseException("Error while checking if movie is on watchlist");
        }
    }
}