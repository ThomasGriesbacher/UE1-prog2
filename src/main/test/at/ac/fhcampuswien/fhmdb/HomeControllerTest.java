package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerTest {
    private final HomeController controller = new HomeController();

    @Test
    void sortAscending(){
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("title1", "description1", ADVENTURE));
        expected.add(new Movie("title2", "description2", BIOGRAPHY));
        expected.add(new Movie("title3", "description3", CRIME));
        controller.sortAscending();
        assertEquals(expected, controller.filteredMovies);
    }

    @Test
    void sortDescending(){
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("title3", "description3", CRIME));
        expected.add(new Movie("title2", "description2", BIOGRAPHY));
        expected.add(new Movie("title1", "description1", ADVENTURE));
        controller.sortDescending();
        assertEquals(expected, controller.filteredMovies);
    }
}