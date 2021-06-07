package Data;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

class RegionRepository extends DBHelper{
	
	public static void Save(JTextField text, JTextField number, JTextField num, JTable table) {
		conn = DBHelper.getConnection();
		String sql = "insert into area values(null,?,?,?)";
		try {
			state= conn.prepareStatement(sql);
			state.setString(1, text.getText());
			state.setInt(2,Integer.parseInt(number.getText()));
			state.setInt(3, Integer.parseInt(num.getText()));
			state.execute();
		    table.setModel(DBHelper.refreshRegionTable());
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
		String sql = "delete from area where area_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, RegionPanel.id);
			state.execute();
		    table.setModel(DBHelper.refreshRegionTable());
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void Edit(JTextField name, JTextField space,JTextField population,JTable table) {
		conn = DBHelper.getConnection();
		
		//------------------------- convert jTextField
		String nameValue = name.getText();
		int spaceValue = Integer.parseInt(space.getText().toString());
		int populationValue = Integer.parseInt(population.getText().toString());
		
		//------------------------- SQL statement
		String sql = "update area set area_name=?,area_space=?,population=? where area_id=?";
		try {
			state = conn.prepareStatement(sql);
			state.setString(1, nameValue);
			state.setInt(2, spaceValue);
			state.setInt(3, populationValue);
			state.setInt(4, RegionPanel.id);
			state.execute();
			table.setModel(DBHelper.refreshRegionTable());
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void Search(JComboBox<String> combo,JTable table) {
		String comboValue = combo.getSelectedItem().toString();
		conn = DBHelper.getConnection();
		try {
			state = conn.prepareStatement("SELECT * FROM AREA WHERE AREA_NAME=?");
			state.setString(1, comboValue);
			result = state.executeQuery();
			table.setModel(DBHelper.getSearchedRegion(combo));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static ArrayList<String> loadRegionNames() {
		ArrayList<String> areas = new ArrayList<String>();
		conn = DBHelper.getConnection();
		String sql ="select area_name from area";
			areas.add("");
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
	public static void RefreshCombo(JComboBox<String> combo) {
		ArrayList<String> regionValues = new ArrayList<String>(RegionRepository.loadRegionNames());
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	    model.addAll(regionValues);
		combo.setModel(model);
	}

}