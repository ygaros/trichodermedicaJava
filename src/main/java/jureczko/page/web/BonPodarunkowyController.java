package jureczko.page.web;

import jureczko.page.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bonpodarunkowy")
public class BonPodarunkowyController {

    private final ImageService imageService;

    @Autowired
    public BonPodarunkowyController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<String> getBonPodarunkowy(){
       return new ResponseEntity<>("PODSTRONKA JESZCZE NIE ISTNIEJE",
                HttpStatus.OK);
    }




}

