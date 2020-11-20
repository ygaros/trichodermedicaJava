package jureczko.page.web;

import jureczko.page.objects.Image;
import jureczko.page.response.PreparatyResponse;
import jureczko.page.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/preparatytrychologiczne")
public class PreparatyController {

    private final ImageService imageService;

    @Autowired
    public PreparatyController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<PreparatyResponse> getPreparaty(){
        List<Image> lista =  new ArrayList<>();
        lista.add(imageService.findPreparat());
        return new ResponseEntity<>(new PreparatyResponse(lista, "PLACEHOLDER DLA PODSTRONY BON POPORODOWY"), HttpStatus.OK);
    }
}
