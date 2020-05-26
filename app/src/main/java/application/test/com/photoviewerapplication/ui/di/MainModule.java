package application.test.com.photoviewerapplication.ui.di;

import application.test.com.photoviewerapplication.dagger.activity.ActivityModule;
import application.test.com.photoviewerapplication.dagger.activity.ActivityScope;
import application.test.com.photoviewerapplication.ui.MainActivity;
import application.test.com.photoviewerapplication.ui.MainNavigator;
import application.test.com.photoviewerapplication.ui.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule extends ActivityModule {

    private MainActivity mainActivity;

    MainModule(final MainActivity mainActivity) {
        super(mainActivity);
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter() {
        final MainPresenter mainPresenter =
                new MainPresenter(mainActivity,
                        new MainNavigator(mainActivity));
        mainActivity.getMainComponent().inject(mainPresenter);
        return mainPresenter;
    }
}
