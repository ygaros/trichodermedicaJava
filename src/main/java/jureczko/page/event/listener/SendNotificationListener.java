package jureczko.page.event.listener;

import jureczko.page.event.SendNotificationEvent;
import jureczko.page.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

@Component
public class SendNotificationListener implements ApplicationListener<SendNotificationEvent> {

    private final MailService mailService;

    @Autowired
    public SendNotificationListener(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void onApplicationEvent(SendNotificationEvent sendNotificationEvent) {
        try{
            mailService.sendConfirmationMail(sendNotificationEvent);
        }catch (MailException e){
            e.printStackTrace();
        }

    }


}
