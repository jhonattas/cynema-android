package br.com.patrocine.patrocine.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Movie;
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener;

public class MovieSoonAdapter extends RecyclerView.Adapter<MovieSoonAdapter.MovieViewHolder> {

    public Context context;
    private ArrayList<Movie> movieList;
    private OnFragmentInteractionListener listener;

    public MovieSoonAdapter(Context context, ArrayList<Movie> movieList, OnFragmentInteractionListener listener) {
        this.context = context;
        this.movieList = movieList;
        this.listener = listener;
    }

    @Override
    public MovieSoonAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.movie = movieList.get(position);

        holder.name.setText(holder.movie.getFullTitle());

        Picasso.with(context).load(holder.movie.getImage()).into(holder.thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != listener) {
                    listener.onFragmentInteraction(holder.movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        Movie movie;
        private TextView name;
        private ImageView thumbnail;
        private View mView;
        private CardView cardView;

        public MovieViewHolder(View view) {
            super(view);
            mView = view;
            cardView = mView.findViewById(R.id.card_view);
            name = mView.findViewById(R.id.title);
            thumbnail = mView.findViewById(R.id.thumbnail);
        }
    }
}
