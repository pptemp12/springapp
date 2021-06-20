package at.htlstp.pfiel.lab15.error;

public class TurnierHasAlreadyIdException extends RuntimeException{
    public TurnierHasAlreadyIdException() {
        super();
    }

    public TurnierHasAlreadyIdException(String message) {
        super(message);
    }
}
