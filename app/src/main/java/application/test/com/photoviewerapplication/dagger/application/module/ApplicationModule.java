package application.test.com.photoviewerapplication.dagger.application.module;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import application.test.com.photoviewerapplication.TestApplication;
import application.test.com.photoviewerapplication.dagger.application.ForApplication;
import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final TestApplication testApplication;

    public ApplicationModule(final TestApplication testApplication) {
        this.testApplication = testApplication;
    }

    @Provides
    @Singleton
    TestApplication provideTestApplication() {
        return testApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideContext() {
        return testApplication;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return testApplication.getResources();
    }

    public interface Exposes {
        TestApplication testApplication();
        @ForApplication
        Context context();
        Resources resources();
    }
}
