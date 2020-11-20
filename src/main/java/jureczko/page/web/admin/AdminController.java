package jureczko.page.web.admin;

import jureczko.page.objects.Image;
import jureczko.page.response.AdminResponse;
import jureczko.page.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ImageService imageService;

    @Autowired
    public AdminController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<AdminResponse> getAll(){
        return new ResponseEntity<>(new AdminResponse(imageService.getALl()),
                HttpStatus.OK);
    }
}
