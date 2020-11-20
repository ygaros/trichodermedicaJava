package jureczko.page.other;

import jureczko.page.objects.Problem;
import jureczko.page.objects.Zabieg;

import java.util.ArrayList;
import java.util.List;

public class Holder {
    private List<Problem> problems = new ArrayList<>();
    private List<Zabieg> zabiegs = new ArrayList<>();

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public List<Zabieg> getZabiegs() {
        return zabiegs;
    }

    public void setZabiegs(List<Zabieg> zabiegs) {
        this.zabiegs = zabiegs;
    }
}
