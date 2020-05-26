package application.test.com.photoviewerapplication.ui.detail.di;

import application.test.com.photoviewerapplication.dagger.fragment.FragmentModule;
import application.test.com.photoviewerapplication.dagger.fragment.FragmentScope;
import application.test.com.photoviewerapplication.ui.detail.DetailFragment;
import application.test.com.photoviewerapplication.ui.detail.DetailNavigator;
import application.test.com.photoviewerapplication.ui.detail.DetailPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule extends FragmentModule {

    private DetailFragment detailFragment;

    DetailModule(final DetailFragment detailFragment) {
        super(detailFragment);
        this.detailFragment = detailFragment;
    }

    @Provides
    @FragmentScope
    DetailPresenter provideDetailPresenter() {
        final DetailPresenter detailPresenter =
                new DetailPresenter(detailFragment,
                        new DetailNavigator(detailFragment));
        detailFragment.getDetailComponent().inject(detailPresenter);
        return detailPresenter;
    }
}
