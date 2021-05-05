package waterRegister;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	static void insertDataInTable(String tableName, JTextField areaNameTF, JTextField areaSpaceTF, JTextField areaPopulationTF) {
		
		conn = getConnection();
		String sql = "insert into "+ tableName+" values(null,?,?,?)";
		try {
			state= conn.prepareStatement(sql);
			state.setString(1, areaNameTF.getText());
			state.setInt(2,Integer.parseInt(areaSpaceTF.getText()));
			state.setInt(3, Integer.parseInt(areaPopulationTF.getText()));
			state.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			state.close();
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	static void deleteDataFromTable(String tableName) {
		conn = DBHelper.getConnection();
		String sql = "delete from "+ tableName +" where id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, CreateRegionFrame.id);
			state.execute();
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	
	static void editData(String tableName,JTextField name, JTextField space,JTextField population) {//popravi go za drugite tablici
		
		conn = getConnection();
		
		//------------------------- convert jTextField
		String strName = name.getText();
		int intSpace = Integer.parseInt(space.getText().toString());
		int intPopulation = Integer.parseInt(population.getText().toString());
		
		//------------------------- SQL statement
		String sql = "update "+tableName+" set area_name= "+ "'"+strName+"'"+",area_space="+ intSpace+",population="+
					intPopulation+" where id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, CreateRegionFrame.id);
			state.execute();
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	static void getRowData(int id) {
		conn = getConnection();

		try {
			state = conn.prepareStatement("SELECT * FROM AREA WHERE ID="+id);
			result = state.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (result.next()) {
				int identity = result.getInt("id");
				System.out.println(identity);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
}
	

