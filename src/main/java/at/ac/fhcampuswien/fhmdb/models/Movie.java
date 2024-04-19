package at.ac.fhcampuswien.fhmdb.models;

import java.util.*;
import static at.ac.fhcampuswien.fhmdb.models.Genre.*;

public class Movie {
    private String id;

    private String title;

    private ArrayList<Genre> genres;

    private int releaseYear;

    private String description;

    private String imgURL;

    private int lengthInMinutes;

    private ArrayList<String> directors;

    private ArrayList<String> writers;

    private ArrayList<String> mainCast;

    private double rating;

    //Movie constructor
    //getter methods
    //setter methods

    public Movie (String id, String title, ArrayList<Genre> genres, int releaseYear, String description, String imgURL, int lengthInMinutes, ArrayList<String> directors, ArrayList<String> writers, ArrayList<String> mainCast,double rating)
    {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.description = description;
        this.imgURL = imgURL;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }

    public Movie() {

    }

    public String getId() { return id;}
    public String getTitle() {return title;}
    public int getReleaseYear() {return releaseYear;}
    public String getDescription() {return description;}
    public String getImgURL() {return imgURL;}
    public int getLengthInMinutes() {return lengthInMinutes;}
    public double getRating() {return rating;}
    public ArrayList<String> getMainCast() {return mainCast;}
    public ArrayList<String> getDirectors() {return directors;}
    public ArrayList<Genre> getGenres(){
        return genres;
    };

    public String getGenresAsString() {
        return genres.stream().map(Enum::name).reduce((a, b) -> a + ", " + b).orElse("No Genre");

    }
    public String getDirectorsAsString() {
        return directors.stream().reduce((a, b) -> a + ", " + b).orElse("n/a");
    }
    public String getWritersAsString() {
        return writers.stream().reduce((a, b) -> a + ", " + b).orElse("No Genre");
    }
    public String getMainCastAsString() {
        return mainCast.stream().reduce((a, b) -> a + ", " + b).orElse("No Genre");
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgURL = imgURL;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public void setWriters(ArrayList<String> writers) {
        this.writers = writers;
    }

    public void setMainCast(ArrayList<String> mainCast) {
        this.mainCast = mainCast;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}



   /* obsolete code from Exercise 1, no APIs back then

    private final String title;
    private final String description;

    //Properties: Genres added
    private final EnumSet<Genre> genres;*/

   /* public Movie(String title, String description, Genre... genres) {
        this.title = title;
        this.description = description;
        this.genres = EnumSet.copyOf(Arrays.asList(genres));
    }

    public String getTitle() {

        return title;
    }
    public String getDescription() {

        return description;
    }
    //Properties: Genres added
    public EnumSet<Genre> getGenres() {
        return genres;
    }

    *//*public static List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();
        //  add some movie titles + description + Genre
        movies.add(new Movie("Pi", "A paranoid mathematician searches for a key number that will unlock the universal patterns found in nature" ,DRAMA, HORROR, MYSTERY));
        movies.add(new Movie("Kill Bill Vol.1","After awakening from a four-year coma, a former assassin wreaks vengeance on the team of assassins who betrayed her",ACTION,CRIME,THRILLER));
        movies.add(new Movie("Her", "In a near future, a lonely writer develops an unlikely relationship with an operating system designed to meet", DRAMA, ROMANCE, SCIENCE_FICTION));
        movies.add(new Movie("Everything Everywhere All at once", "A middle-aged Chinese immigrant is swept up into an insane adventure in which she alone can save existence by exploring other universes and connecting with the lives she could have led", ACTION, ADVENTURE, COMEDY));
        movies.add(new Movie("Annabelle", "A couple begins to experience terrifying supernatural occurrences involving a vintage doll shortly after their home is invaded by satanic cultists", HORROR, MYSTERY, THRILLER));
        movies.add(new Movie("Barbie", "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.", ADVENTURE, COMEDY, FANTASY));
        movies.add(new Movie("Titanic", "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.", DRAMA, HISTORY, ROMANCE));
        movies.add(new Movie("Mirrors", "An ex-cop and his family are the target of an evil force that is using mirrors as a gateway into their home.", HORROR, MYSTERY));
        movies.add(new Movie("Napoleon", "An epic that details the chequered rise and fall of French Emperor Napoleon Bonaparte and his relentless journey to power through the prism of his addictive, volatile relationship with his wife, Josephine", ACTION, ADVENTURE, BIOGRAPHY, HISTORY, WAR));
        movies.add(new Movie("Grease", "Good girl Sandy Olsson and greaser Danny Zuko fell in love over the summer. When they unexpectedly discover they're now in the same high school, will they be able to rekindle their romance?", COMEDY, MUSICAL, ROMANCE));
        movies.add(new Movie("Spider Man 2", "Peter Parker is beset with troubles in his failing personal life as he battles a former brilliant scientist named Otto Octavius.", ACTION, ADVENTURE, SCIENCE_FICTION));
        movies.add(new Movie("Passengers", "A malfunction in a sleeping pod on a spacecraft traveling to a distant colony planet wakes one passenger 90 years early.", SCIENCE_FICTION, DRAMA, ROMANCE));
        movies.add(new Movie("The Karate Kid", "Work causes a single mother to move to China with her young son; in his new home, the boy embraces kung fu, taught to him by a master.", ACTION, DRAMA, SPORT));
        movies.add(new Movie("Dunkirk", "Allied soldiers from Belgium, the British Commonwealth and Empire, and France are surrounded by the German Army and evacuated during a fierce battle in World War II.", ACTION, DRAMA, WAR, HISTORY));
        movies.add(new Movie("Spirited Away", "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches and spirits, a world where humans are changed into beasts.",  ANIMATION, ADVENTURE, FANTASY));
        movies.add(new Movie("The Fountain", "As a modern-day scientist, Tommy is struggling with mortality, desperately searching for the medical breakthrough that will save the life of his cancer-stricken wife, Izzi", DRAMA, MYSTERY, ROMANCE));
        movies.add(new Movie("Mr. and Mrs. Smith", "A bored married couple is surprised to learn that they are both assassins hired by competing agencies to kill each other.", ACTION, COMEDY, CRIME));
        movies.add(new Movie("The Fifth Element", "In the colorful future, a cab driver unwittingly becomes the central figure in the search for a legendary cosmic weapon to keep Evil and Mr. Zorg at bay.", ACTION, ADVENTURE, SCIENCE_FICTION));
        movies.add(new Movie("Hercules", "The son of Zeus and Hera is stripped of his immortality as an infant and must become a true hero in order to reclaim it.", ANIMATION, ADVENTURE, COMEDY));
        movies.add(new Movie("Renaissance: A Film by Beyoncé", "Beyoncé in performance at her record-breaking RENAISSANCE World Tour and the creative mastermind behind it.", DOCUMENTARY, MUSICAL));

        return movies;
    }
*//*
    public static List<Movie> getMoviesByGenre(List<Movie> movies, Genre filter) {
        if (filter == NONE) return movies;
        return movies.stream().filter(movie -> movie.getGenres().contains(filter)).toList();
    }

    @Override
    public int compareTo(Movie o) {
        return this.title.compareTo(o.title);
    }

    public static List<Movie> searchTitle(String title, List<Movie> movieList) {
        if(Objects.equals(title, "") || Objects.equals(title, " ")) {
            return movieList;
        }

        return movieList.stream().filter(movie -> movie.getTitle().contains(title)).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(genres, movie.genres);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, description, genres);
    }
}*/