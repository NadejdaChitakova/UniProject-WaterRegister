package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class WaterSourceRepository extends DBHelper {

	
	public static void Save(JTextField text, JTextField number, JTextField num,JComboBox<String> combo,JTable table) {
		conn = DBHelper.getConnection();
		String sql = "insert into water values(null,?,?,?,?)";
		int areaId = getById(combo);

		try {
			state= conn.prepareStatement(sql);
			state.setString(1, text.getText());
			state.setInt(2,Integer.parseInt(number.getText()));
			state.setInt(3, Integer.parseInt(num.getText()));
			state.setInt(4, areaId);
			state.execute();
			table.setModel(DBHelper.refreshWaterSourceTable());
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
		String sql = "delete from water where water_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, WaterSourcePanel.id);
			state.execute();
			table.setModel(DBHelper.refreshWaterSourceTable());
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	
	public static void Edit(JTextField name, JTextField space, JTextField depth, JComboBox<String> combo,JTable table) {
		String nameValue = name.getText();
		int spaceValue = Integer.parseInt(space.getText().toString());
		int depthValue = Integer.parseInt(depth.getText().toString());
		int comboValue = getById(combo);
		
		String sql = "update water set name=?, space=?,depth=?,area_id=? where water_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setString(1, nameValue);
			state.setInt(2, spaceValue);
			state.setInt(3, depthValue);
			state.setInt(4, comboValue);
			state.setInt(5, WaterSourcePanel.id);
			state.execute();
			table.setModel(DBHelper.refreshWaterSourceTable());
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public static void Search(JComboBox<String> combo,JComboBox<String> regionCombo,JTable table) {
		String comboValue = combo.getSelectedItem().toString();
		int regionId = getById(regionCombo);
		System.out.println(comboValue);
		System.out.println(regionId);
		conn = DBHelper.getConnection();
		try {
			state = conn.prepareStatement("SELECT W.WATER_ID,W.NAME,W.SPACE,W.DEPTH,A.AREA_NAME FROM WATER W JOIN AREA A ON W.AREA_ID=A.AREA_ID AND W.NAME=? and a.area_id=?");
			state.setString(1, comboValue);
			state.setInt(2, regionId);
			result = state.executeQuery();
			table.setModel(DBHelper.getSearchedWaterSource(comboValue, regionId, table));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static int getById(JComboBox<String> combo) {
		
		conn = DBHelper.getConnection();
		String comboString = combo.getSelectedItem().toString();
		String sql = "SELECT area_id from area where area_name =?";
		int areaId = 0;

			try {
				state = conn.prepareStatement(sql);
				state.setString(1, comboString);
				DBHelper.result = state.executeQuery();
				while (DBHelper.result.next()) {
					areaId = (int)DBHelper.result.getInt("area_id");
					System.out.println("areaid"+areaId);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return areaId;
	}
//	public static void getSearchedRow(JComboBox waterSource, int regionId,JTable table) {
//		String comboValue = waterSource.getSelectedItem().toString();
//		//String comboRegion = regionName.getSelectedItem().toString();
//		conn = DBHelper.getConnection();
//		try {
//	
//			state = conn.prepareStatement("select W.WATER_ID,W.NAME,W.SPACE,W.DEPTH,A.AREA_NAME FROM WATER W "
//					+ "JOIN AREA A ON W.AREA_ID=A.AREA_ID AND W.NAME=? and a.area_id=?");
//			state.setString(1, comboValue);
//			state.setInt(2, regionId);
//			result = state.executeQuery();
//			table.setModel(DBHelper.getSearchedWaterSource(comboValue, regionId, table));
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//}
	public static ArrayList<String> getWaterSources(){
		ArrayList<String> sources = new ArrayList<String>();
		conn = DBHelper.getConnection();
		String sql ="select name from water";
			sources.add("");
		try {
			state = conn.prepareStatement(sql);
			DBHelper.result = state.executeQuery();
			while(DBHelper.result.next()) {
				String value = DBHelper.result.getObject(1).toString();
				sources.add(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sources;
	}
	
	public static ArrayList<String> loadWaterSourceNames(){
		ArrayList<String> waterSources = new ArrayList<String>();
		conn = DBHelper.getConnection();
		String sql ="select name from water";
		waterSources.add("");
		try {
			state = conn.prepareStatement(sql);
			DBHelper.result = state.executeQuery();
			while(DBHelper.result.next()) {
				String value = DBHelper.result.getObject(1).toString();
				waterSources.add(value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return waterSources;
	}
	public static void RefreshWSCombo(JComboBox<String> combo) {
		ArrayList<String> waterSources = new ArrayList<String>(loadWaterSourceNames());
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	    model.addAll(waterSources);
		combo.setModel(model);
	}
	
}

