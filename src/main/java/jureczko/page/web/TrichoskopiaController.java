package jureczko.page.web;

import jureczko.page.service.ProzaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trichoskopia")
public class TrichoskopiaController {

    private final ProzaService prozaService;

    public TrichoskopiaController(ProzaService prozaRepository) {
        this.prozaService = prozaRepository;
    }

    @GetMapping
    public String getOpisBadania(){
        return prozaService.getByNazwa("trichoskopia").getTresc();
    }
}
