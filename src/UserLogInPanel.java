import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import server.UserDb;
import server.ClientRequest;
import server.ServerResponse;

public class UserLogInPanel extends JFrame{
	private JLabel messageLabel;
	private JTextField usernameText;
	private JTextField passwordText;
	private JButton loginButton;
	private JButton registerButton;
	private UserDb db;
	private Socket socket;
	
	public UserLogInPanel() {
		db = new UserDb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		setSize(600,200);
		this.setLayout(new GridLayout(4,2));
		
		//message
		messageLabel = new JLabel("Please enter your ", SwingConstants.CENTER);
		this.add(messageLabel);
		this.add(new JLabel(""));
		
		//text fields
		usernameText = new JTextField();
		passwordText = new JTextField();

		this.add(new JLabel("Username: ", SwingConstants.CENTER));
		this.add(usernameText);
		this.add(new JLabel("Password: ", SwingConstants.CENTER));
		this.add(passwordText);
		
		// buttons
		createButton();
		
		//socket
		try {
			socket = new Socket("localhost", 8000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void createButton() {
		class LoginListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = usernameText.getText();
				String pwd = passwordText.getText();

				try {
					ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
					ClientRequest action = new ClientRequest("login", name, pwd);
					toServer.writeObject(action);
					toServer.flush();
					
					ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
					ServerResponse response = (ServerResponse)fromServer.readObject();
					System.out.println(response.getIsLogin());
					if (response.getIsLogin()) {
						System.out.println("login successfully");
						MainPanel mainPanel = new MainPanel(response.getProgress(), socket);
						mainPanel.setVisible(true);
						setVisible(false);
					} else {
						System.out.println("login wrong");
						messageLabel.setText("incorrect username or password");
						messageLabel.setForeground(Color.red);
						usernameText.setText("");
						passwordText.setText("");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
		class RegisterListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = usernameText.getText();
				String pwd = passwordText.getText();
				
				try {
					ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
					ClientRequest action = new ClientRequest("login", name, pwd);
					toServer.writeObject(action);
					toServer.flush();
					
					ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
					ServerResponse response = (ServerResponse)fromServer.readObject();
					System.out.println(response.getIsLogin());
					if (response.getIsLogin()) {
						System.out.println("Register successfully");
						MainPanel mainPanel = new MainPanel(response.getProgress(), socket);
						mainPanel.setVisible(true);
						setVisible(false);
					} else {
						System.out.println("Register wrong");
						messageLabel.setText("incorrect username or password");
						messageLabel.setForeground(Color.red);
						usernameText.setText("");
						passwordText.setText("");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new LoginListener());
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(new RegisterListener());
		
		this.add(loginButton);
		this.add(registerButton);
	}
	
	public static void main(String[] args) {
		UserLogInPanel loginPanel = new UserLogInPanel();
		loginPanel.setVisible(true);
	}
	
}
