package jureczko.page.data;

import jureczko.page.objects.Slideshow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlideshowRepository extends JpaRepository<Slideshow, Long> {
    Optional<Slideshow> findByName(String name);

}
