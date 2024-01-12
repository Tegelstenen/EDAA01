package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class SudokuGUI {
	private JFrame frame;
	private JPanel panel;
	private JTextField[][] sudokuCells;
	private JButton solveButton;
	private JButton clearButton;
	private SudokuSolver sudokuSolver;

	public SudokuGUI(SudokuSolver solver) {
		frame = new JFrame("Sudoku");
		panel = new JPanel(new GridLayout(9, 9, 2, 2));
		sudokuCells = new JTextField[9][9];
		solveButton = new JButton("Solve");
		clearButton = new JButton("Clear");
		sudokuSolver = solver;

		initializeGUI();
	}

	private void initializeGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 550);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JTextField textField = new JTextField();
				textField.setHorizontalAlignment(JTextField.CENTER);
				textField.setFont(new Font("Arial", Font.BOLD, 35));
				if ((i / 3 + j / 3) % 2 == 0) {
					textField.setBackground(Color.ORANGE);
				} else {
					textField.setBackground(Color.WHITE);
				}
				sudokuCells[i][j] = textField;
				panel.add(textField);
			}
		}

		solveButton.addActionListener(this::solveAction);
		clearButton.addActionListener(this::clearAction);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(solveButton);
		buttonPanel.add(clearButton);

		frame.add(panel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private void solveAction(ActionEvent e) {
		if (validateInput()) {
			boolean solved = sudokuSolver.solve();
			if (solved) {
				updateGrid();
			} else {
				JOptionPane.showMessageDialog(frame,
						"The Sudoku puzzle is unsolvable. Please change the current setup or press Clear to reset.",
						"Unsolvable Sudoku", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean validateInput() {
		// Check for validity of elements in cells
		for (JTextField[] row : sudokuCells) {
			for (JTextField cell : row) {
				String text = cell.getText().trim();
				if (!text.isEmpty() && !text.matches("[1-9]")) {
					JOptionPane.showMessageDialog(frame, "Invalid input: Please enter only numbers 1-9.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}

		// Check for validity of Sudoku rules
		readInputIntoSolver();
		if (sudokuSolver.isAllValid()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(frame,
					"The set up is invalid. Please change the current setup or press Clear to reset.", "Invalid Set Up",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private void readInputIntoSolver() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String text = sudokuCells[i][j].getText().trim();
				int digit = text.isEmpty() ? 0 : Integer.parseInt(text);
				sudokuSolver.set(i, j, digit);
			}
		}
	}

	private void clearAction(ActionEvent e) {
		sudokuSolver.clearAll();
		for (JTextField[] row : sudokuCells) {
			Arrays.stream(row).forEach(cell -> cell.setText(""));
		}
	}

	private void updateGrid() {
		int[][] grid = sudokuSolver.getGrid();
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				int cell = grid[r][c];

				if (cell == 0) {
					sudokuCells[r][c].setText("");
				} else {
					sudokuCells[r][c].setText(Integer.toString(cell));
				}

			}
		}
	}

	public static void main(String[] args) {
		SudokuSolver solver = new SudokuProgram();
		SwingUtilities.invokeLater(() -> new SudokuGUI(solver));
	}
}
