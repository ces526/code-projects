package edu.wm.cs.cs301.memorygame;

class LatinAlphabet implements Alphabet{
	
	public int num;
	public char[] original = {'\u0061', '\u0062', '\u0063', '\u0064','\u0065','\u0066','\u0067','\u0068','\u0069','\u006A','\u006B','\u006C','\u006D','\u006E','\u006F', '\u0070','\u0071','\u0072','\u0073','\u0074','\u0075','\u0076','\u0077','\u0078','\u0079','\u007A', '\u0041', '\u0042'};
	
	
	public LatinAlphabet(int n) {
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