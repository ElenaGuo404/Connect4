/**
 * This file is to be completed by you.
 * @author S2064253
 */
public final class Model {
	// The most common version of Connect Four has 7 rows and 6 columns.
//	public static final int DEFAULT_NR_ROWS = 6;
//	public static final int DEFAULT_NR_COLS = 7;

	// The size of the board.
	private int nrRows;
	private int nrCols;
	private int ConnectX;
//	private static char player1;
//	private static char[][] board;
	private  int count = 0;
	private int connect = 0;
	int n = 0;
	char[][] grid = new char [nrRows][nrCols];
	public static char[] Player = {'A', 'B'};

    public boolean validGridSize(){
		return nrRows - ConnectX >= 2 &&
				nrCols > ConnectX &&
				ConnectX > 1;
	}

	public Model() {
		// welcome and instruction;
		System.out.println("Welcome to the ConnectX! \n" +
				"\nConnect X is a two-player connection board game;\n" +
				"in which the players take turns dropping the 'A' and 'B' into a free column.\n" +
				"Player win if the number of connection is made. \n" +
				"Let's start the game!");
		// Initialise the board size to its default values;
		System.out.println("\nPlease enter a number for the Rows");
		int DEFAULT_NR_ROWS = InputUtil.readIntFromUser();
		System.out.println("Please enter a number for the Columns");
		int DEFAULT_NR_COLS = InputUtil.readIntFromUser();
		System.out.println("Please choose a number for your connection to win ");
		int connectX = InputUtil.readIntFromUser();

		this.nrRows = DEFAULT_NR_ROWS;
		this.nrCols = DEFAULT_NR_COLS;
		this.ConnectX = connectX;
		this.grid = new char[nrRows][nrCols];

		for (int i = 0; i < nrRows; i++) {
			for (int j = 0; j < nrCols; j++) {
				grid[i][j] = '.';
			}
		}
	}

	//Reset the game for next round play;
	public void resetGame() {
		this.connect = 0;
		this.Player = new char[] {'A','B'};
		this.n = 0;

		System.out.println("Please enter a number for the Rows");
		int DEFAULT_NR_ROWS = InputUtil.readIntFromUser();
		System.out.println("Please enter a number for the Columns");
		int DEFAULT_NR_COLS = InputUtil.readIntFromUser();
		System.out.println("Please choose a number for your ConnectX ");
		int connectX = InputUtil.readIntFromUser();

		this.nrRows = DEFAULT_NR_ROWS;
		this.nrCols = DEFAULT_NR_COLS;
		this.ConnectX = connectX;

		this.grid = new char[nrRows][nrCols];
		for (int i = 0; i < nrRows; i++) {
			for (int j = 0; j < nrCols; j++) {
				this.grid[i][j] = '.';
			}
		}
	}

	// Both boolean used for check the move validity;

	public boolean isColFull(int move) {
		if (grid[0][move - 1] != '.') {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isMoveValid(int move) {
		return move > 0 && move <= nrCols;
	}

	// Make player move alternatively;
	public void makeMove(int move) {

		if (isMoveValid(move)) {
			for (int i = nrRows - 1; i >= 0; i--) {
				if (grid[i][move - 1] == '.') {
					grid[i][move - 1] = Player[0];
					break;
				}
			}
		}
		char t = Player[1];
		Player[1] = Player[0];
		Player[0] = t;

		for (char[] row : grid) {
			System.out.println(row);
		}
	}

	// For AI move automatically;
	public int getRandoms() {
    	int min = 1;
    	int max = nrCols;
    	return (int) (Math.random() * max + min);
	}

	public boolean hvWins() {

		//horizontal win detection:
		//note: for Cols<6: because we need to do Cols+1 in the if statement
		for (int Rows = 0; Rows < nrRows; Rows++) {

			int connect = 0;

			for (int Cols = 0; Cols < nrCols-1; Cols++) {

				if ((grid[Rows][Cols] == 'A' && grid[Rows][Cols + 1] == 'A') ||
						(grid[Rows][Cols] == 'B' && grid[Rows][Cols + 1] == 'B')) {
					connect++;
				} else {
					connect = 0;
				}
				if (connect == ConnectX-1) {
					return true;
				}
			}
		}
		//vertical win detection:
		//note: for Rows<6: because we need to do Rows+1 in the if statement.
		for (int Cols = 0; Cols < nrCols; Cols++) {

			int connect = 0;

			for (int Rows = 0; Rows < nrRows-1; Rows++) {

				if ((grid[Rows][Cols] == 'A' && grid[Rows + 1][Cols] == 'A') ||
						(grid[Rows][Cols] == 'B' && grid[Rows + 1][Cols] == 'B')) {
					connect++;
				}
				else {
					connect = 0;
				}
				if (connect == ConnectX-1) {
					return true;
				}
			}
		}
		return false;
	}

	//Right diagonal(/) win detection:
	public boolean rDiagonalWin() {

		int connect = 0;

		for (int Cols = 0; Cols < ConnectX; Cols++) {

			for (int Rows = nrRows-1; Rows > nrRows-ConnectX; Rows--) {

				for (int Range = 0; Range < ConnectX; Range++) {

					if ((grid[Rows - Range][Cols + Range] == 'A' && grid[Rows][Cols] == 'A') ||
							(grid[Rows - Range][Cols + Range] == 'B' && grid[Rows][Cols] == 'B')){
						connect++;
					}
				}
				if (connect == ConnectX) {
					return true;
				}
				connect = 0;
			}
		}return false;
	}

	//Left diagonal(\) win detection:
	public boolean lDiagonalWin() {

		int connect = 0;

		for (int Cols = nrCols-1; Cols >= ConnectX-1; Cols--) {

		for (int Rows = nrRows - 1; Rows > nrRows - ConnectX; Rows--) {
				for (int Range = 0; Range < ConnectX; Range++) {

					if ((grid[Rows - Range][Cols - Range] == 'A' && grid[Rows][Cols] == 'A') ||
							(grid[Rows - Range][Cols - Range] == 'B' && grid[Rows][Cols] == 'B')) {
						connect++;
					}
				}
				if (connect == ConnectX) {
					return true;
				}
				connect = 0;
			}
		}return false;
	}
	
	// When nobody wins and the board is full(drawn).
	public boolean Full() {
		return n == nrCols * nrRows;
	}

	public boolean ifGameOver() {
		return hvWins() || Full() || rDiagonalWin() || lDiagonalWin();
	}

	public boolean concede() {
		return n % 5 == 0;
	}

	public boolean aiConcede() {
    	return n % 4 == 0;
	}

	// Below is used for return, in order to use it in the controller or mainly: textview;
	public int getNrRows() {
		return nrRows;
	}
	
	public int getNrCols() {
		return nrCols;
	}

	public char getPlayer0() {
		return Player[0];
	}

	public char getPlayer1() {
		return Player[1];
	}

}
