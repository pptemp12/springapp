package at.htlstp.pfiel.lab15.error;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException() {
        super();
    }

    public InvalidDateException(String message) {
        super(message);
    }
}
