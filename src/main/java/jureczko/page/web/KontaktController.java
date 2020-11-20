package jureczko.page.web;

import jureczko.page.aop.ForLogging;
import jureczko.page.objects.Kontakt;
import jureczko.page.response.PostFromMessageDTO;
import jureczko.page.service.KontaktService;
import jureczko.page.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/kontakt")
public class KontaktController {

    private final KontaktService kontaktService;
    private final SenderService senderService;

    @Autowired
    public KontaktController(KontaktService kontaktService,
                             SenderService senderService) {
        this.kontaktService = kontaktService;
        this.senderService = senderService;
    }


    @GetMapping
    public ResponseEntity<Kontakt> getKontakt(){
        return new ResponseEntity<>(kontaktService.getKontakt(),
                HttpStatus.OK);
    }

    @PostMapping
    @ForLogging
    public ResponseEntity<Void> processMessage(@Valid @RequestBody PostFromMessageDTO message, BindingResult result){
        if (!result.hasErrors()) {
            senderService.saveMessageAndUser(message);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
