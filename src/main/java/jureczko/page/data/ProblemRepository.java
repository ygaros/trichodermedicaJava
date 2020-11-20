package jureczko.page.data;

import jureczko.page.objects.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    Optional<Problem> findByName(String name);
    Optional<Problem> findByUrlPath(String urlPath);
}
