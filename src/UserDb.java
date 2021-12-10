import java.sql.*;

public class UserDb {
		private Connection conn;
	
	
	public UserDb() {		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaProject", "root", "sandy870531");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public boolean login(String name, String pwd) throws SQLException {
		PreparedStatement ppStatement = conn.prepareStatement("select * from userinfo where name = ? and pwd = ?");
		
		ppStatement.setString(1, name);
		ppStatement.setString(2, pwd);
		ResultSet RS = ppStatement.executeQuery();
		
		if (RS.next()) {
			System.out.println("User ID: " + RS.getString("ID") + ", Name: " + RS.getString("Name") + ", Progress: " + RS.getString("Progress"));
			return true;
		}
        return false;
	}
	
	public boolean signup(String name, String pwd) throws SQLException {
		
		PreparedStatement ppStatement_name = conn.prepareStatement("select * from userinfo where name = ?");
		PreparedStatement ppStatement_pwd = conn.prepareStatement("select * from userinfo where pwd = ?");

		ppStatement_name.setString(1, name);
		ppStatement_pwd.setString(1, pwd);
		
		ResultSet RS_name = ppStatement_name.executeQuery();
		ResultSet RS_pwd = ppStatement_pwd.executeQuery();
		
		if (RS_name.next()) {
			System.out.println("Username already exists, please use another username.");
			return false;
		}
		else if (RS_pwd.next()) {
			System.out.println("Password already exists, please use another password.");
			return false;
		}
		else {
			PreparedStatement ppStatement_insert = conn.prepareStatement("insert into userinfo(name, pwd, progress) value (?, ?, 0);");	
			ppStatement_insert.setString(1, name);
			ppStatement_insert.setString(2, pwd);
			ppStatement_insert.executeUpdate();
			return true;
		}
        
	}
	
	
	public static void main(String[] args) {
		UserDb myDatabase = new UserDb();
		
		//test login
		try {
			myDatabase.login("Sandy", "sandy");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
