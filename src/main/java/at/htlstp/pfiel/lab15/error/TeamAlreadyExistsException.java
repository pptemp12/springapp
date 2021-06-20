package at.htlstp.pfiel.lab15.error;

public class TeamAlreadyExistsException extends RuntimeException {
    public TeamAlreadyExistsException() {
        super();
    }

    public TeamAlreadyExistsException(String message) {
        super(message);
    }
}
