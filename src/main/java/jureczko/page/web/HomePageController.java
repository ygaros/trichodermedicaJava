package jureczko.page.web;

import jureczko.page.aop.ForLogging;
import jureczko.page.objects.Image;
import jureczko.page.response.HomePageResponse;
import jureczko.page.objectsDTO.ProblemLight;
import jureczko.page.objectsDTO.SlideshowDTO;
import jureczko.page.service.ImageService;
import jureczko.page.service.ProblemService;
import jureczko.page.service.ProzaService;
import jureczko.page.service.SlideshowService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/home")
public class HomePageController {
    private final ProblemService problemService;
    private final ProzaService prozaService;
    private final SlideshowService slideshowService;
    private final ImageService imageService;

    public HomePageController(ProblemService problemService,
                              ProzaService prozaService,
                              SlideshowService slideshowService,
                              ImageService imageService) {
        this.problemService = problemService;
        this.prozaService = prozaService;
        this.slideshowService = slideshowService;
        this.imageService = imageService;
    }

    @GetMapping
    @ForLogging
    public ResponseEntity<HomePageResponse> showHomePage(){
        String prozas = prozaService.getAllTrescForHomePage();
        List<ProblemLight> problemLights = problemService.getListOfDTO();
        List<String> imageSrc = slideshowService.getFirst().getImages()
                .stream().map(Image::getPath).collect(Collectors.toList());
        SlideshowDTO sDTO = slideshowService.getSlideShowDTOForGivenListOFImagePaths(imageSrc);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new HomePageResponse(imageService.findBaner(), problemLights, sDTO, prozas)
                ,header, HttpStatus.OK);

    }



}
