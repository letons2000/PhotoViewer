package application.test.com.photoviewerapplication.ui.category;

import android.widget.ImageView;

import application.test.com.photoviewerapplication.BasePresenter;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;

public class CategoryPresenter
        extends BasePresenter<CategoryContract.View, CategoryContract.Navigator>
        implements CategoryContract.Presenter {

    public CategoryPresenter(CategoryContract.View view,
                             CategoryContract.Navigator navigator) {
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
    public void onClickRecipe(ImageView view, PhotoData photoData) {
        navigator.openDetailScreen(view, photoData);
    }
}
