package jureczko.page.service;

import jureczko.page.data.NavBarHrefRepository;
import jureczko.page.objects.NavBarHref;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NavBarService {

    private final NavBarHrefRepository navBarHrefRepository;

    @Autowired
    public NavBarService(NavBarHrefRepository navBarHrefRepository) {
        this.navBarHrefRepository = navBarHrefRepository;
    }

    public List<NavBarHref> getAll(){
        return navBarHrefRepository.findAll();
    }
}
