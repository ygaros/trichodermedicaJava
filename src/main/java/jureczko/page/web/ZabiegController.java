package jureczko.page.web;

import jureczko.page.aop.ForLogging;
import jureczko.page.exceptions.ZabiegNotFoundException;
import jureczko.page.objects.Zabieg;
import jureczko.page.response.TrychologiaResponse;
import jureczko.page.service.ZabiegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/zabieg")
public class ZabiegController {

    private final ZabiegService zabiegService;

    @Autowired
    public ZabiegController(ZabiegService zabiegService) {
        this.zabiegService = zabiegService;
    }

    @GetMapping
    public ResponseEntity<Set<String>> getMenu(){
        return new ResponseEntity<>(zabiegService.getAllCategories(),
                HttpStatus.OK);
    }
    @GetMapping("/{category}")
    public ResponseEntity<List<Zabieg>> getListForCategory(
            @PathVariable String category){
        return new ResponseEntity<>(zabiegService.getAllByCategory(category),
                HttpStatus.OK);
    }

    @GetMapping("/{category}/{name}")
    @ForLogging
    public ResponseEntity<TrychologiaResponse> getTrychologia(
            HttpServletRequest request
    ) throws ZabiegNotFoundException {
        return new ResponseEntity<>(new TrychologiaResponse(
                zabiegService.getALlDTO(),
                zabiegService.getByUrl(request.getRequestURI())
        ), HttpStatus.OK);
    }
}
