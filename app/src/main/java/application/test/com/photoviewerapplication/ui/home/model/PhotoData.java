package application.test.com.photoviewerapplication.ui.home.model;

import java.io.Serializable;

public class PhotoData implements Serializable {
    private String photoName;
    private String photoDescription;
    private int photoImage;

    public PhotoData(String photoName, String photoDescription, int photoImage) {
        this.photoName = photoName;
        this.photoDescription = photoDescription;
        this.photoImage = photoImage;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public int getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(int photoImage) {
        this.photoImage = photoImage;
    }
}
