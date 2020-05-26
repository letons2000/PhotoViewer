package application.test.com.photoviewerapplication.ui.category;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import application.test.com.photoviewerapplication.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    CategoryViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
    }
}