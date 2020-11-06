package com.example.moviesapp.data.remote.model;

import com.example.moviesapp.data.local.MovieEntity;

import java.util.List;

public class MoviesResponse {
    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }

    private List<MovieEntity> results;


}
