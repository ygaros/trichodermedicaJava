package jureczko.page.service.admin;

import jureczko.page.data.ZabiegRepository;
import jureczko.page.objects.Zabieg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminZabiegService {

    private final ZabiegRepository zabiegRepository;

    public AdminZabiegService(ZabiegRepository zabiegRepository) {
        this.zabiegRepository = zabiegRepository;
    }

    public List<Zabieg> getAll(){
        return zabiegRepository.findAll();
    }

}
