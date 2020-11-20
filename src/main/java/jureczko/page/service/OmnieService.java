package jureczko.page.service;

import jureczko.page.response.OmnieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmnieService {

    private final ProzaService prozaService;
    private final ImageService imageService;

    @Autowired
    public OmnieService(ProzaService prozaService,
                        ImageService imageService) {
        this.prozaService = prozaService;
        this.imageService = imageService;
    }
    public OmnieResponse getOmnieDTO(){
        return new OmnieResponse(imageService.findBaner(),
                prozaService.getByNazwa("omnie").getTresc());
    }
}
