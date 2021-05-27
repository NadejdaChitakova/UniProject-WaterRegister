package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import Data.DBHelper;
import Data.MyModel;
import UI.RegionPanel;

public class RepositoryRegion {

	private static Connection conn = null;
	private static PreparedStatement state = null;
	
	public static MyModel model = null;
	public static ResultSet result = null;
	
	public static void addData(JTextField text, JTextField number, JTextField num) {
		
		conn = DBHelper.getConnection();
		String sql = "insert into area values(null,?,?,?)";
		try {
			state= conn.prepareStatement(sql);
			state.setString(1, text.getText());
			state.setInt(2,Integer.parseInt(number.getText()));
			state.setInt(3, Integer.parseInt(num.getText()));
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
	
	public static void Delete() {
		conn = DBHelper.getConnection();
		String sql = "delete from area where area_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, RegionPanel.id);
			state.execute();
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	
	public static void edit(JTextField name, JTextField space,JTextField population) {
		conn = DBHelper.getConnection();
		
		//------------------------- convert jTextField
		String nameString = name.getText();
		int intSpace = Integer.parseInt(space.getText().toString());
		int intPopulation = Integer.parseInt(population.getText().toString());
		
		//------------------------- SQL statement
		String sql = "update area set area_name= "+ "'"+nameString+"'"+",area_space="+ intSpace+",population="+
					intPopulation+" where area_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, RegionPanel.id);
			state.execute();
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	static void getRowData(int id) {
		conn = DBHelper.getConnection();

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
	static void getRegionByName() {
		
	}
}
