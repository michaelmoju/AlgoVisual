import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainPanel extends JFrame{
	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JPanel buttonPanel;
	private HashMap<String, Boolean> progressMap;
	
	public MainPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);
		
		// menubar
		createMenuBar();
		this.setJMenuBar(menuBar);
	}
	
	public MainPanel(HashMap<String, Boolean> progressMap) {
		this();
		this.progressMap = progressMap;
	}
	
	private void createMenuBar() {
		menuBar = new JMenuBar();  
		JMenu progressMenu = new JMenu("AlgoVisual");
		JMenu algoMenu = new JMenu("Algorithms");
		JMenu setMenu = new JMenu("Settings");
		
		class LoginActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
			}
		}
		
		//main menu
		JMenuItem loginItem = new JMenuItem("Log in");
		JMenuItem LogoutItem = new JMenuItem("Log out");
		JMenuItem fileExitItem = new JMenuItem("Exit");
		
		loginItem.addActionListener(new LoginActionListener());
		fileExitItem.addActionListener((e) -> System.exit(0));
		
		progressMenu.add(loginItem);
		progressMenu.add(fileExitItem);
		
		// algorithms
		JMenuItem bubbleSort = new JMenuItem("Bubble sort");
		JMenuItem insertSort = new JMenuItem("Insertion sort");
		JMenuItem mergeSort = new JMenuItem("Merge sort");
		algoMenu.add(bubbleSort);
		algoMenu.add(insertSort);
		algoMenu.add(mergeSort);
		
		
		menuBar.add(progressMenu);
		menuBar.add(algoMenu);
		menuBar.add(setMenu);
		
		//setting 
		
		
	
	}

	public static void main(String[] args) {
		MainPanel mainPanel = new MainPanel();
		mainPanel.setVisible(true);
	}

}
