package game;

public class Board {
	private int noPins = 0;
	
	public void setUp(int n) {
		noPins = n;
	}
	
	public int getNoPins() {
		return noPins;
	}
	
	public void takePins(int n) {
		noPins -= n;
	}
}
