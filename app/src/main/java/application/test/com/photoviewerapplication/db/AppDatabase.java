package application.test.com.photoviewerapplication.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import application.test.com.photoviewerapplication.model.Photo;
import application.test.com.photoviewerapplication.model.source.PhotoConverter;
import application.test.com.photoviewerapplication.model.source.PhotoDao;

@Database(version = 1, entities = {Photo.class}, exportSchema = false)
@TypeConverters({PhotoConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    static private final String dbName = "photo.db";

    public abstract PhotoDao photoDao();

    static private AppDatabase instance;

    static public AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, dbName).build();
        }
        return instance;
    }
}
