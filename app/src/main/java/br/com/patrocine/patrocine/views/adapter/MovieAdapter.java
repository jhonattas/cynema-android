package br.com.patrocine.patrocine.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Movie;
import br.com.patrocine.patrocine.views.interfaces.OnMovieClickListener;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    public Context context;
    private ArrayList<Movie> movieList;
    private OnMovieClickListener listener;

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        Movie movie;
        private TextView name, duration;
        private ImageView thumbnail;
        private View mView;

        public MovieViewHolder(View view) {
            super(view);
            mView = view;
            name = view.findViewById(R.id.title);
            duration = view.findViewById(R.id.duration);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }


    public MovieAdapter(Context context, ArrayList<Movie> movieList, OnMovieClickListener listener) {
        this.context = context;
        this.movieList = movieList;
        this.listener = listener;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.movie = movieList.get(position);

        holder.name.setText(holder.movie.getTitle());
        String description = holder.movie.getDuration() + " minutos | " + holder.movie.getGenre();
        holder.duration.setText(description);

        Glide.with(context)
                .load(holder.movie.getImage())
                .into(holder.thumbnail);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMovieClick(holder.movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
