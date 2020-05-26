package application.test.com.photoviewerapplication.ui.home;

import android.widget.ImageView;

import application.test.com.photoviewerapplication.BasePresenter;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;

public class HomePresenter
        extends BasePresenter<HomeContract.View, HomeContract.Navigator>
        implements HomeContract.Presenter {

    public HomePresenter(HomeContract.View view,
                         HomeContract.Navigator navigator) {
        super(view, navigator);
    }

    @Override
    public void activate() {
        super.activate();
    }

    @Override
    public void deactivate() {
        super.deactivate();
  }

    @Override
    public void onClickCategoryRecipe(String title) {
        navigator.openCategoryScreen(title);
    }

    @Override
    public void onClickRecipe(ImageView view, PhotoData photoData) {
        navigator.openDetailScreen(view, photoData);
    }
}
