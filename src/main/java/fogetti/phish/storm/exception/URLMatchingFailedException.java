package fogetti.phish.storm.exception;

import java.io.FileNotFoundException;

public class URLMatchingFailedException extends RuntimeException {

	private static final long serialVersionUID = 6281261141639398665L;

	public URLMatchingFailedException() {
	}
	
	public URLMatchingFailedException(FileNotFoundException e) {
		super(e);
	}
	
}
