package jureczko.page.service;

import jureczko.page.data.SlideshowRepository;
import jureczko.page.exceptions.ImageNotFoundException;
import jureczko.page.objects.Slideshow;
import jureczko.page.objectsDTO.SlideshowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SlideshowService {

    private final SlideshowRepository slideshowRepository;


    @Autowired
    public SlideshowService(SlideshowRepository slideshowRepository) {
        this.slideshowRepository = slideshowRepository;
    }

    public List<Slideshow> getAllSlideshows(){
        return slideshowRepository.findAll();
    }

    public Slideshow findByNazwa(String nazwa) throws ImageNotFoundException {
        Optional<Slideshow> optional = slideshowRepository.findByName(nazwa);
        return optional.orElseThrow(()-> new ImageNotFoundException("Zdjęcie o adresie -> '"+nazwa+"' nie istnieje"));

    }
    public Slideshow getFirst(){
        return slideshowRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }
    public SlideshowDTO getSlideShowDTOForGivenListOFImagePaths(List<String> src){
        return new SlideshowDTO(
                src, this.getFirst().getDescription()
        );
    }
    public Slideshow save(Slideshow slideshow){
        return slideshowRepository.save(slideshow);
    }
}
