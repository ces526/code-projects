package edu.wm.cs.cs301.wordle.model;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.wm.cs.cs301.wordle.controller.ReadWordsRunnable;

public class WordleModel {
	
	private char[] currentWord, guess;
	
	private final int columnCount, maximumRows;
	private int currentColumn, currentRow;
	
	private List<String> wordList = new ArrayList<String>();
	
	private final Random random;
	
	private final Statistics statistics;
	
	private WordleResponse[][] wordleGrid;
	
	private String diffMode;

	
	
	public WordleModel() {
		this("medium");
	}
	
	public WordleModel(String d) {
		this.currentColumn = -1;
		this.currentRow = 0;
		
		if(d.equals("easy")) {
			this.columnCount = 4;
			this.maximumRows = 5;
		}
		
		else if(d.equals("hard")) {
			this.columnCount = 6;
			this.maximumRows = 7;
		}
		else {
			this.columnCount = 5;
			this.maximumRows = 6;
		}
		
		this.random = new Random();
		this.diffMode = d;
		
		createWordList();
		
		this.wordleGrid = initializeWordleGrid();
		this.guess = new char[columnCount];
		this.statistics = new Statistics(diffMode);
		

		
	}
	
	private void createWordList() {
		ReadWordsRunnable runnable = new ReadWordsRunnable(this);
		new Thread(runnable).start();
	}
	
	
	public void initialize() {
		this.wordleGrid = initializeWordleGrid();
		this.currentColumn = -1;
		this.currentRow = 0;
		generateCurrentWord();
		String gameWord = new String(currentWord);
		System.out.println(gameWord);
		this.statistics.setGameWord(gameWord);
		this.guess = new char[columnCount];
	}

	public void generateCurrentWord() {
		String word = getCurrentWord();
		this.currentWord = word.toUpperCase().toCharArray();
		
		this.statistics.setGameWord(word.toUpperCase());
		
		//uncomment line below to show secret word on the console for debugging
		//System.out.println("Current word set to " + word);
	}

	private String getCurrentWord() {
		return wordList.get(getRandomIndex());
	}

	private int getRandomIndex() {
		int size = wordList.size();
		return random.nextInt(size);
	}
	
	private WordleResponse[][] initializeWordleGrid() {
		WordleResponse[][] wordleGrid = new WordleResponse[maximumRows][columnCount];

		for (int row = 0; row < wordleGrid.length; row++) {
			for (int column = 0; column < wordleGrid[row].length; column++) {
				wordleGrid[row][column] = null;
			}
		}

		return wordleGrid;
	}
	
	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}
	
	public void setCurrentWord() {
		int index = getRandomIndex();
		currentWord = wordList.get(index).toCharArray();
	}
	
	public void setCurrentColumn(char c) {
		currentColumn++;
		currentColumn = Math.min(currentColumn, (columnCount - 1));
		guess[currentColumn] = c;
		wordleGrid[currentRow][currentColumn] = new WordleResponse(c,
				Color.WHITE, Color.BLACK);
	}
	
	public void backspace() {
		if (this.currentColumn > -1) { //only backspace if there's room
			wordleGrid[currentRow][currentColumn] = null;
			guess[currentColumn] = ' ';
			this.currentColumn--;
			this.currentColumn = Math.max(currentColumn, -1);
		}
	}
	
	public WordleResponse[] getCurrentRow() {
		return wordleGrid[getCurrentRowNumber()];
	}
	
	public int getCurrentRowNumber() {
		return currentRow - 1;
	}
	
	public boolean setCurrentRow() {	
		
		if(wordList.contains(this.getCurrentGuess().toLowerCase())) {
		
		for (int column = 0; column < guess.length; column++) {
			Color backgroundColor = AppColors.GRAY;
			Color foregroundColor = Color.WHITE;
			if (guess[column] == currentWord[column]) {
				backgroundColor = AppColors.GREEN;
			} else if (contains(currentWord, guess, column)) {
				backgroundColor = AppColors.YELLOW;
			}
			
			wordleGrid[currentRow][column] = new WordleResponse(guess[column],
					backgroundColor, foregroundColor);
		}
		
		currentColumn = -1;
		currentRow++;
		guess = new char[columnCount];
		}
		
		return currentRow < maximumRows;
	}
	
	private boolean contains(char[] currentWord, char[] guess, int column) {
		for (int index = 0; index < currentWord.length; index++) {
			if (index != column && guess[column] == currentWord[index]) {
				return true;
			}
		}
		
		return false;
	}

	public WordleResponse[][] getWordleGrid() {
		return wordleGrid;
	}
	
	public int getMaximumRows() {
		return maximumRows;
	}

	public int getColumnCount() {
		return columnCount;
	}
	
	public int getCurrentColumn() {
		return currentColumn;
	}

	public int getTotalWordCount() {
		return wordList.size();
	}

	public Statistics getStatistics() {
		return statistics;
	}
	
	public List<String> getWordList(){
		return wordList;
	}
	
	public String getCurrentGuess() {
		String guessWord = new String(guess);
		return(guessWord);
	}
	
	public void testSetter() {
		String[] guessList = {"abcde","aback"};
		Random r = new Random();
		int s = r.nextInt(2);
		this.guess = guessList[s].toCharArray();
	}
	
	public void setDiffMode(String mode) {
		this.diffMode = mode;
	}
	
	public String getDiffMode() {
		return(this.diffMode);
	}
	
	public void DiffTestSetter() {
		String[] modes = {"easy", "medium", "hard"};
		Random r = new Random();
		int s = r.nextInt(3);
		setDiffMode(modes[s]);
	}
	

	
	
	
	

	
	
	

	

}
