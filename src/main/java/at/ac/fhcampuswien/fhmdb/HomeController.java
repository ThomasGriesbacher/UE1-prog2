package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    public List<String> allGenres = new ArrayList<>();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public HomeController() throws IOException {
    }

    public HomeController(List<Movie> allMovies) throws IOException{
        this.allMovies = allMovies;
    }

    public void getAllGenres(){
        for (Movie movie : allMovies) {
            allGenres.addAll(movie.getGenre());
        }
    }

    @FXML
    public List<Movie> handleGenreSelected(String genre) {
        return Movie.filterMoviesByGenre(allMovies, genre);
    }

    @FXML
    public List<Movie> handleSearchQuery(String query){
        return Movie.filterMovieBySearchQuery(allMovies, query);
    }

    @FXML
    public List<Movie> handleBothSearchQueryAndGenre(String query, String genre){
        return Movie.filterMoviesBySearchQueryAndGenre(query, genre, allMovies);
    }

    public void sortMovies(String order) {
        Comparator<Movie> titleComparator = Comparator.comparing(Movie::getTitle);
        if (order.equalsIgnoreCase("asc")) {
            observableMovies.sort(titleComparator);
        } else if (order.equalsIgnoreCase("desc")) {
            observableMovies.sort(titleComparator.reversed());
        } else {
            System.out.println("Invalid sort order specified.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies); // add dummy data to observable list

        getAllGenres();
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(allGenres);

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        genreComboBox.setOnAction(actionEvent -> {
            String selectedGenre = (String) genreComboBox.getValue();
            observableMovies.setAll(handleGenreSelected(selectedGenre));
        });

        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String query = newValue.trim(); // Get the new value of the search query
                System.out.println(query);
                observableMovies.setAll(handleSearchQuery(query)); // Filter movies based on the new query and update the list
            }
        });

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortMovies("asc");
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortMovies("desc");
                sortBtn.setText("Sort (asc)");
            }
        });

        searchBtn.setOnAction(actionEvent -> {
            String selectedGenre = (String) genreComboBox.getValue();
            String query = searchField.getText();
            observableMovies.setAll(handleBothSearchQueryAndGenre(query, selectedGenre));
        });
    }
}