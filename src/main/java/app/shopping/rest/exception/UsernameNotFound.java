package app.shopping.rest.exception;

public class UsernameNotFound extends RuntimeException {
    public UsernameNotFound() {
        super();
    }

    public UsernameNotFound(String message) {
        super(message);
    }

    public UsernameNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFound(Throwable cause) {
        super(cause);
    }

    protected UsernameNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
