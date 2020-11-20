package jureczko.page.service;

import jureczko.page.data.ZabiegRepository;
import jureczko.page.exceptions.ZabiegNotFoundException;
import jureczko.page.objects.Usluga;
import jureczko.page.objects.Zabieg;
import jureczko.page.objectsDTO.ZabiegDTO;
import jureczko.page.other.FileReader;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ZabiegService {

    private final ZabiegRepository zabiegRepository;

    public ZabiegService(ZabiegRepository zabiegRepository) {
        this.zabiegRepository = zabiegRepository;
    }

    public List<Zabieg> getAll(){
        return zabiegRepository.findAll();
    }

    public Zabieg getById(Long id) throws ZabiegNotFoundException {
        Optional<Zabieg> optional=  zabiegRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new ZabiegNotFoundException("Zabieg o numerze -> '"+id+"' nie istnieje");

    }
    public Zabieg getByUrl(String urlPath) throws ZabiegNotFoundException {
        Optional<Zabieg> optional=  zabiegRepository.findByUrlPath(urlPath.replace("/api", ""));
        if(optional.isPresent()){
            return optional.get();
        }
        throw new ZabiegNotFoundException("Zabieg o numerze -> '"+urlPath+"' nie istnieje");

    }
    public List<ZabiegDTO> getALlDTO(){
        return zabiegRepository.findAll().stream()
                .map(z -> new ZabiegDTO(z.getUrlPath(), z.getName()))
                .collect(Collectors.toList());
    }
    public Map<String, List<ZabiegDTO>> getMapForRoot(){
        Map<String, List<ZabiegDTO>> mapDTO = new HashMap<>();
        zabiegRepository.findAll().stream()
                .collect(Collectors.groupingBy(Zabieg::getCategory)).
                        forEach((s, z)->{
            List<ZabiegDTO> dto = z.stream().map(zabieg -> new ZabiegDTO(zabieg.getUrlPath(), zabieg.getName()))
                    .collect(Collectors.toList());
            mapDTO.put(s, dto);

        });
    return mapDTO;

    }

    public void save(Zabieg zabieg){
        if(zabieg.getUrlPath()==null && zabieg.getName()!=null){
            zabieg.setUrlPath(FileReader.removeStrangeChars(zabieg.getName()));
        }
            zabiegRepository.save(zabieg);
    }

    public Set<String> getAllCategories(){
        return this.getAll().stream()
                .map(z -> z.getCategory().replaceAll("_", "-"))
                .collect(Collectors.toSet());
    }

    public List<Zabieg> getAllByCategory(String category) {
        return this.getAll().stream()
                .filter(z -> z.getCategory().equalsIgnoreCase(category.replaceAll("-", "_")))
                .collect(Collectors.toList());
    }
}
