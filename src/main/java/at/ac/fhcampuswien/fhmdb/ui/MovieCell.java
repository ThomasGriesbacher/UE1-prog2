package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label title_info = new Label();
    private final HBox title_wrapper = new HBox(title, title_info);
    private final Label detail = new Label();
    private final ImageView imgView = new ImageView();
    private final VBox info_wrapper = new VBox(title_wrapper, detail);
    private final HBox layout = new HBox(imgView, info_wrapper);


    public static String doubleToAsciiStars(double value) {
        int numFullStars = (int) Math.round(value);
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < numFullStars; i++) stars.append("★");
        int numEmptyStars = Math.max(10 - numFullStars, 0);
        for (int i = 0; i < numEmptyStars; i++) stars.append("☆");
        return stars.toString().trim();
    }

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle() + "  ");
            title_info.setText(doubleToAsciiStars(movie.getRating()) + " • " + movie.getLengthInMinutes() + "min" + " • " + movie.getReleaseYear());
            String movieDetails = (movie.getDescription() != null ? movie.getDescription() : "No description available")
                    + "\nGenres: " + movie.getGenresAsString()
                    + "\nCast: " + movie.getMainCastAsString()
                    + "\nDirectors: " + movie.getDirectorsAsString()
                    + "\nWriters: " + movie.getWritersAsString();

            detail.setText(movieDetails);

            /*imgView.setImage(new Image(movie.getImgUrl()));
            imgView.setFitHeight(200);
            imgView.setPreserveRatio(true);*/

            // color scheme
            title.getStyleClass().add("move-cell-title");
            imgView.getStyleClass().add("move-cell-img");
            layout.setBackground(new Background(new BackgroundFill(Color.web("beige"), null, null)));

            // layout
            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }
}

