package com.example.yara.recyclerview;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static com.nispok.snackbar.R.style.Snackbar;

/**
 * Created by YaRa on 12/27/2016.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private  List<Movie> moviesList;
    private  List<Movie> moviesToRemove;

    public MoviesAdapter(List<Movie> data) {
        moviesList = data;
        moviesToRemove = new ArrayList<>();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(moviesList.get(position).getTitle());
        holder.genre.setText(moviesList.get(position).getGenre());
        holder.year.setText(moviesList.get(position).getYear());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public void onItemRemove(final RecyclerView.ViewHolder viewHolder, final RecyclerView recyclerView) {
        final int adapterPosition = viewHolder.getAdapterPosition();
        final Movie tempMovie = moviesList.get(adapterPosition);

        Log.d("position",adapterPosition+"");

        android.support.design.widget.Snackbar snackbar = android.support.design.widget.Snackbar
                .make(recyclerView, "Movie removed from list", android.support.design.widget.Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        moviesList.add(adapterPosition, tempMovie);
                        notifyItemInserted(adapterPosition);
                        recyclerView.scrollToPosition(adapterPosition);
                        moviesToRemove.remove(tempMovie);
                    }
                });
        snackbar.show();
        moviesList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        moviesToRemove.add(tempMovie);
    }
}