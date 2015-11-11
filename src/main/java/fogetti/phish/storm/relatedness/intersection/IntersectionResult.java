package fogetti.phish.storm.relatedness.intersection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class IntersectionResult {

	public final String msgId;
	public Set<String> termSet = new HashSet<>();
	
	public IntersectionResult(String msgId) {
		this.msgId = msgId;
	}
	
	public boolean add(String e) {
		return termSet.add(e);
	}
	
	public boolean add(Collection<String> e) {
		return termSet.addAll(e);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IntersectionResult)) {
			return false;
		}
		IntersectionResult other = (IntersectionResult)obj;
		boolean eq = true;
		eq &= (other.msgId == null) ? msgId == null : other.msgId.equals(msgId);
		return eq;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + msgId.hashCode();
		return result;
	}
}
