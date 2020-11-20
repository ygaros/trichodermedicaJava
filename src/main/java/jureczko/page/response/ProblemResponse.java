package jureczko.page.response;

import jureczko.page.objects.Problem;
import jureczko.page.objectsDTO.ProblemLight;

import java.util.List;

public class ProblemResponse {
    private List<ProblemLight> menu;
    private Problem problem;


    public ProblemResponse(Problem problem, List<ProblemLight> menu) {
        this.problem = problem;
        this.menu = menu;
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
