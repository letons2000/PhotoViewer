package application.test.com.photoviewerapplication.dagger.application.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import application.test.com.photoviewerapplication.dagger.application.ForApplication;
import application.test.com.photoviewerapplication.db.AppDatabase;
import application.test.com.photoviewerapplication.model.PhotoRepository;
import application.test.com.photoviewerapplication.model.PhotoRepositoryImpl;
import dagger.Module;
import dagger.Provides;

@Module
public final class DataModule {
    @Provides
    @Singleton
    PhotoRepository providePhotoRepository(final @ForApplication Context context) {
        return new PhotoRepositoryImpl(AppDatabase.getInstance(context.getApplicationContext()));
    }

    public interface Exposes {
        PhotoRepository photoRepository();
    }
}
