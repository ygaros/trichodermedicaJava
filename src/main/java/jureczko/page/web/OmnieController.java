package jureczko.page.web;

import jureczko.page.aop.ForLogging;
import jureczko.page.response.OmnieResponse;
import jureczko.page.service.OmnieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/omnie")
public class OmnieController {

    private final OmnieService omnieService;

    @Autowired
    public OmnieController(OmnieService omnieService) {
        this.omnieService = omnieService;
    }

    @GetMapping
    @ForLogging
    public ResponseEntity<OmnieResponse> getOmnie(){
        return new ResponseEntity<>(omnieService.getOmnieDTO(),
                HttpStatus.OK);
    }
}
