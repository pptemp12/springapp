package at.htlstp.pfiel.lab15.error;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException() {
        super();
    }

    public TeamNotFoundException(String message) {
        super(message);
    }
}
