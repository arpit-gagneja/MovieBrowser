package com.example.arpit.moviebrowser.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.arpit.moviebrowser.JsonParser;
import com.example.arpit.moviebrowser.MovieListConstants;
import com.example.arpit.moviebrowser.R;
import com.example.arpit.moviebrowser.api.HttpClient;
import com.example.arpit.moviebrowser.model.Movie;
import com.example.arpit.moviebrowser.model.MovieWrapper;
import com.example.arpit.moviebrowser.ui.adapter.MovieListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private int pageNumber = 1;
    private int maxPageNumber = Integer.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_movies_listview);
        final ListView moviesListView = (ListView) findViewById(R.id.moviesListView);
        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        final Context context = this;

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNumber > 1) {
                    pageNumber --;
                }
                getMovieList(context, moviesListView, pageNumber);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNumber < maxPageNumber) {
                    pageNumber++;
                }
                getMovieList(context, moviesListView, pageNumber);
            }
        });


        getMovieList(context, moviesListView, pageNumber);

    }

    private void getMovieList(final Context context, final ListView moviesListView, int pageNumber) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, MovieListConstants.MOVIE_LIST_SOURCE_PATH + pageNumber , null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String jsonResponse = response.toString();
                        JsonParser parser = new JsonParser();
                        try {
                            MovieWrapper wrapper = parser.getMovieResultWrapper(jsonResponse);
                            List<Movie> movieList = wrapper.getMovieList();
                            maxPageNumber = wrapper.getTotalPages();
                            MovieListAdapter movieListAdapter = new MovieListAdapter(context, movieList);
                            moviesListView.setAdapter(movieListAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        HttpClient.getInstance(this.getApplicationContext()).addToRequestQueue(jsObjRequest);
    }
}
