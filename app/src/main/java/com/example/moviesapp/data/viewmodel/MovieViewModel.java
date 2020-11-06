package com.example.moviesapp.data.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.data.MovieRepository;
import com.example.moviesapp.data.local.MovieEntity;
import com.example.moviesapp.data.network.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private final LiveData<Resource<List<MovieEntity>>> popularMovies;
    private MovieRepository movieRepository;

    public MovieViewModel(){
        movieRepository = new MovieRepository();
        popularMovies = movieRepository.getPopularMovies();
    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies(){
        return popularMovies;
    }

}
