package waterRegister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;

public class CrudRegion {

	private static Connection conn = null;
	private static PreparedStatement state = null;
	
	static void insertIntoArea(JTextField text, JTextField number, JTextField num) {
		
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

	static void editDataRegion(JTextField name, JTextField space,JTextField population) {
		conn = DBHelper.getConnection();
		
		//------------------------- convert jTextField
		String nameString = name.getText();
		int intSpace = Integer.parseInt(space.getText().toString());
		int intPopulation = Integer.parseInt(population.getText().toString());
		
		//------------------------- SQL statement
		String sql = "update area set area_name= "+ "'"+nameString+"'"+",area_space="+ intSpace+",population="+
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
}
