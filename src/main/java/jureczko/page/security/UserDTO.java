package jureczko.page.security;

import jureczko.page.exceptions.PasswordIncorectException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    @Email(message = "Wprowadź poprawny email")
    private String username;

    @NotBlank(message = "Hasło nie może być puste")
    @NotNull(message = "Hasło nie może być puste")
    private String password;

    @NotBlank(message = "Potwierdzenie hasło nie może być puste")
    @NotNull(message = "Potwierdzenie hasło nie może być puste")
    private String repassword;

    public UserDTO() {
    }

    public UserDTO(@Size(min = 1, max = 50) String username, @NotNull String password, @NotNull String repassword) {
        this();
        this.username = username;
        this.password = password;
        this.repassword = repassword;
    }


    public User toUser(PasswordEncoder passwordEncoder) throws PasswordIncorectException {
        if(!this.password.equals(this.repassword)){
            throw new PasswordIncorectException("Hasła nie są takie same!");
        }
        return new User(this.username, passwordEncoder.encode(this.password));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
