package at.ac.fhcampuswien.fhmdb;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends TypeAdapter<Movie>{

    @Override
    public void write(JsonWriter out, Movie movie) throws IOException {
        // not needed for de-serialization
    }

    @Override
    public Movie read(JsonReader in) throws IOException {
        Movie movie = new Movie();
        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "id":
                    movie.setId(in.nextString());
                    break;
                case "title":
                    movie.setTitle(in.nextString());
                    break;
                case "genres":
                    movie.setGenres(readGenres(in));
                    break;
                case "releaseYear":
                    movie.setReleaseYear(in.nextInt());
                    break;
                case "description":
                    movie.setDescription(in.nextString());
                    break;
                case "imgUrl":
                    movie.setImgUrl(in.nextString());
                    break;
                case "lengthInMinutes":
                    movie.setLengthInMinutes(in.nextInt());
                    break;
                case "directors":
                    movie.setDirectors(readStringList(in));
                    break;
                case "writers":
                    movie.setWriters(readStringList(in));
                    break;
                case "mainCast":
                    movie.setMainCast(readStringList(in));
                    break;
                case "rating":
                    movie.setRating(in.nextDouble());
                    break;
                default:
                    in.skipValue(); // Ignore unknown fields
                    break;
            }
        }
        in.endObject();
        return movie;
    }
    private ArrayList<String> readStringList(JsonReader in) throws IOException {
        List<String> list = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            list.add(in.nextString());
        }
        in.endArray();
        return (ArrayList<String>) list;
    }
    private static ArrayList<Genre> readGenres(JsonReader in) throws IOException {
        ArrayList<Genre> genres = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            String genre = in.nextString();
            genres.add(Genre.valueOf(genre.toUpperCase())); // assuming genre strings are all upper case
        }
        in.endArray();
        return genres;
    }
}
