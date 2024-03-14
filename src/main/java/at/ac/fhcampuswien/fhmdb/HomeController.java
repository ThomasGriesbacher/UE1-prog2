package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import static at.ac.fhcampuswien.fhmdb.models.Genre.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton filterBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<Genre> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();
    public List<Movie> filteredMovies = new ArrayList<>(allMovies);
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.setValue(NONE);

        // filtering button
        filterBtn.setOnAction(actionEvent -> {
            filteredMovies = new ArrayList<>();
            filteredMovies.addAll(getMoviesFiltered(searchField.getText(), genreComboBox.getValue()));
            observableMovies.setAll(filteredMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell());
        });

        // sorting button
        sortBtn.setOnAction(actionEvent -> {
            sort_movies();
            observableMovies.setAll(filteredMovies);
        });
    }

    public List<Movie> getMoviesFiltered(String searchQuery, Genre filter) {
        List<Movie> filteredMovies = Movie.getMoviesByGenre(allMovies, filter);
        List<Movie> queriedMovies;
        queriedMovies = Movie.searchTitle(searchQuery, filteredMovies);
        return queriedMovies;
    }
    public void sort_movies() {
        if (sortBtn.getText().equals("Sort (asc)")) {
            sortBtn.setText("Sort (desc)");
            sortAscending();
        } else {
            sortBtn.setText("Sort (asc)");
            sortDescending();
        }
    }

    //sorting with Comparator
    public void sortAscending() {
        filteredMovies.sort(Comparator.comparing(Movie::getTitle));
    }
    public void sortDescending() {
        filteredMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
    }

}