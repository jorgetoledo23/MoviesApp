package com.example.moviesapp.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.moviesapp.app.MyApp;
import com.example.moviesapp.data.local.MovieDao;
import com.example.moviesapp.data.local.MovieEntity;
import com.example.moviesapp.data.local.MovieRoomDatabase;
import com.example.moviesapp.data.network.NetworkBoundResource;
import com.example.moviesapp.data.network.Resource;
import com.example.moviesapp.data.remote.ApiConstants;
import com.example.moviesapp.data.remote.MovieApiService;
import com.example.moviesapp.data.remote.RequestInterceptor;
import com.example.moviesapp.data.remote.model.MoviesResponse;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;
    private final MovieDao movieDao;

    public MovieRepository(){

        //Local ROOM
        MovieRoomDatabase movieRoomDatabase = Room.databaseBuilder(
                MyApp.getContext(),
                MovieRoomDatabase.class,
                "db_movies"
        ).build();

        movieDao = movieRoomDatabase.getMovieDao();

        //Remote Retrofit
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new RequestInterceptor());
        OkHttpClient cliente = okHttpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.API_BASE_URL)
                .client(cliente)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApiService = retrofit.create(MovieApiService.class);

    }


    public LiveData<Resource<List<MovieEntity>>> getPopularMovies() {
        return new NetworkBoundResource<List<MovieEntity>, MoviesResponse>(){

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                return movieDao.loadMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                return movieApiService.loadPopularMovies();
            }
        }.getAsLiveData();
    }


}
