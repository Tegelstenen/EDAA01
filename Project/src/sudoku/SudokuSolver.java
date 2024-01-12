package sudoku;

public interface SudokuSolver {
	/**
	 * Solves the sudoku.
	 * 
	 * @return true if the sudoku is solveable
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void set(int row, int col, int digit);

	/**
	 * Returns the element at the specified row and column
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row or col or digit is outside the range [0..9]
	 * @return the element at row and col in the grid matrix.
	 */
	int get(int row, int col);

	/**
	 * Clear one cell of the grid
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row, col is outside the range [0..9]
	 */
	void clear(int row, int col);

	/**
	 * Sets every element in the grid to 0.
	 */
	void clearAll();

	/**
	 * Checks that the digit in the box row, col follows the sudoku rules.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 * @return true if the digit in the box row, col follows the sudoku rules.
	 */
	boolean isValid(int row, int col);

	/**
	 * Calls the isValid() function on each cell of the sudoku matrix.
	 */
	boolean isAllValid();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setGrid(int[][] m);

	/**
	* Gets the sudoku grid.
	*
	* @return the sudoku grid as matrix.
	*/
	int[][] getGrid();
}