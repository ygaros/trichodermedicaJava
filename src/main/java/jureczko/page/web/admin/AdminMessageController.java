package jureczko.page.web.admin;

import jureczko.page.objects.Message;
import jureczko.page.response.MailAnswer;
import jureczko.page.service.MailService;
import jureczko.page.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/message")
public class AdminMessageController {

    private final MessageService messageService;
    private final MailService mailService;

    @Autowired
    public AdminMessageController(MessageService messageService,
                                  MailService mailService) {
        this.messageService = messageService;
        this.mailService = mailService;
    }

    @GetMapping
    public List<Message> getAll(){
        return this.messageService.getAllMessages();
    }

    @PostMapping
    public ResponseEntity<Void> sendAnswer(@RequestBody MailAnswer mailAnswer){
        try{
            this.mailService.sendAnswerMail(mailAnswer);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
