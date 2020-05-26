package application.test.com.photoviewerapplication.ui.category;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import application.test.com.photoviewerapplication.R;
import application.test.com.photoviewerapplication.ui.detail.DetailFragment;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;

public class CategoryNavigator implements CategoryContract.Navigator {
    private CategoryFragment fragment;
    public CategoryNavigator(CategoryFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void openDetailScreen(ImageView view, PhotoData photoData) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle argument = new Bundle();
        argument.putSerializable(DetailFragment.PHOTO_EXTRA, photoData);
        detailFragment.setArguments(argument);
        FragmentTransaction fragmentTransaction = fragment.requireActivity()
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detailFragment);
        fragmentTransaction.addSharedElement(view, ViewCompat.getTransitionName(view));
        fragmentTransaction.addToBackStack(CategoryFragment.TAG);
        fragmentTransaction.commit();
    }
}
