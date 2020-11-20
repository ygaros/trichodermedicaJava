package jureczko.page.data;

import jureczko.page.objects.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * cennik repo
 */
public interface UslugaRepository extends JpaRepository<Usluga, Long> {
}
