package jureczko.page.exceptions;

public class UsernameAlreadyExists extends RuntimeException{
    public UsernameAlreadyExists(String message) {
        super(message);
    }
}
