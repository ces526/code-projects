package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.guimemorygame.controller.TileAction;
import edu.wm.cs.cs301.guimemorygame.model.GameModel;
import edu.wm.cs.cs301.guimemorygame.model.Tile;



public class GameFrame {

	private final JFrame frame;
	
	private final TileAction action;
	
	private int numberFlipped;
	

	private JLabel turnAndDiffLabel;
	
	private final String DiffLabelValue;
	
	private final GameModel model;
	
	
	public GameFrame(GameModel model) {
		this.model = model;
		

		
		this.DiffLabelValue = "Mode: " + this.model.getDiffMode().toUpperCase()
 + "\t \t \t Turns: ";		
		this.turnAndDiffLabel = new JLabel(DiffLabelValue + model.getTurns());
		turnAndDiffLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		this.numberFlipped = 0;
		
		this.action = new TileAction(this,model);
		this.frame = createAndShowGUI();

	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Memory Game");
		frame.setSize(new Dimension(646,505));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setJMenuBar(createMenuBar());
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				shutdown();
			}
		});
		
		frame.add(createTitlePanel(), BorderLayout.NORTH);
		frame.add(createGamePanel(), BorderLayout.CENTER);
		frame.add(createLowerPanel(), BorderLayout.SOUTH);
		
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		return frame;
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu diffMenu = new JMenu("Difficulty");
		menuBar.add(diffMenu);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem instructionsItem = new JMenuItem("Instructions...");
		instructionsItem.addActionListener(event -> new InstructionsDialog(this));
		helpMenu.add(instructionsItem);
		
		JMenuItem usernameItem = new JMenuItem("Player");
		usernameItem.addActionListener(event -> new PlayerDialog(this));
		menuBar.add(usernameItem);
		
	
		
		JMenuItem easyMode = new JMenuItem("Easy");
		easyMode.addActionListener(event -> newGame("easy"));
		diffMenu.add(easyMode);
		
		JMenuItem medMode = new JMenuItem("Medium");
		medMode.addActionListener(event -> newGame("medium"));
		diffMenu.add(medMode);
		
		JMenuItem hardMode = new JMenuItem("Hard");
		hardMode.addActionListener(event -> newGame("hard"));
		diffMenu.add(hardMode);
		
		JMenuItem exitButton = new JMenuItem("Exit");
		exitButton.addActionListener(event -> shutdown());
		menuBar.add(exitButton);
		
		
		return menuBar;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", new CancelAction());
		
		JLabel label = new JLabel("Memory Game");
		label.setFont(new Font("Calibri", Font.BOLD, 25));
		panel.add(label);
		
	
		
		return panel;
	}
	
	private JPanel createLowerPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel usernameLabel = new JLabel(this.model.getUserName());
		
		panel.add(this.turnAndDiffLabel, BorderLayout.WEST);
		panel.add(usernameLabel, BorderLayout.EAST);
		this.turnAndDiffLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		return(panel);
	}
	
	private JPanel createGamePanel() {
		
		JPanel panel = new JPanel (new GridLayout(model.getRowCount(), model.getColumnCount()));
		
		
		Tile[][] temp = this.model.getTileGrid();
		
		for(int i = 0; i < model.getRowCount(); i++) {
			for(int c = 0; c < model.getColumnCount(); c++) {
				
				String shownIcon = temp[i][c].getSymbol();
				
				
				JToggleButton toggleButton = new JToggleButton("?", false);
				
				
				ItemListener itemListener = new ItemListener() {
					public void itemStateChanged(ItemEvent itemEvent) {
						int state = itemEvent.getStateChange();
						
						if(state == itemEvent.SELECTED) {
							toggleButton.setText(shownIcon);
							
						}
						else {
							toggleButton.setText("?");
						
						}
					}
				};
				
				toggleButton.addItemListener(itemListener);
				toggleButton.setActionCommand("Compare");
				toggleButton.addActionListener(action);
				toggleButton.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
				panel.add(toggleButton);
			}
		}
		
		panel.setPreferredSize(new Dimension(640,480));
		
		return(panel);
		
	}
	
	public void shutdown() {
		//model.getStatistics().writeStatistics();
		frame.dispose();
		System.exit(0);
	}
	
	public void updateTurnLabel() {
		turnAndDiffLabel.setText(DiffLabelValue + model.getTurns());
	}
	
	public void updateNumberFlipped() {
		this.numberFlipped +=2;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public int getNumberFlipped() {
		return(this.numberFlipped);
	}
	
	public GameFrame newGame(String d) {	
		
		GameModel newModel = new GameModel(d);
		
		GameFrame result = new GameFrame(newModel);
	
		this.frame.dispose();
		
		return(result);
	}
	
	public GameModel getModel() {
		return(this.model);
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			shutdown();
		}
	
	

	
}
}
