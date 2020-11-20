package jureczko.page.data;

import jureczko.page.objects.Proza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProzaRepository extends JpaRepository<Proza, Long> {
    Proza findByNazwa(String name);
}
