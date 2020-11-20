package jureczko.page.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jureczko.page.data.SenderRepository;
import jureczko.page.event.SendNotificationEvent;
import jureczko.page.objects.Message;
import jureczko.page.response.PostFromMessageDTO;
import jureczko.page.security.Sender;
import jureczko.page.security.SenderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SenderService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final SenderRepository senderRepository;
    private final MessageService messageService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public SenderService(SenderRepository senderRepository,
                         MessageService messageService,
                         ApplicationEventPublisher eventPublisher) {
        this.senderRepository = senderRepository;
        this.messageService = messageService;
        this.eventPublisher = eventPublisher;
    }

    public List<Sender> getAllSenders(){
        return senderRepository.findAll();
    }

    public boolean saveMessageAndUser(PostFromMessageDTO postForm){
        if(postForm.isRights()) {
            SenderDTO senderDTO = postForm.getSenderDTO();
            Message message = postForm.getMessage();
            Sender sender = senderRepository.findByFullName(senderDTO.getFullName());
            if (sender == null) {
                sender = senderRepository.save(senderDTO.toSender());
            }
            message.setSender(sender);
            message.setAnswered(false);
            messageService.saveMesssage(message);
            eventPublisher.publishEvent(new SendNotificationEvent(senderDTO, message));
            return true;
        }
        return false;
    }
}
