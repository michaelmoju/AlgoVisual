import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import server.UserDb;

public class UserLogInPanel extends JFrame{
	private JLabel messageLabel;
	private JTextField usernameText;
	private JTextField passwordText;
	private JButton loginButton;
	private JButton registerButton;
	private UserDb db;
	
	public UserLogInPanel() {
		db = new UserDb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		setSize(600,200);
		this.setLayout(new GridLayout(4,2));
		
		//message
		messageLabel = new JLabel("Pleas enter your: ");
		this.add(messageLabel);
		this.add(new JLabel("null"));
		
		//text fields
		usernameText = new JTextField();
		passwordText = new JTextField();

		this.add(new JLabel("Username: "));
		this.add(usernameText);
		this.add(new JLabel("Password: "));
		this.add(passwordText);
		
		// buttons
		createButton();
		
	}
	
	private void createButton() {
		class LoginListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = usernameText.getText();
				String pwd = passwordText.getText();			
				
				if (db.login(name, pwd)) {
					System.out.println("login successfully");
					MainPanel mainPanel = new MainPanel();
					mainPanel.setVisible(true);
				} else {
					System.out.println("login wrong");
					messageLabel.setText("incorrect username or password");
					messageLabel.setForeground(Color.red);
					usernameText.setText("");
					passwordText.setText("fff");
				}
			}
			
		}
		
		class RegisterListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = usernameText.getText();
				String pwd = passwordText.getText();
				
				if (db.signup(name, pwd)) {
					System.out.println("Sign up successfully");
					MainPanel mainPanel = new MainPanel();
					mainPanel.setVisible(true);
				} else {
					System.out.println("Sign up wrong");
					messageLabel.setText("Username or password already been used");
					messageLabel.setForeground(Color.red);
					usernameText.setText("");
					passwordText.setText("");
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
