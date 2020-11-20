package jureczko.page.response;

import jureczko.page.objects.Image;

import java.util.List;

public class PreparatyResponse {
    private List<Image> pictures;
    private String decription;

    public PreparatyResponse() {
    }

    public PreparatyResponse(List<Image> pictures, String decription) {
        this();
        this.pictures = pictures;
        this.decription = decription;
    }

    public List<Image> getPictures() {
        return pictures;
    }

    public void setPictures(List<Image> pictures) {
        this.pictures = pictures;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
