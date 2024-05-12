package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.MovieAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MovieAPI {

    public static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";


    public List<Movie> getAllMovies(String endpoint) throws MovieApiException{

        OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(endpoint)
                .header("User-Agent", "http.agent")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response); //different reason- delete IOException
            }

            try (ResponseBody body = response.body()) { //Json Format
                if (body != null) {
                    Gson gson = new GsonBuilder().create();
                    Type movieListType = new TypeToken<List<Movie>>() {
                    }.getType();
                    String json = body.string();
                    return gson.fromJson(json, movieListType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new MovieApiException(e.getMessage());
        }

        return null;
    }

    public List<Movie> getMoviesByQuery(String endpoint, String query, int releaseYear, double ratingFrom, String genre) {


        String baseUrl = endpoint + "?";

        //
        Type movieListType = new TypeToken<List<Movie>>() {
        }.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Movie.class, new MovieAdapter())
                .create();

        OkHttpClient httpClient = new OkHttpClient();

        if (query != null) baseUrl += "query=" + query + "&";
        if (releaseYear > 0) baseUrl += "releaseYear=" + String.valueOf(releaseYear) + "&";
        if (ratingFrom > 0) baseUrl += "ratingFrom=" + String.valueOf(ratingFrom) + "&";
        if (genre != null) baseUrl += "genre=" + genre + "&";

        Request request = new Request.Builder()
                .url(baseUrl)
                .header("User-Agent", "http.agent")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            try (ResponseBody body = response.body()) {
                if (body != null) {
                    String json = body.string();
                    return gson.fromJson(json, movieListType);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ignored) {
        }

        return null;
    }
}