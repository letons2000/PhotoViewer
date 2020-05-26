package application.test.com.photoviewerapplication.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "photo_tb")
public class Photo implements Serializable {

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "photo_path")
    private String photoPath;

    @ColumnInfo(name = "photo_description")
    private String photoDescription;

    @ColumnInfo(name = "link")
    private String link;

    @ColumnInfo(name = "category")
    private PhotoRepository.Category category;

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public PhotoRepository.Category getCategory() {
        return category;
    }

    public void setCategory(PhotoRepository.Category category) {
        this.category = category;
    }

    public Photo(String name, String path, PhotoRepository.Category category) {
        this.name = name;
        this.photoPath = path;
        //this.category = category;
    }

    public Photo() {
    }
}
