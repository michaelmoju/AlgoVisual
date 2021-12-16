package server;
import java.sql.*;
import java.util.HashMap;

import server.ServerResponse;

public class UserDb {
		private Connection conn;
	
	
	public UserDb() {		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:record.db");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public ServerResponse login(String name, String pwd) {
		try {
			PreparedStatement ppStatement = conn.prepareStatement("select * from userinfo where name = ? and pwd = ?");
			
			ppStatement.setString(1, name);
			ppStatement.setString(2, pwd);
			ResultSet rs = ppStatement.executeQuery();
			boolean isNext = rs.next();
			
			System.out.println(isNext);
			if (!isNext) {
				System.out.println("fail");
				//System.out.println("User ID: " + RS.getString("ID") + ", Name: " + RS.getString("Name") + ", Progress: " + RS.getString("Progress"));
				return new ServerResponse(false);
			} else {
				HashMap<String, Boolean> progress = (HashMap<String, Boolean>)rs.getBlob(3);
				return new ServerResponse(true, progress);
			}
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ServerResponse(false);
		
	}
	

	public ServerResponse signup(String name, String pwd){	
		
		PreparedStatement ppStatement_name;
		try {
			ppStatement_name = conn.prepareStatement("select * from userinfo where name = ?;");
			PreparedStatement ppStatement_pwd = conn.prepareStatement("select * from userinfo where pwd = ?;");
			
			ppStatement_name.setString(1, name);
			ppStatement_pwd.setString(1, pwd);
			
			ResultSet RS_name = ppStatement_name.executeQuery();
			ResultSet RS_pwd = ppStatement_pwd.executeQuery();
			
			
			if (RS_name.next()) {
				System.out.println("Username already exists, please use another username.");
				return new ServerResponse(false);
			}
			else if (RS_pwd.next()) {
				System.out.println("Password already exists, please use another password.");
				return new ServerResponse(false);
			}
			else {
//				HashMap<String, boolean> progress = {"Insertion sort": false, "Bubble sort", "Merge sort"};
				ServerResponse s = new ServerResponse(true);
				PreparedStatement ppStatement_insert = conn.prepareStatement(" insert into userinfo values (?, ?, ?);");	
				ppStatement_insert.setString(1, name);
				ppStatement_insert.setString(2, pwd);
				ppStatement_insert.setObject(3, s.getProgress());
				ppStatement_insert.executeUpdate();
				return s;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ServerResponse(false);
		
	}
	
	public ServerResponse store(String name, String pwd, HashMap<String, Boolean> progress) {
		return null;
	}
	
	
	public static void main(String[] args) {
		UserDb myDatabase = new UserDb();
		
		myDatabase.login("test4", "test4");
		
	}
}
