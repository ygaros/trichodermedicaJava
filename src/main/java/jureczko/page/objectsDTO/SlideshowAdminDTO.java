package jureczko.page.objectsDTO;

import jureczko.page.objects.Image;
import jureczko.page.response.SlideshowPostHandler;

import java.util.List;

public class SlideshowAdminDTO {
    private SlideshowPostHandler slideshow;
    private List<Image> images;

    public SlideshowAdminDTO(SlideshowPostHandler SlideshowPostHandler, List<Image> images) {
        this.slideshow = SlideshowPostHandler;
        this.images = images;
    }


    public SlideshowPostHandler getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(SlideshowPostHandler slideshow) {
        this.slideshow = slideshow;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
