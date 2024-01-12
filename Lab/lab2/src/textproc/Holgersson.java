package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		
		// skapar en lista som innehåller en blanding
		// av objekt som implementerar TextProcessor interfacet
		List<TextProcessor> ordLista = new ArrayList<>();
		ordLista.add(new SingleWordCounter("nils"));
		ordLista.add(new SingleWordCounter("norge"));
		ordLista.add(new MultiWordCounter(REGIONS));
		
		// Scannar in alla stopord i en hasmap
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new TreeSet<>();
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		
		TextProcessor r = new GeneralWordCounter(stopwords);
		ordLista.add(r);
		
		 	
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			for (int i = 0; i < ordLista.size(); i++) {
				ordLista.get(i).process(word);
			}
			
			
		}

		
		s.close();
		
		for (int i = 0; i < ordLista.size(); i++) {
			ordLista.get(i).report();
		}
		
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
		//301.087583 ms
		
		//293.871417 ms
		
		

	}
}