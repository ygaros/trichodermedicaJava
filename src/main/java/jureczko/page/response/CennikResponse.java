package jureczko.page.response;

import jureczko.page.objects.Usluga;

import java.util.List;
import java.util.Map;

public class CennikResponse {
    private Map<String, List<Usluga>> cennik;


    public CennikResponse(Map<String, List<Usluga>> cennik) {
        this.cennik = cennik;
    }

    public Map<String, List<Usluga>> getCennik() {
        return cennik;
    }

    public void setCennik(Map<String, List<Usluga>> cennik) {
        this.cennik = cennik;
    }

}
