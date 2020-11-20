package jureczko.page.response;

import jureczko.page.objects.Image;
import jureczko.page.objects.NavBarHref;
import jureczko.page.objectsDTO.KontaktDTO;
import jureczko.page.objectsDTO.ProblemLight;
import jureczko.page.objectsDTO.ZabiegDTO;

import java.util.List;
import java.util.Map;

public class RootResponse {
    private Image logo;
    private KontaktDTO kontakt;
    private List<NavBarHref> menuNavBar;
    private List<ProblemLight> problems;
    private Map<String, List<ZabiegDTO>> trychologyMenu;


    public RootResponse(KontaktDTO kontakt,
                        List<NavBarHref> menuNavBar,
                        List<ProblemLight> problems,
                        Map<String, List<ZabiegDTO>> trychologyMenu,
                        Image logo) {
        this.kontakt = kontakt;
        this.menuNavBar = menuNavBar;
        this.problems = problems;
        this.trychologyMenu = trychologyMenu;
        this.logo = logo;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public List<ProblemLight> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemLight> problems) {
        this.problems = problems;
    }

    public Map<String, List<ZabiegDTO>> getTrychologyMenu() {
        return trychologyMenu;
    }

    public void setTrychologyMenu(Map<String, List<ZabiegDTO>> trychologyMenu) {
        this.trychologyMenu = trychologyMenu;
    }

    public List<NavBarHref> getMenuNavBar() {
        return menuNavBar;
    }

    public void setMenuNavBar(List<NavBarHref> menuNavBar) {
        this.menuNavBar = menuNavBar;
    }

    public KontaktDTO getKontakt() {
        return kontakt;
    }

    public void setKontakt(KontaktDTO kontakt) {
        this.kontakt = kontakt;
    }
}
