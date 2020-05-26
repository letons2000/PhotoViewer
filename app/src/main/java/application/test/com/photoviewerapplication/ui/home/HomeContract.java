package application.test.com.photoviewerapplication.ui.home;

import android.widget.ImageView;

import application.test.com.photoviewerapplication.BaseNavigator;
import application.test.com.photoviewerapplication.BaseView;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;

public interface HomeContract {

    interface View extends BaseView {
    }

    interface Presenter {
        void onClickCategoryRecipe(String title);
        void onClickRecipe(ImageView view, PhotoData photoData);
    }

    interface Navigator extends BaseNavigator {
        void openCategoryScreen(String title);
        void openDetailScreen(ImageView view, PhotoData photoData);
    }
}
