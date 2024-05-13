package at.ac.fhcampuswien.fhmdb.enums;

public enum UIComponent {
    HOME("/markup/home.fxml"),
    WATCHLIST("/markup/watchlist.fxml"),
    MOVIELIST("/markup/movie-list.fxml");

    public final String path;

    UIComponent(String path){
        this.path = path;
    }
}