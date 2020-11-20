package jureczko.page.response;


import jureczko.page.objectsDTO.Banner;
import jureczko.page.objectsDTO.ProblemLight;
import jureczko.page.objectsDTO.SlideshowDTO;

import java.util.List;

public class HomePageResponse {
    private Banner banner;
    private List<ProblemLight> problems;
    private SlideshowDTO slideshow;
    private String prozas;


    public HomePageResponse(Banner banner, List<ProblemLight> problems, SlideshowDTO slideshow, String prozas) {
        this.problems = problems;
        this.slideshow = slideshow;
        this.prozas = prozas;
        this.banner = banner;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public List<ProblemLight> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemLight> problems) {
        this.problems = problems;
    }

    public SlideshowDTO getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(SlideshowDTO slideshow) {
        this.slideshow = slideshow;
    }

    public String getProzas() {
        return prozas;
    }

    public void setProzas(String prozas) {
        this.prozas = prozas;
    }
}
