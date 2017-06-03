package com.example.arpit.moviebrowser.model;

import java.util.List;

/**
 * Created by arpit on 2017-06-02.
 */

public class MovieWrapper {

    private List<Movie> movieList;
    private int totalPages;

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
