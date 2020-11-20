package jureczko.page.service;

import jureczko.page.data.ImageRepository;
import jureczko.page.exceptions.ImageNotFoundException;
import jureczko.page.objects.Image;
import jureczko.page.objectsDTO.Banner;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;
    private final ProzaService prozaService;

    public ImageService(ImageRepository imageRepository,
                        ProzaService prozaService) {
        this.imageRepository = imageRepository;
        this.prozaService = prozaService;
    }

    public List<Image> getALl(){
        return imageRepository.findAll();
    }
    public Image findById(long id) throws ImageNotFoundException{
        return imageRepository.findById(id).orElseThrow(
                () -> new ImageNotFoundException("Zdjecie o id -> '"+id+"' nie istnieje")
        );
    }
    public Image findLogo() throws ImageNotFoundException{
        return this.getALl()
                .stream()
                .filter(i -> i.getPath().contains("Logo"))
                .findFirst().orElseThrow(() -> new ImageNotFoundException("Loga nie znaleziono"));
    }
    public Banner findBaner() throws ImageNotFoundException{
        String tresc = prozaService.getByNazwa("cytat").getTresc();
        return this.getALl()
                .stream()
                .filter(i -> i.getPath().contains("pierwsza"))
                .findFirst()
                .map(i -> new Banner(i.getName(), i.getPath(), tresc))
                .orElseThrow(() -> new ImageNotFoundException("Loga nie znaleziono"));
    }
    public Image findPreparat(){
        return this.imageRepository.findByName("preparaty trychologiczne.jpg")
                .orElseThrow(() -> new ImageNotFoundException("Preparaty doesnt exists"));
    }
}
