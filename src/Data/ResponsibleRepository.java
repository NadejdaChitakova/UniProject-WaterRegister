package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ResponsibleRepository {
	private static Connection conn = null;
	private static PreparedStatement state = null;
	
	public static MyModel model = null;
	public static ResultSet result = null;
	
	public static void Save(JTextField text, JTextField lname, JTextField num,JTextField comment,JComboBox<String>combo,JTable table) {
			
		conn = DBHelper.getConnection();
		String sql = "insert into responsible values(null,?,?,?,?,?)";
		int waterId = getById(combo);
		System.out.println(waterId);
		try {
			state= conn.prepareStatement(sql);
			state.setString(1, text.getText());
			state.setString(2,lname.getText());
			state.setInt(3, Integer.parseInt(num.getText()));
			state.setString(4,comment.getText());
			state.setInt(5,waterId);
			state.execute();
			table.setModel(DBHelper.refreshResponsibleTable());
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
	
	public static void Delete(JTable table) {
		conn = DBHelper.getConnection();
		String sql = "delete from responsible where responsible_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1,ResponsiblePanel.id);
			state.execute();
			table.setModel(DBHelper.refreshResponsibleTable());

		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	
	public static void Edit(JTextField text, JTextField lnameF, JTextField num,JTextField commentF,JComboBox<String>combo,JTable table) {
		String fname = text.getText();
		String lname = lnameF.getText();
		int age = Integer.parseInt(num.getText().toString());
		String comment = commentF.getText();
		int comboValue = getById(combo);
		
		String sql = "update responsible set firstname=?, lastname=?,age=?,comment=?,water_id=? where responsible_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setString(1,fname);
			state.setString(2, lname);
			state.setInt(3, age);
			state.setString(4, comment);
			state.setInt(5, comboValue);
			state.setInt(6, ResponsiblePanel.id);
			state.execute();
			table.setModel(DBHelper.refreshResponsibleTable());
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
		public static void Search(JComboBox combo) {
		String comboValue = combo.getSelectedItem().toString();
		conn = DBHelper.getConnection();
		try {
			state = conn.prepareStatement("SELECT * FROM RESPONSIBLE WHERE NAME='"+ comboValue +"'");
			result = state.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	static int getById(JComboBox<String> combo) {
		
		conn = DBHelper.getConnection();
		String comboString = combo.getSelectedItem().toString();
		String sql = "SELECT water_id from water where name=?";
		int areaId = 0;

			try {
				state = conn.prepareStatement(sql);
				state.setString(1, comboString);
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
}
