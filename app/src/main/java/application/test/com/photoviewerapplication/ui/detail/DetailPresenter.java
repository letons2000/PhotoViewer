package application.test.com.photoviewerapplication.ui.detail;

import application.test.com.photoviewerapplication.BasePresenter;

public class DetailPresenter
        extends BasePresenter<DetailContract.View, DetailContract.Navigator>
        implements DetailContract.Presenter {

    public DetailPresenter(DetailContract.View view,
                           DetailContract.Navigator navigator) {
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

}
