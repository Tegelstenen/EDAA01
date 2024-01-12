package textproc;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class BookReaderController {

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();

		// Skapar listan, dess skrollbar, och adderar till fönstret.
		ArrayList<Map.Entry<String, Integer>> filterList = new ArrayList<>(counter.getWordList());
		filterList.removeIf(e -> Character.isDigit(e.getKey().charAt(0))); // Filtrerar bort siffror.

		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<Map.Entry<String, Integer>>(
				filterList);
		JList<Map.Entry<String, Integer>> listView = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(listView);
		scrollPane.setPreferredSize(new Dimension(200, 100));
		pane.add(scrollPane, BorderLayout.CENTER);

		// Skapa knappar.
		JPanel panel = new JPanel();
		JRadioButton alpha = new JRadioButton("Alphabetic");
		JRadioButton freq = new JRadioButton("Frequency");
		ButtonGroup group = new ButtonGroup();
		group.add(alpha);
		group.add(freq);

		// Ge knapparna funktion.
		alpha.addActionListener(event -> listModel.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey())));
		freq.addActionListener(event -> listModel.sort((e1, e2) -> e1.getValue() - e2.getValue()));

		// Lägg till sökfunktion.
		JTextField sokRuta = new JTextField();
		sokRuta.setPreferredSize(new Dimension(200, 20));
		JButton sokKnapp = new JButton("Find");

		sokKnapp.addActionListener(e -> {
			String sokOrd = sokRuta.getText();

			// Renskrivning av text.
			String renSkriven = sokOrd.trim().toLowerCase();

			for (int i = 0; i < filterList.size(); i++) {
				if (filterList.get(i).getKey().equals(renSkriven)) {
					listView.setSelectedIndex(i);
					listView.ensureIndexIsVisible(i);
					break;
				} else if (i == filterList.size() - 1) { //Felmeddelande om ordet saknas.
					JOptionPane.showMessageDialog(frame, "Ordet finns ej i listan.");
				}
			}
			;
		});

		// Lägg till alla komponenter.

		panel.add(alpha);
		panel.add(freq);
		panel.add(sokRuta);
		panel.add(sokKnapp);

		pane.add(panel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}
	
}
