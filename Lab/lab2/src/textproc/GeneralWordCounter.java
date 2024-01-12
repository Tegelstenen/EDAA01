package textproc;


import java.util.*;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> m;
	private Set<String> stopwords;
	
	public GeneralWordCounter(Set<String> hm) {
		stopwords = hm;
		m = new TreeMap<String, Integer>();
	}

	@Override
	public void process(String w) {
		if (!stopwords.contains(w)) {
			if (!m.containsKey(w)) {
				m.put(w, 1);
			} else {
				m.put(w, m.get(w) + 1);
			}
		}

	}

	@Override
	public void report() {
//		for (String key : m.keySet()) {
//			if (m.get(key) >= 200) {
//				System.out.println(key + ": " + m.get(key));
//			}
//		}

		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String, Integer>> WordList = new ArrayList<>(wordSet);

		WordList.sort((s1, s2) -> {
		int diff = s2.getValue() - s1.getValue();
			if (diff == 0) {
				return s2.getKey().compareTo(s1.getKey());
				
			} else {
				return diff;
			}
			
		
		});
		
		for (int i = 0; i < 5; i++) {
			System.out.println(WordList.get(i));
			
		}
	}
	
	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<>(m.entrySet());
	}

}
