package jureczko.page.service;

import jureczko.page.data.UslugaRepository;
import jureczko.page.data.ZabiegRepository;
import jureczko.page.exceptions.ZabiegNotFoundException;
import jureczko.page.objects.Usluga;
import jureczko.page.objects.Zabieg;
import jureczko.page.response.CennikResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@Transactional
public class CennikService {

    private final UslugaRepository uslugaRepository;
    private final ZabiegRepository zabiegRepository;

    public CennikService(UslugaRepository uslugaRepository,
                         ZabiegRepository zabiegRepository) {
        this.uslugaRepository = uslugaRepository;
        this.zabiegRepository = zabiegRepository;
    }

    public List<Usluga> getAllUslugi() {
        return uslugaRepository.findAll();
    }

    public Usluga getById(Long id) {
        return uslugaRepository.findById(id).orElse(null);
    }

    public CennikResponse getCennik(){
        return new CennikResponse(new TreeMap<>(uslugaRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Usluga::getCategory))));
    }
    public void save(Map<String, List<Usluga>> cennik)throws ZabiegNotFoundException{
        uslugaRepository.deleteAll();
        cennik.forEach((s, lista) -> {
            for (Usluga usluga : lista) {
                if (usluga.getZabieg() != null) {
                    Zabieg zabieg = zabiegRepository.findByUrlPath(usluga.getZabieg().getUrlPath())
                            .orElseThrow(() -> new ZabiegNotFoundException("NIe ma takiego zabiegu"));
                    zabieg.setCategory(usluga.getCategory());
                    usluga.setZabieg(zabieg);
                }
                lista.forEach(uslugaRepository::saveAndFlush);
            }
        });
    }

}
