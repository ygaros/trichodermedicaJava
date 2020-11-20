package jureczko.page.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SlideshowPostHandler {
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    private List<Long> slides;

    public SlideshowPostHandler(String description, List<Long> slides) {
        this.description = description;
        this.slides = slides;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getSlides() {
        return slides;
    }

    public void setSlides(List<Long> slides) {
        this.slides = slides;
    }
}
