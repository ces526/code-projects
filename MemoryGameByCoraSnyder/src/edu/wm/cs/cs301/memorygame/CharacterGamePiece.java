package edu.wm.cs.cs301.memorygame;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	boolean vis;
	
	public CharacterGamePiece(char s) {
		this.symbol = s;
		this.vis = false;
		
	}

	public Character getSymbol() {
		return(symbol);
	}
	
	public void setVisible(boolean v) {
		this.vis = v;
	}
	
	public boolean isVisible() {
		return(this.vis);
	}
	
	public boolean equals(GamePiece other) {
		return(this.symbol.equals(other.getSymbol()));
	}
	
}
