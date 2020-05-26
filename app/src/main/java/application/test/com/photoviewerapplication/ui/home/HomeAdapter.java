package application.test.com.photoviewerapplication.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import application.test.com.photoviewerapplication.R;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> implements Filterable {
    private Context mContext;
    private List<PhotoData> mPhotoList;
    private List<PhotoData> mFilteredPhotoList;
    private PhotoFilter mFilter;
    private HomeClickListener mCategoryClickListener;

    public HomeAdapter(Context mContext, List<PhotoData> photoList, HomeClickListener categoryClickListener) {
        this.mContext = mContext;
        this.mPhotoList = photoList;
        this.mFilteredPhotoList = photoList;
        this.mCategoryClickListener = categoryClickListener;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new HomeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        holder.mImage.setImageResource(mPhotoList.get(position).getPhotoImage());
        holder.mTitle.setText(mPhotoList.get(position).getPhotoName());
        holder.mCardView.setOnClickListener(view -> mCategoryClickListener.onClicked(holder,
                new PhotoData(mPhotoList.get(position).getPhotoName(),
                mPhotoList.get(position).getPhotoDescription(),
                        mPhotoList.get(position).getPhotoImage())));
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new PhotoFilter();
        }
        return mFilter;
    }

    class PhotoFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults result = new FilterResults();

            if (charSequence != null && charSequence.length() > 0) {
                charSequence.toString().toUpperCase();
                ArrayList<PhotoData> folwerList = new ArrayList<PhotoData>();
                for (int i = 0; i < mFilteredPhotoList.size(); i++) {
                    if (mFilteredPhotoList.get(i).getPhotoName().toUpperCase().contains(charSequence)) {
                        PhotoData f = new PhotoData(mFilteredPhotoList.get(i).getPhotoName(),
                                "Test", mFilteredPhotoList.get(i).getPhotoImage());
                        folwerList.add(f);
                    }
                }

                result.count = folwerList.size();
                result.values = folwerList;
            } else {
                result.count = mFilteredPhotoList.size();
                result.values = mFilteredPhotoList;
            }
            return result;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mPhotoList = (ArrayList<PhotoData>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}

