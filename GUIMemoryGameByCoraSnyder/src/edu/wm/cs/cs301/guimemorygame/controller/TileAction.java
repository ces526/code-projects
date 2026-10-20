package edu.wm.cs.cs301.guimemorygame.controller;

import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import edu.wm.cs.cs301.guimemorygame.model.GameModel;
import edu.wm.cs.cs301.guimemorygame.view.GameFrame;
import edu.wm.cs.cs301.guimemorygame.view.StatisticsDialog;


public class TileAction extends AbstractAction {

private static final long serialVersionUID = 1L;
	
	private final GameFrame view;
	
	private final GameModel model;
	
	
	private JToggleButton comparedButton;

	public TileAction(GameFrame view, GameModel model) {
		this.view = view;
		this.model = model;
		

		this.comparedButton = null;
		
	}
	
	public void actionPerformed(ActionEvent event) {
		JToggleButton button = (JToggleButton) event.getSource();
		String text = button.getActionCommand();
		
		switch(text) {
		
		case "Compare":
			
			if(!(button.isSelected())) {
				
				button.setSelected(true);
				
				JOptionPane warning = new JOptionPane();
				warning.showMessageDialog(view.getFrame(), "Please Select a New Tile");
				
				break;
			}
			

			
			if(comparedButton == null) {
				comparedButton = button;
			}
			else {
				model.addTurn();
				view.updateTurnLabel();
				if(comparedButton.getText().equals(button.getText())) {
					comparedButton = null;
					
					view.updateNumberFlipped();
					
					if(view.getNumberFlipped() == (model.getRowCount()*model.getColumnCount())){
						
						
						
						System.out.println(model.getUserName());
						model.writeStatistics();
						new StatisticsDialog(view,model);
					}
					
					break;
				}
				else {
					
					TimerTask flipBoth = new TimerTask() {
						public void run() {
							comparedButton.setSelected(false);
							button.setSelected(false);
							comparedButton = null;
						}
					};
					long delay = 2000L;
					
					Timer timer = new Timer("timer");
					timer.schedule(flipBoth, delay);
					
					break;
					
				}
			}
			
			
		}
		
	}

	
	
}
