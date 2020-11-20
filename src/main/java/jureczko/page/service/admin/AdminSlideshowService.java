package jureczko.page.service.admin;

import jureczko.page.exceptions.ImageNotFoundException;
import jureczko.page.objects.Image;
import jureczko.page.objects.Slideshow;
import jureczko.page.objectsDTO.SlideshowAdminDTO;
import jureczko.page.response.SlideshowPostHandler;
import jureczko.page.service.ImageService;
import jureczko.page.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminSlideshowService {

    private final ImageService imageService;
    private final SlideshowService slideshowService;

    @Autowired
    public AdminSlideshowService(ImageService imageService, SlideshowService slideshowService) {
        this.imageService = imageService;
        this.slideshowService = slideshowService;
    }

    public SlideshowAdminDTO getAllData(){
        Slideshow slideshow = slideshowService.getFirst();
        List<Long> imageId = slideshow.getImages().stream()
                .map(Image::getId).collect(Collectors.toList());
        return new SlideshowAdminDTO(
                new SlideshowPostHandler(slideshow.getDescription(), imageId),
                imageService.getALl());
    }
    public ResponseEntity<Void> createNew(SlideshowPostHandler slideshowPost){
        Slideshow slideshow = slideshowService.getFirst();
        slideshow.setDescription(slideshowPost.getDescription());
        slideshow.setImages(this.getImagesFromHeader(slideshowPost.getSlides()));
        slideshowService.save(slideshow);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private List<Image> getImagesFromHeader(List<Long> dots){
        List<Image> problems = new ArrayList<>();
        dots.forEach(i ->{
                    problems.add(imageService.findById(i));
                });
        return problems;
    }

}
