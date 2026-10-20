package edu.wm.cs.cs301.guimemorygame.model;


import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;


public class Statistics {
	
	private GameModel model;
	public String username;
	
	public Statistics(GameModel model) {
		
		this.model = model;
	}
	
	public void setName(String name) {
		this.username = name;
		System.out.println(this.username);
	}
	
	
	
	public String[][] readStatistics() {
		
		String[][] result = new String[3][3];
		
		try {
			
			BufferedReader r = new BufferedReader(new FileReader("leaderboard.txt"));
			
			String[][] leaders = new String[3][3];
			
			File myFile = new File("leaderboard.txt");
			
			if(myFile.length() <= 0) {
				
				leaders[0][0] = "HARD";
				leaders[1][0] = "MEDIUM";
				leaders[2][0] = "EASY";

				
				for(int z = 1; z < 3; z++) {
					for(String[] b: leaders) {
						b[z] = "0";
					}
				}
			}
			
			else {
			
			
			
			
			String stats;
			
			int i = 0;
			while((stats = r.readLine()) != null) {
				leaders[i] = stats.split(",");
				i++;
			}
			}
			
			
			
			
				
				
				
				
				result = leaders;
				
			
			

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	return(result);
	}
	

	
	public void writeStatistics(int turns, String mode) {
		
		
		
		
		String[][] leaders = readStatistics();
		
		
		for(int k = 0; k < 3; k++) {
			
			if(leaders[k][0].contains(mode.toUpperCase())) {
				
				if(!(leaders[k][2].equals("0")) && leaders[k][2] != null) {
			
		
					if(turns < (Integer.valueOf(leaders[k][2]))) {
					
						leaders[k][0] = mode.toUpperCase();
						leaders[k][1] = this.username;
						leaders[k][2] = Integer.toString(turns);

				}
				
					
				}
				else {
					leaders[k][1] = this.username;
					leaders[k][2] = Integer.toString(turns);
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

}
