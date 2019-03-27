package br.com.patrocine.patrocine.ui.adapters;

import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Grid;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {

    private static final String CLASS_NAME = SessionAdapter.class.getSimpleName();
    private ArrayList<Grid> grids;
    int i;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public LinearLayout gridBox;
        public LinearLayout locations;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            gridBox = (LinearLayout) view;
            locations = view.findViewById(R.id.gridSessions);
        }
    }

    public SessionAdapter(ArrayList<Grid> grids) {
        this.grids = grids;
        this.i = 0;
    }

    @Override
    public SessionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        ViewHolder vh = new ViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.locations.removeAllViews();

        while(this.i < grids.size()){

            Log.e(CLASS_NAME, "existem: " + grids.size());
            Log.e(CLASS_NAME, "estou no: " + i);

            LinearLayout l = (LinearLayout) LayoutInflater.from(holder.view.getContext()).inflate(R.layout.item_session, holder.locations, false);
            LinearLayout locationButtons = l.findViewById(R.id.locationButtons);
            locationButtons.setOrientation(LinearLayout.VERTICAL);

            // ROOM
            TextView tvLocation = new TextView(l.getContext());
            tvLocation.setText(grids.get(i).getDay());

            LinearLayout sessionContainer = new LinearLayout(l.getContext());
            sessionContainer.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(2, 1, 2, 1);

            Button locationStart = new Button(l.getContext());
            locationStart.setText(grids.get(i).getHour());
            locationStart.setTextColor(l.getResources().getColor(R.color.primaryTextColor));
            locationStart.setTypeface(locationStart.getTypeface(), Typeface.BOLD);
            locationStart.setBackgroundColor(l.getResources().getColor(R.color.sessionStart));
            locationStart.setLayoutParams(params);

            Button sessionCategory = new Button(l.getContext());
            sessionCategory.setText(grids.get(i).getCategory());
            sessionCategory.setTypeface(sessionCategory.getTypeface(), Typeface.BOLD);
            sessionCategory.setTextColor(l.getResources().getColor(R.color.primaryTextColor));
            sessionCategory.setBackgroundColor(l.getResources().getColor(R.color.sessionCategory));
            sessionCategory.setLayoutParams(params);

            Button sessionLanguage = new Button(l.getContext());
            sessionLanguage.setText(grids.get(i).getLanguage());
            sessionLanguage.setTypeface(sessionLanguage.getTypeface(), Typeface.BOLD);
            sessionLanguage.setTextColor(l.getResources().getColor(R.color.gray));
            sessionLanguage.setBackgroundColor(l.getResources().getColor(R.color.sessionLanguage));
            sessionLanguage.setLayoutParams(params);


            sessionContainer.addView(tvLocation);
            sessionContainer.addView(locationStart);
            sessionContainer.addView(sessionCategory);
            sessionContainer.addView(sessionLanguage);

            locationButtons.addView(sessionContainer);

            if(this.i < grids.size()) {
                holder.locations.addView(l);
                this.i++;
            } else {
                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return grids.size();
    }

}
