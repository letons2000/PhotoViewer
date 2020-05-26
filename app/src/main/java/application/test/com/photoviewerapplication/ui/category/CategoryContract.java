package application.test.com.photoviewerapplication.ui.category;

import android.widget.ImageView;

import application.test.com.photoviewerapplication.BaseNavigator;
import application.test.com.photoviewerapplication.BaseView;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;

public interface CategoryContract {

    interface View extends BaseView {
    }

    interface Presenter {
        void onClickRecipe(ImageView view, PhotoData photoData);
    }

    interface Navigator extends BaseNavigator {
        void openDetailScreen(ImageView view, PhotoData photoData);
    }
}
