package br.com.patrocine.patrocine.ui.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Grid;
import br.com.patrocine.patrocine.model.Location;
import br.com.patrocine.patrocine.model.Session;

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
            LinearLayout l = (LinearLayout) LayoutInflater.from(holder.view.getContext()).inflate(R.layout.item_session, holder.locations, false);

            LinearLayout locationButtons = l.findViewById(R.id.locationButtons);
            locationButtons.setOrientation(LinearLayout.VERTICAL);
            TextView tvLocationName = l.findViewById(R.id.tvLocationName);

            tvLocationName.setText(location.getName());

            for(Session session : location.getSessions()){
                LinearLayout sessionContainer = new LinearLayout(l.getContext());
                sessionContainer.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(2, 1, 2, 1);

                Button locationStart = new Button(l.getContext());
                locationStart.setText(session.getStart());
                locationStart.setTextColor(l.getResources().getColor(R.color.primaryTextColor));
                locationStart.setTypeface(locationStart.getTypeface(), Typeface.BOLD);
                locationStart.setBackgroundColor(l.getResources().getColor(R.color.sessionStart));
                locationStart.setLayoutParams(params);

                Button sessionCategory = new Button(l.getContext());
                sessionCategory.setText(session.getCategory());
                sessionCategory.setTypeface(sessionCategory.getTypeface(), Typeface.BOLD);
                sessionCategory.setTextColor(l.getResources().getColor(R.color.primaryTextColor));
                sessionCategory.setBackgroundColor(l.getResources().getColor(R.color.sessionCategory));
                sessionCategory.setLayoutParams(params);

                Button sessionLanguage = new Button(l.getContext());
                sessionLanguage.setText(session.getLanguage());
                sessionLanguage.setTypeface(sessionLanguage.getTypeface(), Typeface.BOLD);
                sessionLanguage.setTextColor(l.getResources().getColor(R.color.gray));
                sessionLanguage.setBackgroundColor(l.getResources().getColor(R.color.sessionLanguage));
                sessionLanguage.setLayoutParams(params);


                sessionContainer.addView(locationStart);
                sessionContainer.addView(sessionCategory);
                sessionContainer.addView(sessionLanguage);

                locationButtons.addView(sessionContainer);
            }



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
