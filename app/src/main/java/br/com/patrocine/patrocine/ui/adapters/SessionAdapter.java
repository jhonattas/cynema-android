package br.com.patrocine.patrocine.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Grid;
import br.com.patrocine.patrocine.model.Location;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {

    private ArrayList<Grid> grids;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public LinearLayout gridBox;
        public TextView tvSessionDay;
        public LinearLayout locations;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            gridBox = (LinearLayout) view;
            tvSessionDay = view.findViewById(R.id.tvSessionDay);
            locations = view.findViewById(R.id.gridSessions);
        }
    }

    public SessionAdapter(ArrayList<Grid> grids) {
        this.grids = grids;
    }

    @Override
    public SessionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        ViewHolder vh = new ViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Grid grid = grids.get(position);
        String day = "Dia " + grid.getMonthNumber() + " (" + grid.getWeekDay() + ")";
        holder.tvSessionDay.setText(day);

        for(Location location : grid.getLocations()){
            LinearLayout l = (LinearLayout) LayoutInflater.from(holder.view.getContext()).inflate(R.layout.item_grid, holder.locations, false);

            holder.locations.addView(l);
            /*LocationAdapter locationAdapter = new LocationAdapter(grid.getLocations());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(holder.view.getContext());
            holder.locations.setLayoutManager(layoutManager);
            holder.locations.setAdapter(locationAdapter);*/
        }

    }

    @Override
    public int getItemCount() {
        return grids.size();
    }

}
