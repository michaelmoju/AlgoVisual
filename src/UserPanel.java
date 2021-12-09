import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class UserPanel extends JFrame{
	private JPanel mainPanel;
	private JMenuBar menuBar;
	
	public UserPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// menubar
		createMenuBar();
		this.setJMenuBar(menuBar);
	}
	
	private void createMenuBar() {
		menuBar = new JMenuBar();  
		JMenu progressMenu = new JMenu("Progress");
		JMenu algoMenu = new JMenu("Algorithms");
		JMenu setMenu = new JMenu("");
	
		
		// algorithms
		JMenuItem bubbleSort = new JMenuItem("Bubble sort");
		JMenuItem insertSort = new JMenuItem("Insertion sort");
		algoMenu.add(bubbleSort);
		algoMenu.add(insertSort);
		
		//setting 
		
		

	}
	
	private static void main(String[] args ) {
		UserPanel userPanel = new UserPanel();
		userPanel.setVisible(true);
	}

}
