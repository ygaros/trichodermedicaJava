package jureczko.page.data;

import jureczko.page.objects.Message;
import jureczko.page.security.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findBySender(Sender sender);
}
