package jureczko.page.service;

import jureczko.page.data.ProzaRepository;
import jureczko.page.exceptions.ProzaNotFoundException;
import jureczko.page.objects.Proza;
import jureczko.page.response.ProzaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProzaService {

    private final ProzaRepository prozaRepository;

    public ProzaService(ProzaRepository prozaRepository) {
        this.prozaRepository = prozaRepository;
    }

    public List<Proza> getAllProzas(){
        return prozaRepository.findAll();
    }

    public Proza getByNazwa(String nazwa){
        return prozaRepository.findByNazwa(nazwa);
    }

    public String getAllTrescForHomePage(){
        return prozaRepository.findAll().stream()
                .filter(p->!(p.getNazwa().contains("trichoskopia")
                        || p.getNazwa().contains("omnie")
                        || p.getNazwa().contains("cytat")))
                .map(Proza::getTresc).collect(Collectors.joining(" "));
    }

    public void save(ProzaRequest proza) throws ProzaNotFoundException{
        Proza original;
        switch (proza.getType()){
            case "quote":
                original = prozaRepository.findByNazwa("cytat");
                break;
            case "omnie":
                original = prozaRepository.findByNazwa("omnie");
                break;
            case "home":
                original = prozaRepository.findByNazwa("proza 1");
                break;
            case "trichoskopia":
                original = prozaRepository.findByNazwa("trichoskopia");
                break;
            default:
                throw new ProzaNotFoundException("Nieprawidłowe dane tekstowe");

        }
        original.setTresc(proza.getValue());
        prozaRepository.save(original);
    }
}
