package edu.wm.cs.cs301.memorygame;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;


public class MemoryGame {
	private boolean running = true;
	private GameBoard board;
	private int turns;
	private boolean gameOver;
	
	public MemoryGame() {
		
		try {
			
			BufferedReader r = new BufferedReader(new FileReader("leaderboard.txt"));
			
			String[][] leaders = new String[3][3];
			String stats;
			int i = 0;
			while((stats = r.readLine()) != null) {
				leaders[i] = stats.split(",");
				i++;
			}
			File myFile = new File("leaderboard.txt");
			
			if(myFile.length() <= 0) {
				leaders[0][0] = "HARD";
				leaders[1][0] = "MEDIUM";
				leaders[2][0] = "EASY";
				
				for(int z = 1; z < 3; z++) {
					for(String[] b: leaders) {
						b[z] = "null";
					}
				}
			}
			
			else if(i < 2) {
				boolean hardScore = false;
				boolean easyScore = false;
				boolean medScore = false;
				
				for(int index = 0; index <= i; index++) {
					if((leaders[i][0] != null) && leaders[i][0].contains("EASY")) {
						easyScore = true;
					}
					if((leaders[i][0] != null) && leaders[i][0].contains("MEDIUM")) {
						medScore = true;
					}
					if((leaders[i][0] != null) && leaders[i][0].contains("HARD")) {
						hardScore = true;
					}
				}
				
				for(int q = 0; q <= i; q ++) {
					
					if(!(hardScore)) {
						if(leaders[q][0] == null) {
							leaders[q][0] = "HARD";
						}
						else if(!(leaders[q][0].contains("HARD"))) {
							leaders[q][0] = "HARD";
						}
					}
					if(!(easyScore)) {
						if(leaders[q][0] == null) {
							leaders[q][0] = "EASY";
						}
						else if(!(leaders[q][0].contains("EASY"))) {
							leaders[q][0] = "EASY";
						}
					}
					if(!(medScore)) {
						if(leaders[q][0] == null) {
							leaders[q][0] = "MEDIUM";
						}
						else if(!(leaders[q][0].contains("MEDIUM"))) {
							leaders[q][0] = "MEDIUM";
						}
					}
				}
				
			}
			
			
			try {
				FileWriter w = new FileWriter("leaderboard.txt", false);
	
				
				for(int x = 0; x < 3; x++) {
					w.write(leaders[x][0]);
					if(leaders[x].length > 1) {
						w.write("," + leaders[x][1] + "," + leaders[x][2] + "\n");
					}
					else {
						w.write("\n");
					}
					}
				w.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}

		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		while(running) {
	
		turns = 0;
		gameOver = false;
		int rowNum = 0;
		int colNum = 0;
		
		Scanner reader = new Scanner(System.in);
		System.out.println("\n" + "Welcome!\n"
				+ " \nRules of the Game:\n"
				+ "> to select a card, enter the row, then column, seperated by a space\n" + "> to play a turn, select two cards to flip\n" + "> matching cards remain flipped, non-matching cards are turned back over\n" + "> the game is over when all cards are matched\n" + "> lowest number of turns gets a  spot on the leaderboard");
		
		printLeaderBoard();
		
		System.out.println("\nReady to Play? Hit ENTER to Start");
		
		
		if(reader.nextLine().equals("")) {
			
		System.out.println("\nEnter your username:");
		String name = reader.nextLine();
		
		System.out.println("\nSelect a mode: Easy  Medium  Hard");
		String mode = reader.nextLine();
		
		if(mode.toLowerCase().contains("hard")) {
			rowNum = 7;
			colNum = 8;
		}
		else if(mode.toLowerCase().contains("medium")) {
			rowNum = 4;
			colNum = 7;
		}
		else if(mode.toLowerCase().contains("easy")) {
			rowNum = 3;
			colNum = 4;
		}
		else {
			System.out.println("uh-oh! we didn't get that-try again!");
			String modeTwo = reader.nextLine();
			
			if(modeTwo.toLowerCase().contains("hard")) {
				rowNum = 7;
				colNum = 8;
			}
			else if(modeTwo.toLowerCase().contains("medium")) {
				rowNum = 4;
				colNum = 7;
			}
			else if(modeTwo.toLowerCase().contains("easy")) {
				rowNum = 3;
				colNum = 4;
			}
			mode = modeTwo;
		}
		
		
		System.out.println("\nSelect an Alphabet: Greek  Latin");
		String lang = reader.nextLine();
		
		if(lang.toLowerCase().contains("greek")) {
			board = new GameBoard(rowNum, colNum,new GreekAlphabet(rowNum*colNum));
		}
		else if (lang.toLowerCase().contains("latin")) {
			board = new GameBoard(rowNum, colNum,new LatinAlphabet(rowNum*colNum));
		}
		else {
			System.out.println("uh-oh, we didn't get that! try again!");
			String langTwo = reader.nextLine();
			
			if(langTwo.toLowerCase().contains("greek")) {
				board = new GameBoard(rowNum, colNum,new GreekAlphabet(rowNum*colNum));
			}
			else if (langTwo.toLowerCase().contains("latin")) {
				board = new GameBoard(rowNum, colNum,new LatinAlphabet(rowNum*colNum));
			}
			
		}
		
		

		
		while(!gameOver) {
			
			this.turns ++;
			
			System.out.println("\n\n" + "Number of Turns: " + this.turns + "\n");
			
			
			
			board.printBoard();
			
			
			
			System.out.println("\nSelect a tile OR quit game");
			String choice = reader.nextLine();

			
			if(choice.toLowerCase().contains("quit")) {
				this.gameOver = true;
				continue;
			}
			else {
				
				String[] numsOne = choice.split(" ");
				int rowOne = Integer.valueOf(numsOne[0]) - 1;
				int colOne = Integer.valueOf(numsOne[1]) - 1;
				
				if(rowOne < 0 || rowOne > rowNum || colOne < 0 || colOne > colNum) {
					System.out.println("sorry, we didn't get that, try again!");
					String secondChoice = reader.nextLine();
					String[] secondNums = secondChoice.split(" ");
					int secondRow = Integer.valueOf(secondNums[0]) - 1;
					int secondCol = Integer.valueOf(secondNums[1]) - 1;
					
					rowOne = secondRow;
					colOne = secondCol;
				}
				else if(this.board.getCard(rowOne, colOne).isVisible()) {
					System.out.println("uh-oh, that card is already flipped, try a different one!");
					String secondChoice = reader.nextLine();
					String[] secondNums = secondChoice.split(" ");
					int secondRow = Integer.valueOf(secondNums[0]) - 1;
					int secondCol = Integer.valueOf(secondNums[1]) - 1;
					
					rowOne = secondRow;
					colOne = secondCol;
				}
				
				
				this.board.getCard(rowOne, colOne).setVisible(true);
				this.board.printBoard();
				
				System.out.println("Select a second tile!");
				String choiceTwo = reader.nextLine();
				String[] numsTwo = choiceTwo.split(" ");
				int rowTwo = Integer.valueOf(numsTwo[0]) - 1;
				int colTwo = Integer.valueOf(numsTwo[1]) - 1;
				
				if(rowTwo < 0 || rowTwo > rowNum || colTwo < 0 || colTwo > colNum) {
					System.out.println("sorry, we didn't get that, try again!");
					String secondChoice = reader.nextLine();
					String[] secondNums = secondChoice.split(" ");
					int secondRow = Integer.valueOf(secondNums[0]) - 1;
					int secondCol = Integer.valueOf(secondNums[1]) - 1;
					
					rowTwo = secondRow;
					colTwo = secondCol;
				}
				
				else if(this.board.getCard(rowTwo, colTwo).isVisible()) {
					System.out.println("uh-oh, that card is already flipped, try a different one!");
					String secondChoice = reader.nextLine();
					String[] secondNums = secondChoice.split(" ");
					int secondRow = Integer.valueOf(secondNums[0]) - 1;
					int secondCol = Integer.valueOf(secondNums[1]) - 1;
					
					rowTwo = secondRow;
					colTwo = secondCol;
				}
				
				
				this.board.getCard(rowTwo, colTwo).setVisible(true);
				
				this.board.printBoard();
				
				if(this.board.getCard(rowTwo, colTwo).equals(this.board.getCard(rowOne, colOne))) {
					System.out.println("Its a Match!");
					if(this.board.allDone()) {
						break;
					}
					continue;
					
				}
				else {
					System.out.println("Try Again!");
					this.board.getCard(rowTwo, colTwo).setVisible(false);
					this.board.getCard(rowOne, colOne).setVisible(false);
					continue;
				}
			
		}
}
	if(this.board.allDone()) {
	
		System.out.println("\nGame Over!\nScore: " + turns);
		
		try {
		
		BufferedReader r = new BufferedReader(new FileReader("leaderboard.txt"));
		
		String[][] leaders = new String[3][3];
		String stats;
		int i = 0;
		while((stats = r.readLine()) != null) {
			leaders[i] = stats.split(",");
			i++;
		}
		
		
		

		for(int k = 0; k < 3; k++) {
			
			if(leaders[k][0].contains(mode.toUpperCase())) {
				
				if(!(leaders[k][2].equals("null")) && leaders[k][2] != null) {
			
		
					if(turns < (Integer.valueOf(leaders[k][2]))) {
					
						leaders[k][0] = mode.toUpperCase();
						leaders[k][1] = name;
						leaders[k][2] = Integer.toString(this.turns);
	
				}
				
					
				}
				else {
					leaders[k][1] = name;
					leaders[k][2] = Integer.toString(this.turns);
				}
				
				try {
					FileWriter w = new FileWriter("leaderboard.txt", false);
		
					
						for(int x = 0; x < 3; x++) {
						w.write(leaders[x][0] + "," + leaders[x][1] + "," + leaders[x][2] + "\n");
		
						}
					w.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
		}
	
				
			}
			
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
		
	
	else {
		System.out.println("Game Terminated");
	}
	
	printLeaderBoard();
	
	System.out.println(" \nPress ENTER to Play Again");
	if(!(reader.nextLine().equals(""))) {
		continue;
	}

		}
		}
		}
		
			
	
	
	public void printLeaderBoard() {
		System.out.println("\n-------------------------------------");
		System.out.println("LEADERBOARD:\n");
		try {
			BufferedReader b = new BufferedReader(new FileReader("leaderboard.txt"));
			String next;
			while((next = b.readLine()) != null) {
				String[] format = next.split(",");
				for(String s: format) {
					if(!(s==null) && !(s.equals("null"))) {
					System.out.print(s+ " ");
				}
					else {
						System.out.print("");
					}
			}
				System.out.println("\n");
		}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------");
	}
	
	
	
	
	
}
	
	


