package jureczko.page.exceptions;

public class AccessTokenExpired extends RuntimeException{
    public AccessTokenExpired(String message) {
        super(message);
    }
}
