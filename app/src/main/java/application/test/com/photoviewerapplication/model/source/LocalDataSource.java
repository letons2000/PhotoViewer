package application.test.com.photoviewerapplication.model.source;

import java.util.ArrayList;
import java.util.List;

import application.test.com.photoviewerapplication.model.Photo;
import application.test.com.photoviewerapplication.model.PhotoRepository;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.SingleSubject;

public class LocalDataSource implements PhotoRepository {

    private PhotoDao photoDao;

    public LocalDataSource(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Override
    public Single<Photo> getPhoto(int id) {
        SingleSubject<Photo> singleSubject = SingleSubject.create();
        Schedulers.io().scheduleDirect(() -> singleSubject.onSuccess(photoDao.getPhoto(id)));
        return singleSubject;
    }

    @Override
    public Single<List<Photo>> getCategoryPhotos(Category category) {
        SingleSubject<List<Photo>> singleSubject = SingleSubject.create();
        Schedulers.io().scheduleDirect(() -> {
            if (category == Category.ALL) {
                List<Photo> photoList = new ArrayList<>();
                photoList.addAll(photoDao.getCategoryPhotos(Category.JAPANESE));
                photoList.addAll(photoDao.getCategoryPhotos(Category.INDIAN));
                photoList.addAll(photoDao.getCategoryPhotos(Category.AMERICAN));
                photoList.addAll(photoDao.getCategoryPhotos(Category.MALAYSIAN));
                photoList.addAll(photoDao.getCategoryPhotos(Category.THAI));
                singleSubject.onSuccess(photoList);
            } else {
                singleSubject.onSuccess(photoDao.getCategoryPhotos(category));
            }
        });
        return singleSubject;
    }
}
