package br.com.patrocine.patrocine.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.SectionData;
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {

    private ArrayList<SectionData> dataList;
    private Context mContext;
    OnFragmentInteractionListener listener;

    public RecyclerViewDataAdapter(Context context, ArrayList<SectionData> dataList, OnFragmentInteractionListener listener) {
        this.dataList = dataList;
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getHeaderTitle();

        ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();

        itemRowHolder.itemTitle.setText(sectionName);

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems, listener);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);


        itemRowHolder.recycler_view_list.setNestedScrollingEnabled(false);


       /*  itemRowHolder.recycler_view_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        //Allow ScrollView to intercept touch events once again.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                // Handle RecyclerView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });*/

       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;
        protected RecyclerView recycler_view_list;

        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);


        }

    }

}
