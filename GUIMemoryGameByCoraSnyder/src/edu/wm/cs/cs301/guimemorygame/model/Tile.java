package edu.wm.cs.cs301.guimemorygame.model;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.JToggleButton;

public class Tile {
	
	private final String symbol;
	private boolean flipped;
	
	public Tile(char s) {
		String str = String.valueOf(s);
		this.symbol = str;
		this.flipped = false;
	}
	
	public String getSymbol() {
		return(this.symbol);
	}
	
	public void setVisible(boolean v) {
		this.flipped = v;
	}
	
	public boolean isVisible() {
		return(this.flipped);
	}
	
	public boolean equals(Tile other) {
		return(this.symbol.equals(other.getSymbol()));
	}

	
	
}
