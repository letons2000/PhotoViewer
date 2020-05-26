package application.test.com.photoviewerapplication.ui.home.di;

import application.test.com.photoviewerapplication.dagger.fragment.FragmentModule;
import application.test.com.photoviewerapplication.dagger.fragment.FragmentScope;
import application.test.com.photoviewerapplication.ui.home.HomeFragment;
import application.test.com.photoviewerapplication.ui.home.HomeNavigator;
import application.test.com.photoviewerapplication.ui.home.HomePresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule extends FragmentModule {

    private HomeFragment homeFragment;

    HomeModule(final HomeFragment homeFragment) {
        super(homeFragment);
        this.homeFragment = homeFragment;
    }

    @Provides
    @FragmentScope
    HomePresenter provideHomePresenter() {
        final HomePresenter homePresenter =
                new HomePresenter(homeFragment,
                        new HomeNavigator(homeFragment));
        homeFragment.getCategoryComponent().inject(homePresenter);
        return homePresenter;
    }
}
