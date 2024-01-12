package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SudokuTester {
	
	@Test
    public void testSetAndGet() {
        SudokuProgram sudoku = new SudokuProgram();
        sudoku.set(0, 0, 5);
        assertEquals(5, sudoku.get(0, 0), "The value at (0,0) should be 5.");
    }

    @Test
    public void testInvalidSet() {
        SudokuProgram sudoku = new SudokuProgram();
        assertThrows(IllegalArgumentException.class, () -> {
            sudoku.set(10, 0, 5);
        }, "Setting value outside the grid should throw IllegalArgumentException");
    }
    
    @Test
    public void testValidity() {
        SudokuProgram sudoku = new SudokuProgram();
        sudoku.setGrid(new int[][] {
        	{0, 0, 8, 0, 0, 9, 0, 6, 2}, 
        	{0, 0, 0, 0, 0, 0, 0, 0, 5}, 
        	{1, 0, 2, 5, 0, 0, 0, 0, 0},
        	{0, 0, 0, 2, 1, 0, 0, 9, 0}, 
        	{0, 5, 0, 0, 0, 0, 6, 0, 0}, 
        	{6, 0, 0, 0, 0, 0, 0, 2, 8},
        	{4, 1, 0, 6, 0, 8, 0, 0, 0}, 
        	{8, 6, 0, 0, 3, 0, 1, 0, 0}, 
        	{0, 0, 0, 0, 0, 0, 4, 0, 0}
        });
        assertTrue(sudoku.isAllValid(), "All cells should be valid in a correct grid.");
        sudoku.set(0, 0, 9);
        assertFalse(sudoku.isValid(0, 0), "The cell should be invalid.");
        assertFalse(sudoku.isAllValid(), "The grid should be invalid.");
        
    }

    @Test
    public void testSolveFigure() {
        SudokuProgram sudoku = new SudokuProgram();
        sudoku.setGrid(new int[][] {
        	{0, 0, 8, 0, 0, 9, 0, 6, 2}, 
        	{0, 0, 0, 0, 0, 0, 0, 0, 5}, 
        	{1, 0, 2, 5, 0, 0, 0, 0, 0},
        	{0, 0, 0, 2, 1, 0, 0, 9, 0}, 
        	{0, 5, 0, 0, 0, 0, 6, 0, 0}, 
        	{6, 0, 0, 0, 0, 0, 0, 2, 8},
        	{4, 1, 0, 6, 0, 8, 0, 0, 0}, 
        	{8, 6, 0, 0, 3, 0, 1, 0, 0}, 
        	{0, 0, 0, 0, 0, 0, 4, 0, 0}
        });
        assertTrue(sudoku.solve(), "The sudoku should be solvable.");
    }
    
    @Test
    public void testSolveEmpty() {
        SudokuProgram sudoku = new SudokuProgram();
        sudoku.setGrid(new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
        });
        assertTrue(sudoku.solve(), "The sudoku should be solvable.");
    }
    
    @Test
    public void testUnsolvable() {
        SudokuProgram sudoku = new SudokuProgram();
        sudoku.setGrid(new int[][]  {
            {1, 2, 3, 0, 0, 0, 0, 0, 0}, 
            {4, 5, 6, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 7, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
        });
        assertFalse(sudoku.solve(), "The sudoku should be unsolvable.");
    }

    @Test
    public void testClearAndClearAll() {
        SudokuProgram sudoku = new SudokuProgram();
        sudoku.set(0, 0, 5);
        sudoku.clear(0, 0);
        assertEquals(0, sudoku.get(0, 0), "The value at (0,0) should be cleared to 0.");

        sudoku.set(0, 0, 5);
        
        sudoku.clearAll();
        assertEquals(0, sudoku.get(0, 0), "All values should be cleared to 0.");
    }
    
    @Test
    public void testSetGridAndGetGrid() {
        SudokuProgram sudoku = new SudokuProgram();
        int[][] testGrid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
            };
        sudoku.setGrid(testGrid);
        assertArrayEquals(testGrid, sudoku.getGrid(), "The grid should match the set grid.");
    }
    
}

