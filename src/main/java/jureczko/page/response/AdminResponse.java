package jureczko.page.response;

import jureczko.page.objects.Image;

import java.util.List;

public class AdminResponse {
    private List<Image> images;

    public AdminResponse() {
    }

    public AdminResponse(List<Image> images) {
        this();
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
