package edu.wm.cs.cs301.guimemorygame.controller;

public class Alphabet {

	public int num;
	public char[] original = {'\u2654', '\u2655', '\u2656', '\u2657', '\u2658', '\u2659', '\u265A', '\u265B', '\u265C', '\u265D', '\u265E', '\u265F', '\u2665', '\u2660', '\u2663', '\u2666', '\u2667', '\u2664', '\u2662', '\u2600', '\u2605', '\u266B', '\u263C', '\u263D', '\u2600', '\u2669', '\u2680', '\u2685' };
	
	
	public Alphabet(int n) {
		this.num = n/2;
	}
	
	
	
	 public char[] toCharArray() {
		 char[] symbolSet = new char[num*2];
		 
			 for (int i = 0; i < num; i++) {
				 symbolSet[i] = original[i];
			 }
			 for (int c = 0; c < num; c++) {
				 symbolSet[c+num] = original[c];
			 }
		 
		 return symbolSet;
	 }


	
}
