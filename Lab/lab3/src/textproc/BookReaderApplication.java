package textproc;

import java.io.*;
import java.util.*;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {

		// Scannar in alla stopord i en hashmap
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new TreeSet<>();
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}

		GeneralWordCounter counter = new GeneralWordCounter(stopwords);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			counter.process(word);

		}

		s.close();

		BookReaderController controller = new BookReaderController(counter);
	}

}
