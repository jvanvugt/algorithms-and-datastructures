import java.util.LinkedList;
import java.util.List;

public class Intersection {
	private boolean bin = false;
	private final List<Intersection> adjacent = new LinkedList<>();

	public void addAdjacent(Intersection i) {
		adjacent.add(i);
	}

	public boolean hasBin() {
		return bin;
	}

	public void setBin(boolean b) {
		bin = b;
	}

	public List<Intersection> getAdjacent() {
		return adjacent;
	}

	public boolean canHaveBin() {
		for (Intersection a : adjacent) {
			if (a.hasBin()) {
				return false;
			}
		}
		return true;
	}

	public int getNumAdjacent() {
		return adjacent.size();
	}
}
