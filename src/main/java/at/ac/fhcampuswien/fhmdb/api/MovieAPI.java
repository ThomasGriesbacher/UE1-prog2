package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Movie;
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

    public static List<Movie> getAllMovies() throws MovieApiException {
        return getAllMovies(null, 0, 0.0, null);
    }

    public static List<Movie> getAllMovies(String query, int releaseYear, double ratingFrom, String genre) throws MovieApiException {
        String url = buildUrl(query, releaseYear, ratingFrom, genre);
        OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "http.agent")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            try (ResponseBody body = response.body()) {
                if (body != null) {
                    Gson gson = new GsonBuilder().create();
                    Type movieListType = new TypeToken<List<Movie>>() {}.getType();
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

    private static String buildUrl(String query, int releaseYear, double ratingFrom, String genre) {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("?");

        if (query != null && !query.isEmpty()) {
            urlBuilder.append("query=").append(query).append("&");
        }
        if (releaseYear > 0) {
            urlBuilder.append("releaseYear=").append(releaseYear).append("&");
        }
        if (ratingFrom > 0) {
            urlBuilder.append("ratingFrom=").append(ratingFrom).append("&");
        }
        if (genre != null && !genre.isEmpty()) {
            urlBuilder.append("genre=").append(genre).append("&");
        }

        return urlBuilder.toString();
    }
}