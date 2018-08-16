package br.com.patrocine.patrocine.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Snack;
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener;

import java.util.ArrayList;

public class SnackDataAdapter extends RecyclerView.Adapter<SnackDataAdapter.ViewHolder> {

    private ArrayList<Snack> snacks;
    private OnFragmentInteractionListener listener;

    public SnackDataAdapter(ArrayList<Snack> snacks, OnFragmentInteractionListener listener) {
        this.snacks = snacks;
        this.listener = listener;
    }

    @Override
    public SnackDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new SnackDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SnackDataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.snack = snacks.get(i);
        viewHolder.tv_name.setText(snacks.get(i).getTitle());
        String price = "R$ " + String.valueOf(snacks.get(i).getPrice());
        viewHolder.tv_version.setText(price);
        viewHolder.tv_api_level.setText(snacks.get(i).getIngredients());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onFragmentInteraction(viewHolder.snack);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return snacks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Snack snack;
        private TextView tv_name,tv_version,tv_api_level;
        private View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_name         = view.findViewById(R.id.tv_name);
            tv_version      = view.findViewById(R.id.tv_version);
            tv_api_level    = view.findViewById(R.id.tv_api_level);

        }
    }

}
