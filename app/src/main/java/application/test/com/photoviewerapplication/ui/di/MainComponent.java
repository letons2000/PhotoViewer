package application.test.com.photoviewerapplication.ui.di;

import application.test.com.photoviewerapplication.dagger.activity.ActivityModule;
import application.test.com.photoviewerapplication.dagger.activity.ActivityScope;
import application.test.com.photoviewerapplication.dagger.application.ApplicationComponent;
import application.test.com.photoviewerapplication.ui.MainActivity;
import application.test.com.photoviewerapplication.ui.MainNavigator;
import application.test.com.photoviewerapplication.ui.MainPresenter;
import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                MainModule.class
        }
)
public interface MainComponent extends ActivityModule.Exposes {

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);

    void inject(MainNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static MainComponent init(final MainActivity mainActivity,
                                         final ApplicationComponent applicationComponent) {
            return DaggerMainComponent.builder()
                    .applicationComponent(applicationComponent)
                    .mainModule(new MainModule(mainActivity))
                    .build();
        }
    }
}
