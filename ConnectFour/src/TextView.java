/**
 * This file is to be completed by you.
 * @author S2064253
 */
public final class TextView
{

	public final void displayNewGameMessage() {
		System.out.println("---- NEW GAME STARTED ----");
		System.out.println("   PlayerA     PlayerB");
		System.out.println(" PlayerA Go First\n");
	}
	public void rule() {
		System.out.println(
				"\nPlease choose your board size in the following rules:" +
				"\n Rows - Connection >= 2" +
				"\n Columns > Connection");
	}

	public void players(Model model) {
		char player = model.getPlayer0();
		System.out.println("\nPlayer " + player + ":");
	}

	public void concede(Model model) {
		char player = model.getPlayer0();
		System.out.println("Player" + player + ", do you want to give up? (Y/N)");
	}
	public void aiConcede(Model model) {
		char player = model.getPlayer1();
		System.out.println("Player" + player + ", do you want to give up? (Y/N)");
	}

	public void winner(Model model) {
		char player = model.getPlayer1();
		System.out.println("\nWinner goes to Player" + player);
	}
	public void aiWinner(Model model) {
		char player = model.getPlayer0();
		System.out.println("\nWinner goes to Player" + player);
	}

	public void notice(Model model) {
		int nrCols = model.getNrCols();
		System.out.println("Enter a number from 1 to " + nrCols);
	}

	public void Thanks() {
		System.out.println("\nThank you for playing!");
		System.out.println("See you all next time and Wish you have a wonderful day!");
	}

	public void gameOver() {System.out.println("Game Over!");}
	public void newGame() {System.out.println("\nWould you like to start a new game? (Y/N)");}
	public void noWins() {System.out.println("NOBODY wins in this round");}
	public void winnerCheer() {System.out.print("Cheer For Her/Him!");}
	public void invalidDecisions() {System.out.println("Please Choose Between Y Or N ");}
	public void invalidVal() { System.err.println("Invalid input, please choose another one."); }
	public void wrongGridSize() {System.err.println("Invalid size for the board!");}
	public void continues() {System.out.println("Go Head!");}
	public void ai() { System.out.println("Do you want to play with AI? (Y/N)\nPlayerA is AI and PlayerB is you."); }
	public void aiWin() {System.out.println("Thank you for play with AI.");}

	public final void displayBoard(Model model) {
		int nrRows = model.getNrRows();
		int nrCols = model.getNrCols();

		// Get your board representation.
		char[][] grid = new char[nrRows][nrCols];

		// You may need to vary the length to fit your representation.
		String rowDivider = "*".repeat(nrCols + 2);
		for (int i = 0; i < nrRows; i++) {
			for (int j = 0; j < nrCols; j++) {
				grid[i][j] = '.';
		}
//			System.out.println(rowDivider);
			System.out.println(grid[i]);
	}
		// A StringBuilder is used to assemble longer Strings more efficiently.
		StringBuilder sb = new StringBuilder();
		
		// You can add to the String using its append method.
		sb.append(rowDivider);
//		sb.append("\n");
//		sb.append("|ABC|");
//		sb.append(1234567);
		
		// Then print out the assembled String.
		System.out.println(sb);
	}
}
