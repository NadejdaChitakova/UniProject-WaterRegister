package waterRegister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

	private static Connection conn = null;
	public static MyModel model = null;
	private static PreparedStatement state = null;
	
	public static ResultSet result = null;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("org.h2.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:h2:C:\\Users\\nadej\\eclipse-workspace\\waterRegister;AUTO_SERVER=TRUE", "sa", "са");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	static MyModel getAllData() {
		conn = getConnection();
		try {
			state = conn.prepareStatement("SELECT * FROM AREA");
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return model;
	}
	
}
