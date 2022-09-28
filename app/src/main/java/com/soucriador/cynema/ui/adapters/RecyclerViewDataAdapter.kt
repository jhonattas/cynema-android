package br.com.soucriador.cynema.cynema.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.patrocine.cynema.R
import br.com.patrocine.cynema.model.SectionData
import br.com.patrocine.cynema.ui.interfaces.OnFragmentInteractionListener
import java.util.ArrayList

class RecyclerViewDataAdapter(val mContext: Context, val dataList: ArrayList<SectionData>?, internal var listener: OnFragmentInteractionListener) : RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, null)
        return ItemRowHolder(v)
    }

    override fun onBindViewHolder(itemRowHolder: ItemRowHolder, i: Int) {

        val sectionName = dataList!![i].headerTitle

        val singleSectionItems = dataList[i].allItemsInSection

        itemRowHolder.itemTitle.setText(sectionName)

        val itemListDataAdapter =
            br.com.soucriador.cynema.cynema.ui.adapters.SectionListDataAdapter(
                mContext,
                singleSectionItems,
                listener
            )

        itemRowHolder.recyclerViewList.setHasFixedSize(true)
        itemRowHolder.recyclerViewList.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        itemRowHolder.recyclerViewList.adapter = itemListDataAdapter


        itemRowHolder.recyclerViewList.isNestedScrollingEnabled = false


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

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    inner class ItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var itemTitle: TextView = view.findViewById(R.id.itemTitle) as TextView
        var recyclerViewList: RecyclerView = view.findViewById(R.id.recyclerViewList) as RecyclerView

    }

}
