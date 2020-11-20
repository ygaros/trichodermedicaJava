package jureczko.page.web;

import jureczko.page.response.RootResponse;
import jureczko.page.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RootController {

    private final NavBarService navBarService;
    private final KontaktService kontaktService;
    private final ImageService imageService;
    private final ZabiegService zabiegService;
    private final ProblemService problemService;

    @Autowired
    public RootController(NavBarService navBarService,
                          KontaktService kontaktService,
                          ImageService imageService,
                          ZabiegService zabiegService,
                          ProblemService problemService) {
        this.navBarService = navBarService;
        this.kontaktService = kontaktService;
        this.imageService = imageService;
        this.zabiegService = zabiegService;
        this.problemService = problemService;
    }

    @GetMapping
    public ResponseEntity<RootResponse> getNavNames(){
        return new ResponseEntity<>(new RootResponse(kontaktService.getKontaktDTO(),
                navBarService.getAll(),
                problemService.getListOfDTO(),
                zabiegService.getMapForRoot(),
                imageService.findLogo()),
                HttpStatus.OK);
    }
    @GetMapping("/accesdenied")
    public ResponseEntity<String> accesDenied(){
        return new ResponseEntity<>("Nie masz uprawnień do wyświetlenia tej strony",
                HttpStatus.UNAUTHORIZED);
    }

}
