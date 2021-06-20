package at.htlstp.pfiel.lab15.error;

public class SpielerHasAlreadyIdException extends RuntimeException{
    public SpielerHasAlreadyIdException() {
        super();
    }

    public SpielerHasAlreadyIdException(String message) {
        super(message);
    }
}
