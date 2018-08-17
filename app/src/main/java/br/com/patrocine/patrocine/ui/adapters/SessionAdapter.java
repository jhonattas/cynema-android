package br.com.patrocine.patrocine.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.patrocine.patrocine.R;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {

    private String[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout sessionBox;
        public TextView tvSessionDay;
        public ViewHolder(LinearLayout l) {
            super(l);
            sessionBox = l;
            tvSessionDay = sessionBox.findViewById(R.id.tvSessionDay);
        }
    }

    public SessionAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public SessionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session, parent, false);
        ViewHolder vh = new ViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvSessionDay.setText(mDataset[position]);

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}
