package jureczko.page.service.admin;

import jureczko.page.data.KontaktRepository;
import jureczko.page.data.OpenHourRepository;
import jureczko.page.objects.Kontakt;
import org.springframework.stereotype.Service;

@Service
public class AdminKontaktService {
    private final KontaktRepository kontaktRepository;
    private final OpenHourRepository openHourRepository;

    public AdminKontaktService(KontaktRepository kontaktRepository,
                               OpenHourRepository openHourRepository) {
        this.kontaktRepository = kontaktRepository;
        this.openHourRepository = openHourRepository;
    }

    public Kontakt getKontakt(){
        return kontaktRepository.findAll().get(0);
    }

    public Kontakt updateKontakt(Kontakt kontakt){
        kontaktRepository.deleteAll();
        openHourRepository.deleteAll();
        openHourRepository.saveAll(kontakt.getOpenHours());
        return kontaktRepository.save(kontakt);
    }

}
