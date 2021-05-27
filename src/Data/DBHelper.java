package Data;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DBHelper {

	private static Connection conn = null;
	private static PreparedStatement state = null;
	
	public static MyModel model = null;
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
	
	public static MyModel getAllData(String sql) {
		conn = getConnection();
		try {
			state = conn.prepareStatement(sql);
			
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
	

//	static int getAreaId(JComboBox<String> combo) {
//		
//		conn = DBHelper.getConnection();
//		String comboString = combo.getSelectedItem().toString();
//		String sql = "select area_id from area where area_name='"+comboString+"'";
//		int areaId = 0;
//		try {
//			state = conn.prepareStatement(sql);
//			result = state.executeQuery();
//			model = new MyModel(result);
//			while (result.next()) {
//				areaId = result.getInt("id");
//				System.out.println(areaId);
//				
//			}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//
//		return areaId ;
//	}

}
	

