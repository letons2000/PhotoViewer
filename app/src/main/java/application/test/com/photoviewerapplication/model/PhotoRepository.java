package application.test.com.photoviewerapplication.model;

import java.util.List;

import io.reactivex.Single;

public interface PhotoRepository {
    enum Category {
        JAPANESE,
        INDIAN,
        THAI,
        AMERICAN,
        MALAYSIAN,
        ALL
    }

    Single<Photo> getPhoto(int id);
    Single<List<Photo>> getCategoryPhotos(PhotoRepository.Category category);
}
