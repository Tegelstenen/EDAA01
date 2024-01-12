package game;

import java.util.Scanner;

public class TakePinsGame {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Board board = new Board();
		Player human = new HumanPlayer("Kalle");
		Player computer = new ComputerPlayer("CP30");
		int storlek;
		boolean few = false;
		boolean spelarvinst = false;
		
		
		System.out.println();
		
		do {
			System.out.println("Hur många stickor vill du spela med?");
			System.out.println("Ange ett tal större än 2: ");
			storlek = scan.nextInt();
			
			if (storlek < 3) {
				System.out.println("För få stickor!");
				few = true;
			} else {
				few = false;
			}
		} while (few);
		
		
		board.setUp(storlek);
		
		while (board.getNoPins()>0) {

			// Mänsklig tur
			human.takePins(board);
			System.out.println();
			if(board.getNoPins() == 0) {
				System.out.println(human.getUserId() + " vann!");
				spelarvinst = true;
			}
			
			// Datortur
			if (board.getNoPins()!=0) {
				int datorVal = computer.takePins(board);
				System.out.println(computer.getUserId() + " tog " + datorVal + " stickor.");
				System.out.println("Kvar finns: " + board.getNoPins() + " stickor.");
			}
			if(board.getNoPins() == 0 && !spelarvinst) {
				System.out.println(computer.getUserId() + " vann!");
			}
			
		}
		
		
		
					
				}
		
		
		
		
		
		
		
		
		

	}


