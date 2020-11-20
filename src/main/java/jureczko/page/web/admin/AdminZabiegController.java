package jureczko.page.web.admin;

import jureczko.page.objects.Zabieg;
import jureczko.page.objectsDTO.ZabiegDTO;
import jureczko.page.service.ZabiegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/zabieg")
public class AdminZabiegController {

    private final ZabiegService zabiegService;

    @Autowired
    public AdminZabiegController(ZabiegService zabiegService) {
        this.zabiegService = zabiegService;
    }

    @GetMapping
    public ResponseEntity<List<ZabiegDTO>> getAll(){
        return new ResponseEntity<>(zabiegService.getALlDTO(),
                HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Zabieg> getById(@PathVariable long id){
        return new ResponseEntity<>(zabiegService.getById(id),
                HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> setNewValue(@RequestBody Zabieg zabieg){
        zabiegService.save(zabieg);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

