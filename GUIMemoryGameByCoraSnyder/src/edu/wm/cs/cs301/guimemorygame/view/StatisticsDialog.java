package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.guimemorygame.model.GameModel;



public class StatisticsDialog extends JDialog {

private static final long serialVersionUID = 1L;
	
	private final ExitAction exitAction;
	
	private final NextAction nextAction;
	
	private final GameFrame view;
	private final GameModel model;
	


	public StatisticsDialog(GameFrame view, GameModel model) {
		super(view.getFrame(), "Statistics", true);
		this.view = view;
		this.model = model;
		this.exitAction = new ExitAction();
		this.nextAction = new NextAction();
		
		add(createTitlePanel(), BorderLayout.NORTH);
		add(createStatsPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(view.getFrame());
		setVisible(true);
	}
	
	
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		JLabel label = new JLabel("You Matched All The Tiles!");
		label.setFont(new Font("Calibri", Font.BOLD, 30));
		panel.add(label);
		
		return panel;
	}
	
	private JPanel createStatsPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		String[][] stats = this.model.getStatistics().readStatistics();
		
		JLabel title = new JLabel("Leaderboard");
		title.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel leaderboard = new JPanel(new BorderLayout());
		
		String labelOne = "";
		String labelTwo = "";
		String labelThree = "";
		
		for(int y = 0; y < 3; y++) {
			labelOne += stats[0][y];
			labelOne += " ";
			labelTwo += stats[1][y];
			labelTwo += " ";
			labelThree += stats[2][y];
			labelThree += " ";
		}
		
		JLabel A = new JLabel(labelOne);
		JLabel B = new JLabel(labelTwo);
		JLabel C = new JLabel(labelThree);
		
		leaderboard.add(A, BorderLayout.NORTH);
		leaderboard.add(B, BorderLayout.CENTER);
		leaderboard.add(C, BorderLayout.SOUTH);
		
		panel.add(leaderboard, BorderLayout.SOUTH);
		
	
		return(panel);
	}
	
	
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exitAction");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "nextAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("nextAction", nextAction);
		actionMap.put("exitAction", exitAction);
		
		JButton nextButton = new JButton("Play Again");
		nextButton.addActionListener(nextAction);
		panel.add(nextButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(exitAction);
		panel.add(exitButton);
		
		Dimension nextDimension = nextButton.getPreferredSize();
		Dimension exitDimension = exitButton.getPreferredSize();
		int maxWidth = Math.max(nextDimension.width, exitDimension.width) + 10;
		nextButton.setPreferredSize(new Dimension(maxWidth, nextDimension.height));
		exitButton.setPreferredSize(new Dimension(maxWidth, exitDimension.height));
		
		return panel;
	}
	
	private class ExitAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			dispose();
			view.shutdown();
		}
		
	}
	
	private class NextAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			dispose();
			
			view.newGame(model.getDiffMode());
			
		}
		
	}
	
}
