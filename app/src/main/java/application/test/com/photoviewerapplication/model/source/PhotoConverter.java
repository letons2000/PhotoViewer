package application.test.com.photoviewerapplication.model.source;

import android.arch.persistence.room.TypeConverter;

import application.test.com.photoviewerapplication.model.PhotoRepository;

public class PhotoConverter {
    @TypeConverter
    public static PhotoRepository.Category categoryFromString(String value) {
        switch (value) {
            case "japanese":
                return PhotoRepository.Category.JAPANESE;
            case "indian":
                return PhotoRepository.Category.INDIAN;
            case "malaysian":
                return PhotoRepository.Category.MALAYSIAN;
            case "thai":
                return PhotoRepository.Category.THAI;
            case "american":
                return PhotoRepository.Category.AMERICAN;
            case "all":
                return PhotoRepository.Category.ALL;
            default:
                return null;
        }
    }

    @TypeConverter
    public static String categoryToString(PhotoRepository.Category category) {
        switch (category) {
            case JAPANESE:
                return "japanese";
            case INDIAN:
                return "indian";
            case MALAYSIAN:
                return "malaysian";
            case THAI:
                return "thai";
            case AMERICAN:
                return "american";
            case ALL:
                return "all";
            default:
                return null;
        }
    }
}
