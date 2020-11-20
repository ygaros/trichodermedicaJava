package jureczko.page.event;

import jureczko.page.objects.Message;
import jureczko.page.security.SenderDTO;
import org.springframework.context.ApplicationEvent;

public class SendNotificationEvent extends ApplicationEvent {

    private final SenderDTO sender;
    private final Message message;

    public SendNotificationEvent(SenderDTO sender, Message message) {
        super(sender);
        this.sender = sender;
        this.message = message;
    }

    public SenderDTO getSender() {
        return sender;
    }

    public Message getMessage() {
        return message;
    }

}
