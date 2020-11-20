package jureczko.page.web.admin;

import jureczko.page.objects.Usluga;
import jureczko.page.response.CennikResponse;
import jureczko.page.service.CennikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/cennik")
public class AdminCennikController {

    private final CennikService cennikService;

    @Autowired
    public AdminCennikController(CennikService cennikService) {
        this.cennikService = cennikService;
    }

    @GetMapping
    public ResponseEntity<CennikResponse> getAll(){
        return new ResponseEntity<>(cennikService.getCennik(),
                HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> updateCennik(@RequestBody Map<String, List<Usluga>> cennik){
        cennikService.save(cennik);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
