package jureczko.page.service;

import jureczko.page.data.MessageRepository;
import jureczko.page.objects.Message;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
    public void saveMesssage(Message message){
        messageRepository.save(message);
    }
}
