package br.com.patrocine.patrocine.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Pizza;
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener;

import java.util.ArrayList;

public class PizzaDataAdapter extends RecyclerView.Adapter<PizzaDataAdapter.ViewHolder> {

    private ArrayList<Pizza> pizzas;
    private OnFragmentInteractionListener listener;

    public PizzaDataAdapter(ArrayList<Pizza> pizzas, OnFragmentInteractionListener listener) {
        this.pizzas = pizzas;
        this.listener = listener;
    }

    @Override
    public PizzaDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PizzaDataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.pizza = pizzas.get(i);
        viewHolder.tv_name.setText(pizzas.get(i).getTitle());
        String price = "R$ " + String.valueOf(pizzas.get(i).getPrice());
        viewHolder.tv_version.setText(price);
        viewHolder.tv_api_level.setText(pizzas.get(i).getIngredients());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onFragmentInteraction(viewHolder.pizza);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Pizza pizza;
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