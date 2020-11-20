package jureczko.page.data;

import jureczko.page.security.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SenderRepository extends JpaRepository<Sender, Long> {

    Sender findByFullName(String fullName);

}
