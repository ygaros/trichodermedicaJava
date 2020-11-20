package jureczko.page.web.admin;

import jureczko.page.objects.Kontakt;
import jureczko.page.service.admin.AdminKontaktService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/kontakt")
public class AdminKontaktController {

    private final AdminKontaktService adminKontaktService;

    public AdminKontaktController(AdminKontaktService adminKontaktService) {
        this.adminKontaktService = adminKontaktService;
    }

    @GetMapping
    public ResponseEntity<Kontakt> getKontakt(){
        return new ResponseEntity<>(adminKontaktService.getKontakt(),
                HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Kontakt> updateKontakt(@RequestBody Kontakt kontakt){
        return new ResponseEntity<>(adminKontaktService.updateKontakt(kontakt),
                HttpStatus.CREATED);
    }
}
