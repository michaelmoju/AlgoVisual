package server;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
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
			PreparedStatement ppStatement = conn.prepareStatement("select progress from userinfo where name = ? and pwd = ?");
			
			ppStatement.setString(1, name);
			ppStatement.setString(2, pwd);
			ResultSet rs = ppStatement.executeQuery();
			boolean isNext = rs.next();
			
			System.out.println(isNext);
			if (!isNext) {
				System.out.println("login fail");
				return new ServerResponse(false);
			} else {
				System.out.println("login successfully");
				Object progressString = rs.getObject(1);
				System.out.println(progressString.getClass());
				System.out.println(progressString);
				
				if (progressString instanceof String) {
					String[] parts = ((String) progressString).split(",");
					HashMap<String, Boolean> progress = new HashMap<String, Boolean>();
					for (String part: parts) {
						part = part.replace("{", "");
						part = part.replace("}", "");
						String[] empData = part.split("=");
						String algoName = empData[0].trim();
						String b = empData[1].trim();
						Boolean complete = null;
						if (b.equals("true")) complete = true;
						else if (b.equals("false"))complete = false;
						else System.err.println("parsing error: " + b);
						progress.put(algoName, complete);
 					}
					System.out.println(progress.getClass());
					System.out.println(progress);
					return new ServerResponse(true, progress);
				} else {
					System.err.println("progress class wrong!");
				}
				
			}
	        
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}
		return new ServerResponse(false);
		
	}
	

	public ServerResponse signup(String name, String pwd){	
		
		PreparedStatement ppStatement_name;
		try {
			ppStatement_name = conn.prepareStatement("select * from userinfo where name = ?;");
			
			ppStatement_name.setString(1, name);
			
			ResultSet RS_name = ppStatement_name.executeQuery();
			
			if (RS_name.next()) {
				System.out.println("Username already exists, please use another username.");
				return new ServerResponse(false);
			} else {
				System.out.println("signup successfully");
				ServerResponse s = new ServerResponse(true);
				PreparedStatement ppStatement_insert = conn.prepareStatement(" insert into userinfo values (?, ?, ?);");	
				ppStatement_insert.setString(1, name);
				ppStatement_insert.setString(2, pwd);
				System.out.println(s.getProgress().getClass());
				ppStatement_insert.setObject(3, s.getProgress());;
				ppStatement_insert.executeUpdate();
				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ServerResponse(false);
		
	}
	
	public ServerResponse store(String name, String pwd, HashMap<String, Boolean> progress) {
		return null;
	}
	
	public String show_all() {
	ArrayList<String> StringArray = new ArrayList<String>();
	String query = "select * from userinfo";
	try (Statement stmt = conn.createStatement()) {
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String name = rs.getString("name");
			String progress = (String)rs.getString("progress");
			StringArray.add(name + ',' + progress + '\n');
		}
		
		String s = new String();
		for(String ele:StringArray) {
            s += ele;
        }
		return s;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return "";
}
	
	
	public static void main(String[] args) {
		UserDb myDatabase = new UserDb();
		
		myDatabase.signup("TEST6", "123");
		myDatabase.login("TEST6", "123");
		
	}
}
