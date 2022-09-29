package com.soucriador.cynema.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import com.soucriador.cynema.R;
import com.soucriador.cynema.model.Movie;
import com.soucriador.cynema.ui.interfaces.OnFragmentInteractionListener;
import com.soucriador.cynema.model.Movie;
import com.soucriador.cynema.ui.interfaces.OnFragmentInteractionListener;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<Movie> itemsList;
    private Context mContext;
    private OnFragmentInteractionListener listener;

    public SectionListDataAdapter(Context context, ArrayList<Movie> itemsList, OnFragmentInteractionListener listener) {
        this.mContext = context;
        this.itemsList = itemsList;
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        holder.movie = itemsList.get(i);
        holder.tvTitle.setText(holder.movie.getFullTitle());

        Picasso.get().load(holder.movie.getImage()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected Movie movie;
        protected TextView tvTitle;
        protected ImageView itemImage;

        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = view.findViewById(R.id.tvTitle);
            this.itemImage = view.findViewById(R.id.itemImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                    listener.onFragmentInteraction(movie);
                }
            });


        }

    }

}
