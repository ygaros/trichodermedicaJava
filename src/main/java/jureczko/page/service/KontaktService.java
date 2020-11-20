package jureczko.page.service;

import jureczko.page.data.KontaktRepository;
import jureczko.page.objects.Kontakt;
import jureczko.page.objectsDTO.KontaktDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class KontaktService {
    private final KontaktRepository kontaktRepository;
    @Autowired
    public KontaktService(KontaktRepository kontaktRepository)
    {
        this.kontaktRepository = kontaktRepository;
    }

    public KontaktDTO getKontaktDTO(){
        Optional<Kontakt> kontaktOptional = kontaktRepository.findAll()
                .stream().findFirst();
        if(kontaktOptional.isPresent()){
            Kontakt kontakt = kontaktOptional.get();
            return new KontaktDTO(kontakt.getEmail(),
                    kontakt.getPhone());
        }
        return null;
    }
    public Kontakt getKontakt(){
        return kontaktRepository.findAll().stream()
                .findFirst().orElse(null);
    }
}
