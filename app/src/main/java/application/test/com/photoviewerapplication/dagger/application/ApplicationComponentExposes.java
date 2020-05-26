package application.test.com.photoviewerapplication.dagger.application;

import application.test.com.photoviewerapplication.dagger.application.module.ApplicationModule;
import application.test.com.photoviewerapplication.dagger.application.module.DataModule;

public interface ApplicationComponentExposes extends ApplicationModule.Exposes,
                                                     DataModule.Exposes {
}
