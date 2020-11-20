package jureczko.page.exceptions;

public class PasswordIncorectException  extends RuntimeException{
    public PasswordIncorectException(String message) {
        super(message);
    }
}
