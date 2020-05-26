package application.test.com.photoviewerapplication.model.source;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import application.test.com.photoviewerapplication.model.Photo;
import application.test.com.photoviewerapplication.model.PhotoRepository;

@Dao
public interface PhotoDao {

    @Query("SELECT * FROM photo_tb WHERE category = :category")
    List<Photo> getCategoryPhotos(PhotoRepository.Category category);

    @Query("SELECT * FROM photo_tb WHERE id = :id")
    Photo getPhoto(int id);
}
