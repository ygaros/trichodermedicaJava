package jureczko.page.service;

import jureczko.page.data.ProblemRepository;
import jureczko.page.exceptions.ImageNotFoundException;
import jureczko.page.objects.Problem;
import jureczko.page.objectsDTO.ProblemLight;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<Problem> getAllImages(){
        return problemRepository.findAll();
    }

    public Problem findByName(String header)throws ImageNotFoundException{
        return problemRepository.findByName(header).orElseThrow(
                ()-> new ImageNotFoundException("Nieprawodłowy nazwa zdjęcia -> '"+ header+ "'")
        );
    }

    public Problem findById(Long id) throws ImageNotFoundException{
        Optional<Problem> zdjecie = problemRepository.findById(id);
        if(zdjecie.isPresent()) {
            Problem zdjecieGet = zdjecie.get();
            if(!zdjecieGet.getName().equalsIgnoreCase("LOGO")){
                return zdjecieGet;
            }
        }
        throw new ImageNotFoundException("Nie znaleziono zdjecia o numerze -> '"+id+"'");
    }
    public Problem findByUrlPath(String urlPath) throws ImageNotFoundException{
        Optional<Problem> zdjecie = problemRepository.findByUrlPath(urlPath.replace("/api", ""));
        if(zdjecie.isPresent()) {
            Problem zdjecieGet = zdjecie.get();
            if(!zdjecieGet.getName().equalsIgnoreCase("LOGO")){
                return zdjecieGet;
            }
        }
        throw new ImageNotFoundException("Nie znaleziono zdjecia o numerze -> '"+urlPath+"'");
    }

    public List<ProblemLight> getListOfDTO(){
        return problemRepository.findAll().stream()
                .filter(i -> !i.getName().equalsIgnoreCase("logo"))
                .map(o ->new ProblemLight( o.getUrlPath(), o.getImage().getPath(), o.getName()))
                .collect(Collectors.toList());
    }


    public void save(Problem problem) throws RuntimeException {
            problemRepository.save(problem);
    }
}
