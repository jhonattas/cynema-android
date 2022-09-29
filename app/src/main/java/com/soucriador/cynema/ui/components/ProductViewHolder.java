package com.soucriador.cynema.ui.components;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView itemTitleTextView;
    public TextView itemDescriptionTextView;
    public ImageView itemCoverPhoto;
    public View itemView;

    public ProductViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        //itemCoverPhoto = (ImageView) itemView.findViewById(R.id.exhibitThumbnailNIV);
        //itemTitleTextView = (TextView) itemView.findViewById(R.id.exhibitNameTV);
        //itemDescriptionTextView = (TextView) itemView.findViewById(R.id.exhibitDescriptionTV);
    }

    @Override
    public void onClick(View view) {

    }
}
