package jureczko.page.response;

public class MailAnswer {
    private String subject;
    private String email;
    private String message;
    private long messageId;

    public MailAnswer(String subject, String email, String message, long messageId) {
        this.subject = subject;
        this.email = email;
        this.message = message;
        this.messageId = messageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
