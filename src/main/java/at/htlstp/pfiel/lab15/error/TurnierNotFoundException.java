package at.htlstp.pfiel.lab15.error;

public class TurnierNotFoundException extends RuntimeException{
    public TurnierNotFoundException() {
        super();
    }

    public TurnierNotFoundException(String message) {
        super(message);
    }
}
