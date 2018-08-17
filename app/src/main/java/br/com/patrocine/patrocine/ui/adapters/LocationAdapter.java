package br.com.patrocine.patrocine.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Location;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private ArrayList<Location> locations;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public LinearLayout sessionBox;
        public TextView tvLocationName;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            sessionBox = (LinearLayout) view;
            tvLocationName = view.findViewById(R.id.tvLocationName);
        }
    }

    public LocationAdapter(ArrayList<Location> locations) {
        this.locations = locations;
    }

    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session, parent, false);
        ViewHolder vh = new ViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Location location = locations.get(position);
        holder.tvLocationName.setText(location.getName());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

}
