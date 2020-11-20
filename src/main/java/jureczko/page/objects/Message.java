package jureczko.page.objects;

import jureczko.page.security.Sender;

import javax.persistence.*;

@Entity
public class Message extends AbstractEntityDate{

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10000)
    private String message;
    @ManyToOne(cascade = CascadeType.ALL)
    private Sender sender;

    private Boolean answered;

    public Message(){

    }
    public Message(String message){
        this();
        this.message = message;
    }

    public Message(String message, Sender sender) {
        this(message);
        this.sender = sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Sender getSender() {
        return sender;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean odpowiedziano) {
        this.answered = odpowiedziano;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String tresc) {
        this.message = tresc;
    }


}
