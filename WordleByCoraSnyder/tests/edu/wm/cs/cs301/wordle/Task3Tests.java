package edu.wm.cs.cs301.wordle;
import edu.wm.cs.cs301.wordle.model.*;
import edu.wm.cs.cs301.wordle.controller.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Task3Tests {
	
	private WordleModel model;
	private int maxRowNum, maxColNum;
	private String diff;
	

	@Before
	public void setUp() throws Exception {
		
		model = new WordleModel();
		
		model.DiffTestSetter();
		
		maxRowNum = model.getMaximumRows();
		maxColNum = model.getColumnCount();
		diff = model.getDiffMode();

		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		if(diff.toLowerCase().equals("easy")){
			assertTrue(maxRowNum == 5 && maxColNum == 4);
			System.out.println("Easy Mode has 5 guesses for a 4 Letter Word");
		}
		if(diff.toLowerCase().equals("medium")) {
			assertTrue(maxRowNum == 6 && maxColNum == 5);
			System.out.println("Medium Mode has 6 guesses for a 5 Letter Word");
		}
		if(diff.toLowerCase().equals("hard")) {
			assertTrue(maxRowNum == 7 && maxColNum == 6);
			System.out.println("Hard Mode has 7 guesses for a 6 letter word");
		}
	}

}
