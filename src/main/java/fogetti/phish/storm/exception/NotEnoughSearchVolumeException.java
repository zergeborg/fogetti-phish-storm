package fogetti.phish.storm.exception;

public class NotEnoughSearchVolumeException extends RuntimeException {

    private static final long serialVersionUID = -8670860168292951813L;

    public NotEnoughSearchVolumeException() {
        super("Not enough search volume");
    }
}
