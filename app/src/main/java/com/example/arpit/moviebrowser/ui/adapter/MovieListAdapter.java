package com.example.arpit.moviebrowser.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.arpit.moviebrowser.R;
import com.example.arpit.moviebrowser.model.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arpit on 2017-06-02.
 */

public class MovieListAdapter extends ArrayAdapter<Movie> {
    public MovieListAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_movie_list, parent, false);
        }
        // Lookup view for data population
        SimpleDraweeView movieImage = (SimpleDraweeView) convertView.findViewById(R.id.movie_image_view);
        TextView movieTextView = (TextView) convertView.findViewById(R.id.movieName);
        TextView movieVoteAvg = (TextView) convertView.findViewById(R.id.movieVoteAvg);
        // Populate the data into the template view using the data object
        movieImage.setImageURI(movie.getImageUrl());
        movieTextView.setText(movie.getTitle());
        movieVoteAvg.setText(movie.getVoteAverage());
        // Return the completed view to render on screen
        return convertView;
    }
}
