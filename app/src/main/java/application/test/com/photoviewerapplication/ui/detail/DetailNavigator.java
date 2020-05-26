package application.test.com.photoviewerapplication.ui.detail;

public class DetailNavigator implements DetailContract.Navigator {
    private DetailFragment fragment;
    public DetailNavigator(DetailFragment fragment) {
        this.fragment = fragment;
    }
}
