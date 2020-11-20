package jureczko.page.web.admin;

import jureczko.page.objects.Problem;
import jureczko.page.response.ProblemAdminResponse;
import jureczko.page.service.ImageService;
import jureczko.page.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/problem")
public class AdminProblemController {

    private final ProblemService problemService;
    private final ImageService imageService;

    @Autowired
    public AdminProblemController(ProblemService problemService,
                                  ImageService imageService) {
        this.problemService = problemService;
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemAdminResponse> getProblem(@PathVariable long id){
        return new ResponseEntity<>(new ProblemAdminResponse(problemService.findById(id),
                problemService.getListOfDTO(),
                imageService.getALl()), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> createOrUpdateProblem(@RequestBody Problem problem){
        problemService.save(problem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
