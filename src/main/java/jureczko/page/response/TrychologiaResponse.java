package jureczko.page.response;

import jureczko.page.objects.Zabieg;
import jureczko.page.objectsDTO.ZabiegDTO;

import java.util.List;

public class TrychologiaResponse {

    private List<ZabiegDTO> menu;
    private Zabieg zabieg;


    public TrychologiaResponse(List<ZabiegDTO> menu, Zabieg zabieg) {
        this.menu = menu;
        this.zabieg = zabieg;
    }

    public List<ZabiegDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<ZabiegDTO> menu) {
        this.menu = menu;
    }

    public Zabieg getZabieg() {
        return zabieg;
    }

    public void setZabieg(Zabieg zabieg) {
        this.zabieg = zabieg;
    }
}
