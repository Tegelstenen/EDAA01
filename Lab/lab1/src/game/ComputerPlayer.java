package game;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String userId) {
		super(userId);
	}

	public int takePins(Board b) {
		int n = (int) (Math.random() * 2) + 1;
		
		if (b.getNoPins() > 1) {	
			b.takePins(n);
		} else {
			n = 1;
			b.takePins(n);
		}
		
		return n;
	}

}
