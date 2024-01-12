package sudoku;

public class SudokuProgram implements SudokuSolver {
	private int[][] grid;

	public SudokuProgram() {
		grid = new int[9][9];
	}

	@Override
	public boolean solve() {
		return solve(0, 0);
	}

	private boolean solve(int row, int col) {
		// If the end of the row is reached, move to the next row
		if (col == 9) {
			row++;
			col = 0;
		}

		// If the end of the matrix is reached, the puzzle is solved
		if (row == 9) {
			return true;
		}

		// If the current cell is not empty, move to the next cell
		if (grid[row][col] != 0) {
			return solve(row, col + 1);
		}

		for (int i = 1; i <= 9; i++) {
			set(row, col, i);

			if (isValid(row, col)) {
				if (solve(row, col + 1)) {
					return true; // Successfully solved with the current digit, so break out of the loop
				}
			}

			set(row, col, 0); // Backtrack: Reset the cell before trying the next digit
		}

		return false; // No valid digit found for this cell
	}

	@Override
	public void set(int row, int col, int digit) {
		if (row < 0 || row > 8) {
			throw new IllegalArgumentException("row dimensions are outside [0-9]");
		} else if (col < 0 || col > 8) {
			throw new IllegalArgumentException("column dimensions are outside [0-9]");
		} else if (digit < 0 || digit > 9) {
			throw new IllegalArgumentException("digit is outside [0-9]");
		}

		grid[row][col] = digit;

	}

	@Override
	public int get(int row, int col) {
		if (row < 0 || row > 8) {
			throw new IllegalArgumentException("row dimensions are outside [1-9]");
		} else if (col < 0 || col > 8) {
			throw new IllegalArgumentException("column dimensions are outside [1-9]");
		}

		return grid[row][col];
	}

	@Override
	public void clear(int row, int col) {
		if (row < 0 || row > 8) {
			throw new IllegalArgumentException("row dimensions are outside [1-9]");
		} else if (col < 0 || col > 8) {
			throw new IllegalArgumentException("column dimensions are outside [1-9]");
		}
		grid[row][col] = 0;
	}

	@Override
	public void clearAll() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				grid[r][c] = 0;
			}
		}

	}

	@Override
	public boolean isValid(int row, int col) {

		if (row < 0 || row > 8) {
			throw new IllegalArgumentException("row dimensions are outside [1-9]");
		} else if (col < 0 || col > 8) {
			throw new IllegalArgumentException("column dimensions are outside [1-9]");
		}

		int toTest = grid[row][col];
		if (toTest == 0) {
			return true; // Empty cell is always valid.
		}

		// Check row and column
		for (int i = 0; i < 9; i++) {
			if (i != col && grid[row][i] == toTest) {
				return false; // Found the same number in the row
			}
			if (i != row && grid[i][col] == toTest) {
				return false; // Found the same number in the column
			}
		}

		// Check 3x3 box
		int boxRowStart = (row / 3) * 3;
		int boxColStart = (col / 3) * 3;
		for (int r = boxRowStart; r < boxRowStart + 3; r++) {
			for (int c = boxColStart; c < boxColStart + 3; c++) {
				if (r != row && c != col && grid[r][c] == toTest) {
					return false; // Found the same number in the 3x3 box
				}
			}
		}

		return true;

	}

	@Override
	public boolean isAllValid() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (!isValid(r, c)) {
					return false;
				}
			}
		}
		return true;
	}

	public void setGrid(int[][] m) {
		if (m.length != 9 || (m.length > 0 && m[0].length != 9)) {
			throw new IllegalArgumentException("Matrix dimensions are not 9x9.");
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (m[i][j] < 0 || m[i][j] > 9) {
					throw new IllegalArgumentException("Matrix contains values outside the range 0-9.");
				}
			}
		}

		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[r].length; c++) {
				grid[r][c] = m[r][c];
			}
		}
	}

	@Override
	public int[][] getGrid() {
		return grid;
	}

}
