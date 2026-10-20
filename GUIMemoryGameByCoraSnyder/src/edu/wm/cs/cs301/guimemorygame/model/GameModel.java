package edu.wm.cs.cs301.guimemorygame.model;

import java.util.Random;

import edu.wm.cs.cs301.guimemorygame.controller.Alphabet;
 

public class GameModel {
	
	
	
	private String diffMode;
	private int turns;
	
	private final Random random;
	
	private final Tile[][] board;
	
	private int rowCount;
	private int colCount;
	
	private Statistics statistics;
	public String userName;

	
	public GameModel(String d) {
		
		this.statistics = new Statistics(this);
		
		turns = 0;
		
		this.diffMode = d;
		
		this.random = new Random();
		
		if(d.equals("hard")) {
			this.rowCount = 7;
			this.colCount = 8;
		}
		else if(d.equals("medium")) {
			this.rowCount = 4;
			this.colCount = 7;
		}
		else {
			this.rowCount = 3;
			this.colCount = 4;
		}
		
		this.board = new Tile[rowCount][colCount];
	
		
		Alphabet a = new Alphabet(rowCount*colCount);
		
		char[] symbolSelect = a.toCharArray();
		symbolSelect = this.shuffleBoard(symbolSelect);
		
		for(int r = 0; r < rowCount; r++) {
			for(int c = 0; c < colCount; c++) {
				board[r][c] = new Tile(symbolSelect[r*(colCount)+ c]);
			}
		}
		

	}
	
	public char[] shuffleBoard(char[] origin) {
		
		char[] shuffled = origin;
		Random rand = new Random();
		
		for(int i = 0; i < shuffled.length; i++) {
			
			int swap = rand.nextInt(shuffled.length);
			char temp = shuffled[swap];
			shuffled[swap] = shuffled[i];
			shuffled[i] = temp;	
		}
		
		return(shuffled);
		
	}
	
	public Tile getCard(int row, int col) {
		return board[row][col];
	}
	
	public boolean allDone() {
		
		boolean allFlipped = true;
		
		for(int r = 0; r < rowCount; r++) {
			for(int c = 0; c < colCount; c++) {
				if(!(this.board[r][c].isVisible())) {
				allFlipped = false;	
				}
				}
		}
		
		return(allFlipped);
	}

	public int getColumnCount() {
		return(this.colCount);
	}
	
	public int getRowCount() {
		return(this.rowCount);
	}
	
	public Tile[][] getTileGrid(){
		return board;
	}
	
	public int getTurns() {
		return(turns);
	}
	
	public String getDiffMode() {
		return(diffMode);
	}
	
	public void addTurn() {
		this.turns+=1;
	}
	
	public void setUserName(String str) {
		this.userName = str;
		this.statistics.setName(str);
		System.out.println(this.userName);

	}
	
	public void writeStatistics() {
		this.statistics.writeStatistics(this.turns, this.diffMode);
	}

	public Statistics getStatistics() {
		return(this.statistics);
	}
	
	public String getUserName() {
		return(this.userName);
	}
	



}
