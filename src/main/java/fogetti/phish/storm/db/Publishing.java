package fogetti.phish.storm.db;

import java.util.concurrent.BlockingQueue;

public class Publishing implements IPublishing {

	private static final long serialVersionUID = 3896918057489260695L;

	private final BlockingQueue<PublishMessage> publishq;
	
	public Publishing(BlockingQueue<PublishMessage> publishq) {
		this.publishq = publishq;
	}
	
	@Override
	public void publish(String channel, String msg) {
		try {
			publishq.put(new PublishMessage(channel, msg));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}