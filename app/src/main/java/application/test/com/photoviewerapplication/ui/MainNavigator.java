package application.test.com.photoviewerapplication.ui;

public class MainNavigator implements MainContract.Navigator {
    private MainActivity activity;
    public MainNavigator(MainActivity activity) {
        this.activity = activity;
    }
}
