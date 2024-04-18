package at.ac.fhcampuswien.fhmdb;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class HomeControllerTest {
    private final HomeController controller = new HomeController();

   /*@Test
    void sortAscending(){
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("title1", "description1", Genre.ACTION));
        expected.add(new Movie("title2", "description2", Genre.ADVENTURE));
        expected.add(new Movie("title3", "description3", Genre.BIOGRAPHY));
        expected.add(new Movie("title4", "description4", Genre.COMEDY));
        expected.add(new Movie("title5", "description5", Genre.CRIME));
        expected.add(new Movie("title6", "description6", Genre.DRAMA));
        expected.add(new Movie("title7", "description7", Genre.DOCUMENTARY));
        expected.add(new Movie("title8", "description8", Genre.FAMILY));
        expected.add(new Movie("title9", "description9", Genre.FANTASY));
        expected.add(new Movie("title10", "description10", Genre.HISTORY));
        expected.add(new Movie("title11", "description11", Genre.HORROR));
        expected.add(new Movie("title12", "description12", Genre.MUSICAL));
        expected.add(new Movie("title13", "description13", Genre.MYSTERY));
        expected.add(new Movie("title14", "description14", Genre.ROMANCE));
        expected.add(new Movie("title15", "description15", Genre.SCIENCE_FICTION));
        expected.add(new Movie("title16", "description16", Genre.SPORT));
        expected.add(new Movie("title17", "description17", Genre.THRILLER));
        expected.add(new Movie("title18", "description18", Genre.WAR));
        expected.add(new Movie("title19", "description19", Genre.WESTERN));
        controller.sortAscending();
        assertEquals(expected, controller.filteredMovies);
    }

    @Test
    void sortDescending(){
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("title3", "description3", Genre.CRIME));
        expected.add(new Movie("title2", "description2", Genre.BIOGRAPHY));
        expected.add(new Movie("title1", "description1", Genre.ADVENTURE));
        controller.sortDescending();
        assertEquals(expected, controller.filteredMovies);
    }
*/
        private HomeController homeController = new HomeController();

        @Test
        public void testFilteringByGenre() {
            List<Movie> filteredMovies = homeController.getMoviesFiltered("", Genre.COMEDY);
            assertEquals(5, filteredMovies.size()); // There are 5 comedy movies
        }

        @Test
        public void testSortAscending() {
            homeController.sortAscending();
            List<Movie> sortedMovies = homeController.filteredMovies;
            assertEquals("Annabelle", sortedMovies.get(0).getTitle()); // Annabelle is the first movie in asc sort
        }

        @Test
        public void testSortDescending() {
            homeController.sortDescending();
            List<Movie> sortedMovies = homeController.filteredMovies;
            assertEquals("Titanic", sortedMovies.get(0).getTitle());
        }


}
