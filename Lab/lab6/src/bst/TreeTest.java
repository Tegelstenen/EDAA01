package bst;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreeTest {
	private BinarySearchTree<Integer> theTree;
	private BinarySearchTree<String> theTreeString;
	Random rand;
	
	@BeforeEach
	void setUp() {
		theTree = new BinarySearchTree<Integer>();
		// Nodes register lengths of strings
		theTreeString = new BinarySearchTree<String>((s1, s2) -> s1.length() - s2.length());
		rand = new Random();
	}

	@AfterEach
	void tearDown() {
		theTree = null;
		rand = null;
	}

	@Test
	void testHeight() {
		assertEquals(0, theTree.height(), "New tree, height not 0");
		theTree.add(rand.nextInt(100));
		assertEquals(1, theTree.height(), "one node, height not 1");
		theTree.clear();
	}

	@Test
	void testSize() {
		assertEquals(0, theTree.size(), "New tree, size not 0");
		theTree.add(rand.nextInt(100));
		assertEquals(1, theTree.size(), "one node, size not 1");
		theTree.clear();
	}
	
	@Test
	void testAdd() {
		theTree.add(5);
		theTree.add(4);
		theTree.add(6);
		theTree.add(2);
		theTree.add(1);
		assertEquals(5, theTree.size(), "size not 5");
		assertEquals(4, theTree.height(), "height not 5");
		
		theTreeString.add("ABCDE");
		theTreeString.add("ABCD");
		theTreeString.add("ABCDEF");
		theTreeString.add("AB");
		theTreeString.add("A");
		assertEquals(5, theTreeString.size(), "size not 5");
		assertEquals(4, theTreeString.height(), "height not 5");
		
		theTree.add(5);
		assertEquals(5, theTree.size(), "duplicates registred twice");
		assertEquals(4, theTree.height(), "duplicates registered twice");
		
		theTreeString.add("XYZK");
		assertEquals(5, theTreeString.size(), "duplicates registred twice");
		assertEquals(4, theTreeString.height(), "duplicates registered twice");
		
		assertEquals(true, theTreeString.add("XYZKJLKSK"), "Does not return true with sucess");
		assertEquals(false, theTreeString.add("XYZK"), "Does not return fail with failure");
		
		assertEquals(true, theTree.add(12), "Does not return true with sucess");
		assertEquals(false, theTree.add(5), "Does not return fail with failure");
	}

	@Test
	void testClear() {
		for(int i = 0; i < 10; i++) {
			theTree.add(rand.nextInt(100));
		}
		theTree.clear();
		assertEquals(0, theTree.size(), "Cleared tree, size not 0");
		assertEquals(0, theTree.height(), "Cleared tree, height not 0");
	}

}

