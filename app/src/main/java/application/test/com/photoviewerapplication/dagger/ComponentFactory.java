package application.test.com.photoviewerapplication.dagger;

import application.test.com.photoviewerapplication.TestApplication;
import application.test.com.photoviewerapplication.dagger.application.ApplicationComponent;

public final class ComponentFactory {
    private ComponentFactory() {
    }

    public static ApplicationComponent createApplicationComponent(
            final TestApplication testApplication) {
        return ApplicationComponent.Initializer.init(testApplication);
    }
}
