package edu.wm.cs.cs301.memorygame;

class GreekAlphabet implements Alphabet{
	
	public int num;
	public char[] original = {'\u03B1','\u03B2','\u03B3','\u03B4','\u03B5','\u03B6','\u03B7','\u03B8','\u03B9','\u03BA','\u03BB','\u03BC','\u03BD','\u03BE','\u03BF','\u03C0','\u03C1','\u03C3','\u03C4','\u03C5','\u03C6','\u03C7','\u03C8','\u03C9', '\u03a9', '\u0397', '\u1f48', '\u1f08'};
	//did not include \u03C2 on purpose (does not exist)
	
	
	public GreekAlphabet(int n) {
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