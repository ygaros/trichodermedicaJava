package jureczko.page.data;

import jureczko.page.objects.Zabieg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZabiegRepository  extends JpaRepository<Zabieg, Long> {
    Optional<Zabieg> findByUrlPath(String urlPath);
}
