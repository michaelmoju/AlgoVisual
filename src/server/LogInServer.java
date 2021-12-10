package server;

public class LogInServer {

	public LogInServer() {
		
	}
	
	public static Boolean login(String username, String password) {
		if (username.equals("michael") && password.equals("123")) return true; 
		return false;
	}
	
	public void register(String username, String password) {
		
	}
}
