package game;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String userId) {
		super(userId);
	}


	public int takePins(Board b) {
		Scanner scan = new Scanner(System.in);
		int kvar = b.getNoPins();
		int drag;
		boolean invalid = false;
		
		do {
			System.out.println("Hur många stickor drar du? (1 eller 2)");
			drag = scan.nextInt();
			
			if (drag < 1 || drag > 2) {
				System.out.println("Du får endast dra 1 eller 2 stickor.");
				invalid = true;
			} else if(drag > kvar) {
				System.out.println("Det finns inte tillräckligt med stickor i högen.");
				invalid = true;
			} else {
				invalid = false;
			}
				
		} while(invalid);
		
		b.takePins(drag);
		
		return drag;
	}

	
}


