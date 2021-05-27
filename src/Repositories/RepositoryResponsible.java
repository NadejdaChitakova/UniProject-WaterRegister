package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Data.DBHelper;
import UI.ResponsiblePanel;

public class RepositoryResponsible {
	private static Connection conn = null;
	private static PreparedStatement state = null;
	
	public static void AddData(JTextField text, JTextField lname, JTextField num,JTextField comment,JComboBox<String>combo) {
			
		conn = DBHelper.getConnection();
		String sql = "insert into responsible values(null,?,?,?,?,?)";
		int waterId = getById(combo);
		try {
			state= conn.prepareStatement(sql);
			state.setString(1, text.getText());
			state.setString(2,lname.getText());
			state.setInt(3, Integer.parseInt(num.getText()));
			state.setString(4,comment.getText());
			state.setInt(5,waterId);
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
	static int getById(JComboBox<String> combo) {
		
		conn = DBHelper.getConnection();
		String comboString = combo.getSelectedItem().toString();
		String sql = "SELECT water_id from water where name = '"+comboString+"'";
		int areaId = 0;
		System.out.println(comboString);
			try {
				state = conn.prepareStatement(sql);
				DBHelper.result = state.executeQuery();
				while (DBHelper.result.next()) {
					areaId = (int)DBHelper.result.getInt("water_id");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return areaId;
	}	
	
	public static ArrayList<String> getAll() {
		ArrayList<String> waters = new ArrayList<String>();
		conn = DBHelper.getConnection();
		String sql ="select name from water";
		try {
			state = conn.prepareStatement(sql);
			DBHelper.result = state.executeQuery();
			while(DBHelper.result.next()) {
				String value = DBHelper.result.getObject(1).toString();
				waters.add(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return waters;
	}
	public static void Delete() {
		conn = DBHelper.getConnection();
		String sql = "delete from responsible where responsible_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1,ResponsiblePanel.id);
			state.execute();
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
}
