import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import sort.SortPanel;
import server.ClientRequest;
import server.ServerResponse;

public class MainPanel extends JFrame{
	private JPanel AlgoPanel; ///
	private JMenuBar menuBar;
	private JPanel buttonPanel;
	private JPanel progressPanel;
	private JPanel playPanel;
	private HashMap<String, Boolean> progress;
//	private Socket socket;
	private String name;
	private String pwd;
	
	public MainPanel(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 200);
		this.setLayout(new BorderLayout());
		
		// menubar
		createMenuBar();
		this.setJMenuBar(menuBar);
		
		// AlgoPanel
		AlgoPanel = new SortPanel("bubble", 10); /////
		this.add(AlgoPanel, BorderLayout.CENTER);
	}
	
	public MainPanel(String name, String pwd, HashMap<String, Boolean> progressMap) {
		this(name, pwd);
		this.progress = progressMap;
		createProgressPanel();
		createPlayPanel();
	}
	
	private void createPlayPanel() {
		
	}
	
	private void createProgressPanel() {
		progressPanel = new JPanel();
		String[] algoNames = ServerResponse.algoNames;
		progressPanel.setLayout(new GridLayout(algoNames.length, 1));
		
		//map algo	
		for (String algoName: algoNames) {
			JCheckBox algoBox = new JCheckBox(algoName);
			algoBox.setSelected(this.progress.get(algoName));
			algoBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange()==1) progress.put(algoName, true);
					else progress.put(algoName, false);
					System.out.println(progress);
					}
				}
			);
			progressPanel.add(algoBox);
		}
		
		progressPanel.setBackground(Color.white);
		this.add(progressPanel, BorderLayout.EAST);
		
	}
	
	private void createMenuBar() {
		menuBar = new JMenuBar();  
		JMenu progressMenu = new JMenu("My Progress");
		JMenu algoMenu = new JMenu("Algorithms");
		JMenu setMenu = new JMenu("Settings");
		
		
		class StoreActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				ObjectOutputStream toServer;
				try {
					Socket socket = new Socket("localhost", 8000);
					toServer = new ObjectOutputStream(socket.getOutputStream());
					ClientRequest action = new ClientRequest("store", name, pwd, progress);
					toServer.writeObject(action);
					toServer.flush();
					
//					ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
//					ServerResponse response = (ServerResponse)fromServer.readObject();
					
//					if (response.getIsLogin()) {
//						System.out.println("Stored successfully");
//						MainPanel mainPanel = new MainPanel(response.getProgress(), socket);
//						mainPanel.setVisible(true);
//						setVisible(false);
//					} else {
//						System.out.println("login wrong");
//						messageLabel.setText("incorrect username or password");
//						messageLabel.setForeground(Color.red);
//						usernameText.setText("");
//						passwordText.setText("");
//					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		
		//main menu
//		JMenuItem loginItem = new JMenuItem("Log in");
		JMenuItem StoreItem = new JMenuItem("Store");
		JMenuItem fileExitItem = new JMenuItem("Exit");
		
//		loginItem.addActionListener(new LoginActionListener());
		StoreItem.addActionListener(new StoreActionListener());
		fileExitItem.addActionListener((e) -> System.exit(0));
		
//		progressMenu.add(loginItem);
		progressMenu.add(StoreItem);
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

//	public static void main(String[] args) {
//		HashMap progressMap = new HashMap<String, Boolean>();
//		
//		for (String algoName: ServerResponse.algoNames) {
//			progressMap.put(algoName, false);
//		}
//		MainPanel mainPanel = new MainPanel(progressMap);
//		mainPanel.setVisible(true);
//	}

}
