package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.data.local.MovieEntity;

import static com.example.moviesapp.data.remote.ApiConstants.IMAGE_API_PREFIX;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent i = getIntent();
        MovieEntity movie = (MovieEntity) i.getSerializableExtra("movie");

        TextView tittle = findViewById(R.id.textViewTittle);
        tittle.setText(movie.getOriginalTitle());

        ImageView cover = findViewById(R.id.imageViewCoverDetails);
        Glide.with(this)
                .load(IMAGE_API_PREFIX + movie.getPosterPath())
                .optionalCenterCrop()
                .into(cover);



    }
}