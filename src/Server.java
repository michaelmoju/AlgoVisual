import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.ClientRequest;
import server.ServerResponse;
import server.UserDb;

public class Server extends JFrame implements Runnable{
	private JTextArea resultBox;
	private UserDb db;
	
	public Server() {
		super("Teacher Server");
		this.setTitle("Teacher Server");
		this.setSize(400,200);
		
		// add result box
		resultBox = new JTextArea(10, 30);
		resultBox.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultBox);
		this.add(scrollPane);
		
		//connect database
		this.db = new UserDb();
		
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(new HandleAClient(socket)).start();
			}
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}

	class HandleAClient implements Runnable {
		private Socket socket;
		
		public HandleAClient(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				ObjectInputStream inputFromClient = new ObjectInputStream(
						socket.getInputStream());
				ObjectOutputStream outputToClient = new ObjectOutputStream(
				          socket.getOutputStream());
				
				ClientRequest request = (ClientRequest)inputFromClient.readObject();
				ServerResponse response = null;
				
				switch(request.getAction()) {
				case "login": 
					response = db.login(request.getName(), request.getPwd());
					break;
				case "register":
					response = db.signup(request.getName(), request.getPwd());
				case "store": {
					response = db.store(request.getName(), request.getPwd(), request.getProgress());
					break;
				}
				default: 
					System.out.println("no action: " + request.getAction());
					break;
				}
				
				outputToClient.writeObject(response);
				
			} catch(IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		Server s = new Server();
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setVisible(true);
	}
}
