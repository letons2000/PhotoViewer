package application.test.com.photoviewerapplication.ui.detail.di;

import application.test.com.photoviewerapplication.dagger.application.ApplicationComponent;
import application.test.com.photoviewerapplication.dagger.fragment.FragmentModule;
import application.test.com.photoviewerapplication.dagger.fragment.FragmentScope;
import application.test.com.photoviewerapplication.ui.detail.DetailFragment;
import application.test.com.photoviewerapplication.ui.detail.DetailNavigator;
import application.test.com.photoviewerapplication.ui.detail.DetailPresenter;
import dagger.Component;

@FragmentScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {DetailModule.class}
)
public interface DetailComponent extends FragmentModule.Exposes {

    void inject(DetailFragment fragment);

    void inject(DetailPresenter presenter);

    void inject(DetailNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static DetailComponent init(final DetailFragment detailFragment,
                                           final ApplicationComponent applicationComponent) {
            return DaggerDetailComponent.builder()
                    .applicationComponent(applicationComponent)
                    .detailModule(new DetailModule(detailFragment))
                    .build();
        }
    }
}
