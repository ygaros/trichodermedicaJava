package jureczko.page.web;

import jureczko.page.exceptions.ImageNotFoundException;
import jureczko.page.objects.Problem;
import jureczko.page.objectsDTO.ProblemLight;
import jureczko.page.response.ProblemResponse;
import jureczko.page.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/problem")
public class ProblemController implements Serializable {
    private static final long serialVersionUID = 10L;

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }
    @GetMapping
    public RedirectView showProblem(){
        return new RedirectView("/api/problem/1");
    }

    @GetMapping("/{urlpath}")
    public ResponseEntity<ProblemResponse> showIdProblem(HttpServletRequest request)throws ImageNotFoundException{
        List<ProblemLight> problems = problemService.getListOfDTO();
        Problem problem = problemService.findByUrlPath(request.getRequestURI());
        return new ResponseEntity<>(new ProblemResponse(problem,
                problems), HttpStatus.OK);
    }
}
