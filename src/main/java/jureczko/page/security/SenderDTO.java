package jureczko.page.security;


import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SenderDTO {
    @NotNull
    private String fullName;
    @NotNull
    @Email
    private String email;
    private String phone;

    public Sender toSender(){
        return new Sender(fullName,
                email,
                phone);
    }

    public SenderDTO() {
    }

    public SenderDTO(String fullName, @NotNull @Email String email, @Digits(integer = 9, fraction = 0) String phone) {
        this();
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
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
}
