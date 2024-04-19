package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeControllerTest {


    @Test
    public void testGetMostPopularActor_EmptyList() {
        List<Movie> movies = new ArrayList<>();
        String mostPopularActor = HomeController.getMostPopularActor(movies);
        assertEquals("", mostPopularActor);
    }


    @Test
    public void testGetMostPopularActor_SingleMovie() {

        ArrayList<Genre> genres = new ArrayList();
        genres.add(Genre.ROMANCE);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.CRIME);
        ArrayList<String> directors = new ArrayList();
        directors.add("Director 1");
        directors.add("Director 2");

        ArrayList<String> writers = new ArrayList();
        writers.add("Writer 1");
        writers.add("Writer 2");


        ArrayList<String> cast = new ArrayList<>();
        cast.add("Actor A");
        cast.add("Actor B");
        cast.add("Actor A");

        Movie movie = new Movie("id1", "title1", genres,
                2002, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                cast,
                10);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);

        String mostPopularActor = HomeController.getMostPopularActor(movies);
        assertEquals("Actor A", mostPopularActor);
    }

    @Test
    public void testGetMostPopularActor_MultipleMovies() {

        ArrayList<Genre> genres = new ArrayList() ;
        genres.add(Genre.ROMANCE);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.CRIME);

        ArrayList<String> directors = new ArrayList() ;
        directors.add("Director 1");
        directors.add("Director 2");


        ArrayList<String> writers = new ArrayList() ;
        writers.add("Writer 1");
        writers.add("Writer 2");


        ArrayList<String> cast1 = new ArrayList<>();
        cast1.add("Actor A");
        cast1.add("Actor B");

        ArrayList<String> cast2 = new ArrayList<>();
        cast2.add("Actor A");
        cast2.add("Actor C");

        Movie movie1 = new Movie("id1", "title1", genres,
                2002, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                cast1,
                10);


        Movie movie2 = new Movie("id1", "title1", genres,
                2002, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                cast2,
                10);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        String mostPopularActor = HomeController.getMostPopularActor(movies);
        assertEquals("Actor A", mostPopularActor);
    }


    @Test
    void testGetLongestMovieTitle() {

        ArrayList<Genre> genres = new ArrayList();
        genres.add(Genre.ROMANCE);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.CRIME);
        ArrayList<String> directors = new ArrayList();
        directors.add("Director 1");
        directors.add("Director 2");
        ArrayList<String> writers = new ArrayList();
        writers.add("Writer 1");
        writers.add("Writer 2");


        ArrayList<String> cast = new ArrayList<>();
        cast.add("Actor A");
        cast.add("Actor B");
        cast.add("Actor C");

        Movie movie = new Movie("id1", "title1", genres,
                2002, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                cast,
                10);
        Movie movie1 = new Movie("id1", "title111", genres,
                2002, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                cast,
                10);
        Movie movie2 = new Movie("id1", "title11111", genres,
                2002, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                cast,
                10);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(movie1);
        movies.add(movie2);


        int longestTitleLength = HomeController.getLongestMovieTitle(movies);

        assertEquals(10, longestTitleLength);
    }

    @Test
    void testGetLongestMovieTitle_EmptyList() {
        List<Movie> movies = new ArrayList<>();

        int longestTitleLength = HomeController.getLongestMovieTitle(movies);

        assertEquals(0, longestTitleLength);
    }

    @Test
    public void testCountMoviesFromWithEmptyMoviesList() {
        assertEquals(0, HomeController.countMoviesFrom(new ArrayList<>(), "Director"));
    }

    @Test
    public void testCountMoviesFrom() {
        ArrayList<Genre> genres = new ArrayList();
        genres.add(Genre.ROMANCE);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.CRIME);
        ArrayList<String> directors = new ArrayList();
        directors.add("Director 1");
        directors.add("Director 2");

        ArrayList<String> writers = new ArrayList();
        writers.add("Writer 1");
        writers.add("Writer 2");


        ArrayList<String> cast1 = new ArrayList<>();
        cast1.add("Actor A");
        cast1.add("Actor B");
        cast1.add("Actor A");
        Movie movie1 = new Movie("id1", "title1", genres,
                2002, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                writers,
                10);
        Movie movie2 = new Movie("id1", "title1", genres,
                2002, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                cast1,
                10);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);


        assertEquals(2, HomeController.countMoviesFrom(movies, "Director 1"));
    }

    // Test case 1: Get movies between 1990 and 2000
    @Test
    public void testGetMoviesBetweenYears_Case1() {

        ArrayList<Genre> genres = new ArrayList();
        genres.add(Genre.ROMANCE);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.CRIME);
        ArrayList<String> directors = new ArrayList();
        directors.add("thomas");
        directors.add("trim");

        ArrayList<String> writers = new ArrayList();
        writers.add("thomas");
        writers.add("trim");


        ArrayList<String> cast1 = new ArrayList<>();
        cast1.add("Actor A");
        cast1.add("Actor B");
        cast1.add("Actor A");
        Movie movie1 = new Movie("id1", "title1", genres,
                1995, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                writers,
                10);
        Movie movie2 = new Movie("id1", "title1", genres,
                2002, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                cast1,
                10);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        List<Movie> result1 = HomeController.getMoviesBetweenYears(movies, 1990, 2000);
        Assertions.assertEquals(1, result1.size());
        Assertions.assertTrue(result1.contains(movie1));
    }

    // Test case 2: Get movies between 2010 and 2020
    @Test
    public void testGetMoviesBetweenYears_Case2() {

        ArrayList<Genre> genres = new ArrayList();
        genres.add(Genre.ROMANCE);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.CRIME);
        ArrayList<String> directors = new ArrayList();
        directors.add("Director 1");
        directors.add("Director 2");

        ArrayList<String> writers = new ArrayList();
        writers.add("Writer 1");
        writers.add("Writer 2");


        ArrayList<String> cast1 = new ArrayList<>();
        cast1.add("Actor A");
        cast1.add("Actor B");
        cast1.add("Actor A");
        Movie movie1 = new Movie("id1", "title1", genres,
                1992, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                writers,
                10);
        Movie movie2 = new Movie("id1", "title1", genres,
                1997, "description",
                "https://image.com/",
                120,
                directors,
                writers,
                cast1,
                10);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        // Test case 1: Get movies between 1990 and 2000
        List<Movie> result1 = HomeController.getMoviesBetweenYears(movies, 1990, 2000);
        Assertions.assertEquals(2, result1.size());
        Assertions.assertTrue(result1.contains(movie1));
        Assertions.assertTrue(result1.contains(movie2));

    }
}
