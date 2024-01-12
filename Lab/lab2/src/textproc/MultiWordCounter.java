package textproc;

import java.util.*;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> m = new TreeMap<String, Integer>();

	public MultiWordCounter(String[] ord) {	
		for (int i = 0; i < ord.length; i++) {
			m.put(ord[i], 0);
		}
	}


	@Override
	public void process(String w) {	
		if (m.containsKey(w)) {
			m.put(w, m.get(w) + 1);	
		}
	}

	@Override
	public void report() {
		for (String key : m.keySet()) {
			System.out.println(key + ": " + m.get(key));
		}

	}

}
