package fogetti.phish.storm.db;

public class PublishMessage {
	public String channel;
	public String msg;
	
	public PublishMessage(String channel, String msg) {
		this.channel = channel;
		this.msg = msg;
	}
}