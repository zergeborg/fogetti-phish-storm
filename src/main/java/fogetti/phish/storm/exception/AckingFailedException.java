package fogetti.phish.storm.exception;

public class AckingFailedException extends RuntimeException {

    private static final long serialVersionUID = -7559087108524892453L;

    public AckingFailedException() {}
    
    public AckingFailedException(String message) {
        super(message);
    }

}