package com.example.arpit.moviebrowser;

import com.example.arpit.moviebrowser.model.Movie;
import com.example.arpit.moviebrowser.model.MovieWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arpit on 2017-06-02.
 */

public class JsonParser {

    public MovieWrapper getMovieResultWrapper(String inputJSON) throws JSONException {
        JSONObject topObject = new JSONObject(inputJSON);

        JSONArray results = (JSONArray) topObject.get("results");
        int totalPages = topObject.getInt("total_pages");

        List<Movie> movieList = new ArrayList<>();

        for (int i=0; i< results.length(); i++) {
            JSONObject currentObject = (JSONObject) results.get(i);

            Movie m = new Movie();
            m.setTitle(currentObject.getString("title"));
            m.setVoteAverage(currentObject.getString("vote_average"));
            m.setImageUrl(MovieListConstants.IMAGE_PATH+ MovieListConstants.IMAGE_BACKDROP_SIZE + currentObject.getString("poster_path"));
            movieList.add(m);
        }

        MovieWrapper wrapper = new MovieWrapper();
        wrapper.setMovieList(movieList);
        wrapper.setTotalPages(totalPages);

        return wrapper;
    }
}
