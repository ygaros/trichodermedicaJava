package jureczko.page.response;

import jureczko.page.objects.Image;
import jureczko.page.objects.Problem;
import jureczko.page.objectsDTO.ProblemLight;

import java.util.List;

public class ProblemAdminResponse {
    private List<ProblemLight> menu;
    private List<Image> images;
    private Problem problem;


    public ProblemAdminResponse(Problem problem, List<ProblemLight> menu,
                                List<Image> zdjecia) {
        this.problem = problem;
        this.menu = menu;
        this.images = zdjecia;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<ProblemLight> getMenu() {
        return menu;
    }

    public void setMenu(List<ProblemLight> menu) {
        this.menu = menu;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
