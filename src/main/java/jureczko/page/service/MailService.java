package jureczko.page.service;

import jureczko.page.data.MessageRepository;
import jureczko.page.event.SendNotificationEvent;
import jureczko.page.exceptions.MessageNotFoundException;
import jureczko.page.objects.Message;
import jureczko.page.response.MailAnswer;
import jureczko.page.security.SenderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailService {
    private final JavaMailSender mailSender;
    private final MessageRepository messageRepository;
    @Value("${spring.mail.username}")
    private String myEmail;

    @Autowired
    public MailService(JavaMailSender mailSender,
                       MessageRepository messageRepository) {
        this.mailSender = mailSender;
        this.messageRepository = messageRepository;
    }
    public void sendAnswerMail(MailAnswer mailAnswer)throws MessageNotFoundException {
        Optional<Message> message = this.messageRepository.findById(mailAnswer.getMessageId());
        if(!message.isPresent()) throw new MessageNotFoundException(
                "Wiadomość o id -> '"+mailAnswer.getMessageId()+"' nie istnieje");
        this.mailSender.send(constructAnswer(mailAnswer, message.get()));
    }
    private SimpleMailMessage constructAnswer(MailAnswer mailAnswer, Message message){
        SimpleMailMessage smp = new SimpleMailMessage();
        smp.setFrom(myEmail);
        smp.setSubject(mailAnswer.getSubject());
        smp.setText("Odpowiedź na wiadomość: \n"+
                message.getMessage()+"\n"+
                mailAnswer.getMessage());
        smp.setTo(mailAnswer.getEmail());
        return smp;
    }





    public void sendConfirmationMail(SendNotificationEvent event)throws MailException {
        SenderDTO sender = event.getSender();
        Message message = event.getMessage();

        SimpleMailMessage email = constructEmailMessage(sender, message);
        mailSender.send(email);
    }
    private SimpleMailMessage constructEmailMessage(SenderDTO sender,
                                                    Message message) {
        String recipientAddress = sender.getEmail();
        String subject = "Confirmation sending message";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("Thanks for you message " + message.getMessage());
//        https://stackoverflow.com/questions/13946581/spring-java-mail-the-from-address-is-being-ignored
        email.setFrom(myEmail);
        return email;
    }
}
