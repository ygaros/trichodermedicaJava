package jureczko.page.web.admin;

import jureczko.page.objects.Proza;
import jureczko.page.response.ProzaRequest;
import jureczko.page.service.ProzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/proza")
public class AdminProzaController {

    private final ProzaService prozaService;

    @Autowired
    public AdminProzaController(ProzaService prozaService) {
        this.prozaService = prozaService;
    }

    @PostMapping
    public ResponseEntity<Void> updateCreateProza(@RequestBody ProzaRequest proza){
        prozaService.save(proza);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
