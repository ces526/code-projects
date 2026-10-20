package edu.wm.cs.cs301.memorygame;
import java.util.Random;


public class GameBoard {
	private final GamePiece[][] board;
	private int rowCount;
	private int colCount;
	
	public GameBoard(int rows, int cols, Alphabet a) {

		this.board = new GamePiece[rows][cols];
		this.rowCount = rows;
		this.colCount = cols;
		
		char[] symbolSelect = a.toCharArray();
		symbolSelect = this.shuffleBoard(symbolSelect);
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				board[r][c] = new CharacterGamePiece(symbolSelect[r*(colCount)+ c]);
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
		
		
	
	
	public void printBoard() {
		
		
		System.out.print("\n" + "    ");
		for(int i = 1; i < colCount + 1; i ++) {
			System.out.print(i + "   ");
		}
		
		int numOfDashes = 4*colCount;
		String horizBorder = "   ";
		while(numOfDashes > 0) {
			horizBorder += "-";
			numOfDashes--;
		}
		System.out.print("\n" + horizBorder + "\n");
		
		for(int i = 1; i < rowCount + 1; i++) {
			System.out.print(i + " | ");
				for(int c = 0; c < colCount; c++) {
					if(board[i-1][c].isVisible()) {
						System.out.print(board[i-1][c].getSymbol() + " | ");
					}
					else {
						System.out.print("\u001B[34m" + "?" + "\u001B[0m" + " | " );
					}
				}
				System.out.println("\n" + horizBorder);
		}

	}
	
	public GamePiece getCard(int row, int col) {
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
}
