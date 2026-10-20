package edu.wm.cs.cs301.wordle;

import edu.wm.cs.cs301.wordle.model.WordleModel;
import edu.wm.cs.cs301.wordle.controller.ReadWordsRunnable;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TaskOneTest {
	
	
	private static List<String> possibleWords;
	private static String curr;
	private static int initialRow, newRow;

	@Before
	public void setUp() throws Exception {
		
		
		WordleModel test = new WordleModel();
		ReadWordsRunnable testRead = new ReadWordsRunnable(test);
		testRead.run();
		
		test.initialize();
		
		initialRow = test.getCurrentRowNumber();
		
		test.testSetter();
		
		possibleWords = test.getWordList();
		
		curr = test.getCurrentGuess();
		
		test.setCurrentRow();
		
		newRow = test.getCurrentRowNumber();
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInvalidWordResponse() {
		
		
	// test should be PASSED when game responds CORRECTLY to an invalid or valid word
		
		if(newRow > initialRow) {
			assertTrue(possibleWords.contains(curr));
			System.out.println("Correct Response: Word IS Valid, Game DID Continue");
		}
		else {
			assertFalse(possibleWords.contains(curr));
			System.out.println("Correct Response: Word NOT Valid, Game DID NOT Continue");
		}
		
		
	}
	
}

	