package jureczko.page.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class LoginRequest {
    @NotBlank(message = "Email nie może być pusty")
    @NotNull(message = "Email nie może być pusty")
    @Email(message = "Wprowadź poprawny email")
    private String username;

    @NotBlank(message = "Hasło nie może być puste")
    @NotNull(message = "Hasło nie może być puste")
    private String password;

    public LoginRequest(@NotBlank(message = "Email nie może być pusty") String username,
                        @NotBlank(message = "Hasło nie może być puste") String password) {
        this.username = username;
        this.password = password;
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
}
