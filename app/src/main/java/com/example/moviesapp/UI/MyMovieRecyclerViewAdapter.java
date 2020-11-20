package com.example.moviesapp.UI;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.MovieDetailsActivity;
import com.example.moviesapp.R;
import com.example.moviesapp.data.local.MovieEntity;

import java.util.List;

import static com.example.moviesapp.data.remote.ApiConstants.IMAGE_API_PREFIX;

public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private List<MovieEntity> mValues;
    Context ctx;

    public MyMovieRecyclerViewAdapter(Context context, List<MovieEntity> items) {
        ctx = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.moviefragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);

        Glide.with(ctx)
                .load(IMAGE_API_PREFIX + holder.mItem.getPosterPath())
                .into(holder.imageViewCover);
    }

    public void setData(List<MovieEntity> movies){
        this.mValues = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if (mValues != null)
            return mValues.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final ImageView imageViewCover;
        public MovieEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageViewCover = view.findViewById(R.id.imageViewCoverDetails);
            //metodo para clickear en el ImageView
            imageViewCover.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(ctx, "asd",Toast.LENGTH_SHORT).show();
            Intent intentDetails = new Intent(ctx, MovieDetailsActivity.class);
            intentDetails.putExtra("movie", mItem);
            ctx.startActivity(intentDetails);
        }
    }
}