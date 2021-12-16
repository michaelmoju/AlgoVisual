import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.ClientRequest;
import server.ServerResponse;
import server.UserDb;

public class Server extends JFrame implements Runnable{
	private JTextArea resultBox;
	private UserDb db;
	private JLabel msg;
	
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
		
		// msg
		this.msg = new JLabel("AAAAAAAAA");
		this.add(this.msg);
		
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
				while (true) {
					ClientRequest request = (ClientRequest)inputFromClient.readObject();
					ServerResponse response = null;
					System.out.println("action:: " + request.getAction());
					msg.setText(request.getAction() + ", " + request.getName() + ", "+ request.getPwd() );
					switch(request.getAction()) {
					case "login": 
						response = db.login(request.getName(), request.getPwd());
						break;
					case "register":
						response = db.signup(request.getName(), request.getPwd());
						break;
					case "store": {
						response = db.store(request.getName(), request.getPwd(), request.getProgress());
						break;
					}
					default: 
						System.out.println("no action: " + request.getAction());
						break;
					}
					
					outputToClient.writeObject(response);
				}
			} catch(IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		Server s = new Server();
		
		final int AREA_ROWS = 10;
		final int AREA_COLUMNS = 30;

		final JTextArea textArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
	    textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		textArea.append(s.db.show_all());
		
		s.add(scrollPane);
		
		s.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setVisible(true);
	}
	
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 200;
}
