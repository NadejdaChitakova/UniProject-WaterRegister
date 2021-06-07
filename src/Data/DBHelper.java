package Data;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class DBHelper {

	protected static Connection conn = null;
	protected static PreparedStatement state = null;
	
	public static MyModel model = null;
	public static ResultSet result = null;
	
	public static Connection getConnection() {

		String username = "", url = "", password = "",driver = "";
			
			try {

				File file = new File("C:\\Users\\nadej\\OneDrive\\Работен плот\\config.properties");
				Scanner sc = new Scanner(file);
				
				while (sc.hasNextLine()) {
					driver = sc.nextLine().trim();
					url = sc.nextLine().trim();
					username = sc.nextLine().trim();
					password = sc.nextLine().trim();
					break;
				}
				
				Class.forName(driver);
				conn = DriverManager.getConnection(url,username,password);
	        
			} catch (IOException | SQLException | ClassNotFoundException e) {
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
	
	public static MyModel refreshRegionTable() {
		String sql = "SELECT * FROM AREA";
		return getAllData(sql);
	}
	public static MyModel refreshWaterSourceTable() {
		String sql = "SELECT W.WATER_ID,W.NAME,W.SPACE,W.DEPTH,A.AREA_NAME FROM WATER W JOIN AREA A ON W.AREA_ID=A.AREA_ID";
		return getAllData(sql);
	}
	
	public static MyModel refreshResponsibleTable() {
		String sql = "SELECT R.FIRSTNAME, R.LASTNAME,R.AGE,R.COMMENT, R.WATER_ID FROM RESPONSIBLE R JOIN WATER W ON R.WATER_ID=W.WATER_ID";
		return getAllData(sql);
	}
	
	public static MyModel getSearchedRegion(JComboBox combo) {
		
		String comboValue = combo.getSelectedItem().toString();
		conn = DBHelper.getConnection();
		
		try {
			state = conn.prepareStatement("SELECT * FROM AREA WHERE area_name=?");
			System.out.println(comboValue);
			state.setString(1,comboValue );
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	public static MyModel getSearchedWaterSource(String waterName, int regionId,JTable table) {
		conn = DBHelper.getConnection();
		try {
			state = conn.prepareStatement("SELECT W.WATER_ID,W.NAME,W.SPACE,W.DEPTH,A.AREA_NAME FROM WATER W"
										+ " JOIN AREA A ON W.AREA_ID=A.AREA_ID WHERE W.NAME=? and a.area_id=?");
			state.setString(1, waterName);
			state.setInt(2, regionId);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}
	
}
	

