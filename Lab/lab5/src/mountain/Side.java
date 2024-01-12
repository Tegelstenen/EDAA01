package mountain;

public class Side {
	private Point start, stop;

	public Side(Point start, Point stop) {
		this.start = start;
		this.stop = stop;
	}

	@Override
	public int hashCode() {
		return start.hashCode() + stop.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Side)) {
			return false;
		}
		Side s = (Side) obj;
		return (start.equals(s.start) && stop.equals(s.stop)) || (start.equals(s.stop) && stop.equals(s.start));
	}
}
