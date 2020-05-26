package application.test.com.photoviewerapplication.model;

import java.util.List;

import javax.inject.Inject;

import application.test.com.photoviewerapplication.db.AppDatabase;
import application.test.com.photoviewerapplication.model.source.LocalDataSource;
import io.reactivex.Single;

public class PhotoRepositoryImpl implements PhotoRepository {

    private LocalDataSource localDataSource;
    @Inject
    public PhotoRepositoryImpl(AppDatabase appDatabase) {
        localDataSource = new LocalDataSource(appDatabase.photoDao());
    }

    @Override
    public Single<Photo> getPhoto(int id) {
        return localDataSource.getPhoto(id);
    }

    @Override
    public Single<List<Photo>> getCategoryPhotos(Category category) {
        return localDataSource.getCategoryPhotos(category);
    }
}
