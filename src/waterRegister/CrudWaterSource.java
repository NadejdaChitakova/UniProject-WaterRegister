package waterRegister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CrudWaterSource {
	
	private static Connection conn = null;
	private static PreparedStatement state = null;
	
	static void insertData(JTextField text, JTextField number, JTextField num,JComboBox<String> combo) {
		conn = DBHelper.getConnection();
		String sql = "insert into water values(null,?,?,?,?)";
		int areaId = getAreaId(combo);

		try {
			state= conn.prepareStatement(sql);
			state.setString(1, text.getText());
			state.setInt(2,Integer.parseInt(number.getText()));
			state.setInt(3, Integer.parseInt(num.getText()));
			state.setInt(4, areaId);
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

	static void editData(JTextField name, JTextField space, JTextField depth, JComboBox<String> combo) {
		String nameValue = name.getText();
		int spaceValue = Integer.parseInt(space.getText().toString());
		int depthValue = Integer.parseInt(depth.getText().toString());
		int comboValue = getAreaId(combo);
		
		String sql = "update water set name="+"'"+nameValue+"'"+
					", space="+spaceValue+",depth="+depthValue+",area_id="+comboValue+" where water_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, CreateWaterFrame.id);
			state.execute();
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	static ArrayList<String> getAll() {
		ArrayList<String> areas = new ArrayList<String>();
		conn = DBHelper.getConnection();
		String sql ="select area_name from area";
		try {
			state = conn.prepareStatement(sql);
			DBHelper.result = state.executeQuery();
			while(DBHelper.result.next()) {
				String value = DBHelper.result.getObject(1).toString();
				areas.add(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return areas;
	}
	static int getAreaId(JComboBox<String> combo) {
		
		conn = DBHelper.getConnection();
		String comboString = combo.getSelectedItem().toString();
		String sql = "SELECT area_id from area where area_name = '"+comboString+"'";
		int areaId = 0;
		System.out.println(comboString);
			try {
				state = conn.prepareStatement(sql);
				DBHelper.result = state.executeQuery();
				while (DBHelper.result.next()) {
					areaId = (int)DBHelper.result.getInt("area_id");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return areaId;
	}	
}
