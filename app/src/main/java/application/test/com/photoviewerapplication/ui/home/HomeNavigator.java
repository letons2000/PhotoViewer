package application.test.com.photoviewerapplication.ui.home;

import android.os.Bundle;
import android.support.transition.Fade;
import android.support.transition.TransitionInflater;
import android.support.transition.TransitionSet;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;

import android.widget.ImageView;

import application.test.com.photoviewerapplication.R;
import application.test.com.photoviewerapplication.ui.category.CategoryFragment;
import application.test.com.photoviewerapplication.ui.detail.DetailFragment;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;

public class HomeNavigator implements HomeContract.Navigator {
    private HomeFragment fragment;
    public HomeNavigator(HomeFragment fragment) {
        this.fragment = fragment;
    }
    @Override
    public void openCategoryScreen(String title) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("CategoryTitle", title);
        categoryFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragment.requireActivity()
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, categoryFragment);
        fragmentTransaction.addToBackStack(HomeFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void openDetailScreen(ImageView view, PhotoData photoData) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle argument = new Bundle();
        argument.putSerializable(DetailFragment.PHOTO_EXTRA, photoData);
        detailFragment.setArguments(argument);
        FragmentTransaction fragmentTransaction = fragment.requireActivity()
                .getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, detailFragment);
//        fragmentTransaction.addSharedElement(view, ViewCompat.getTransitionName(view));
//        fragmentTransaction.addToBackStack(CategoryFragment.TAG);
//        fragmentTransaction.commit();

        // 1. Exit for Previous Fragment
        Fade exitFade = new Fade();
        exitFade.setDuration(300);
        fragment.setExitTransition(exitFade);

        // 2. Shared Elements Transition
        TransitionSet enterTransitionSet = new TransitionSet();
        enterTransitionSet.addTransition(TransitionInflater.from(fragment.getContext()).inflateTransition(android.R.transition.move));
        enterTransitionSet.setDuration(1000);
        enterTransitionSet.setStartDelay(300);
        detailFragment.setSharedElementEnterTransition(enterTransitionSet);

        // 3. Enter Transition for New Fragment
        Fade enterFade = new Fade();
        enterFade.setStartDelay(1300);
        enterFade.setDuration(300);
        detailFragment.setEnterTransition(enterFade);

        fragmentTransaction.addSharedElement(view, ViewCompat.getTransitionName(view));
        fragmentTransaction.replace(R.id.fragment_container, detailFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
