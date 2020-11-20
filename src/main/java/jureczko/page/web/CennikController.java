package jureczko.page.web;

import jureczko.page.response.CennikResponse;
import jureczko.page.service.CennikService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cennik")
public class CennikController {

    private final CennikService cennikService;

    public CennikController(CennikService cennikService) {
        this.cennikService = cennikService;
    }

    @GetMapping
    public ResponseEntity<CennikResponse> getCennik(){
        return new ResponseEntity<>(cennikService.getCennik(),
                HttpStatus.OK);
    }

}
