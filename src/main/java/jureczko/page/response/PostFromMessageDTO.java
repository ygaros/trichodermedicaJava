package jureczko.page.response;

import jureczko.page.objects.Message;
import jureczko.page.security.SenderDTO;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class PostFromMessageDTO {
    private String fullName;
    @NotNull
    @Email
    private String email;
    private String phone;
    @NotNull
    private String message;
    @AssertTrue
    private boolean rights;

    public PostFromMessageDTO() {
    }

    public PostFromMessageDTO(String fullName, String email, String phone, String message, boolean rights) {
        this();
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.rights = rights;
    }

    public SenderDTO getSenderDTO(){
        return new SenderDTO(fullName, email, phone);
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isRights() {
        return rights;
    }

    public void setRights(boolean rights) {
        this.rights = rights;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Message getMessage() {
        return new Message(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
