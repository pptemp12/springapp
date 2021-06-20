package at.htlstp.pfiel.lab15.error;

public class SpielerNotFoundException extends RuntimeException{
    public SpielerNotFoundException() {
        super();
    }

    public SpielerNotFoundException(String message) {
        super(message);
    }
}
