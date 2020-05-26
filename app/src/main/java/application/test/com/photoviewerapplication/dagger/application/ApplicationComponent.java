package application.test.com.photoviewerapplication.dagger.application;

import javax.inject.Singleton;

import application.test.com.photoviewerapplication.TestApplication;
import application.test.com.photoviewerapplication.dagger.application.module.ApplicationModule;
import application.test.com.photoviewerapplication.dagger.application.module.DataModule;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})

public interface ApplicationComponent
        extends ApplicationComponentInjects, ApplicationComponentExposes {
    final class Initializer {
        static public ApplicationComponent init(final TestApplication testApplication) {
            return DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(testApplication))
                    .dataModule(new DataModule())
                    .build();
        }

        private Initializer() {
        }
    }
}
