package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;



public class PlayerDialog extends JDialog{
private static final long serialVersionUID = 1L;
	
	private final CancelAction cancelAction;
	

	private GameFrame view;

	public String name;
	
	
	public PlayerDialog(GameFrame view) {
		super(view.getFrame(), "Player", true);
		this.cancelAction = new CancelAction();
		
		this.view = view;
		
	
		add(usernamePanel(), BorderLayout.NORTH);
		add(createButtonPanel(), BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(view.getFrame());
		setVisible(true);
		
		
		
		
	}
	
	private JPanel usernamePanel() {
		
		JPanel panel = new JPanel(new FlowLayout());
		JTextField box = new JTextField(20);
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				view.getModel().setUserName(box.getText());
			}
		});
		
		
		JLabel label = new JLabel("username: ");
		panel.add(label);
		panel.add(box);
		
		
		this.name = box.getText();
		
		return(panel);
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", cancelAction);
		
		JButton button = new JButton("Back to Game");
		button.addActionListener(cancelAction);
		panel.add(button);
		
		return panel;
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			dispose();
		}
		
	}
	


}
