package Data;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.w3c.dom.Text;

public class Frame extends JFrame{
	
	JTabbedPane tab = new JTabbedPane();
	
	
	//-------------------------Tabs
		
		RegionPanel frame = new RegionPanel();
		WaterSourcePanel waterSource = new WaterSourcePanel();
		ResponsiblePanel responsible = new ResponsiblePanel();
		
	
	public Frame() {
		
		//------------------------- Frame setting
		this.setSize(500,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//------------------------- Add panels in tab
		tab.add(frame,"Регион");
		tab.add(waterSource,"Водоем");
		tab.add(responsible,"Отговорник на водоем");

		
		this.add(tab);
		
		
		this.setVisible(true);
		
	}
	public static void clearForm(JTextField name, JTextField space, JTextField population,JTextField comment) {
		name.setText("");
		space.setText("");
		population.setText("");
		if (comment != null) {
			comment.setText("");
		}
	}
	
	public static void fillTextField(int row ,JTable table, JTextField name, JTextField space, JTextField population,JTextField age,JTextField comment) {
//		name.setText(table.getValueAt(row, 1).toString());
//		space.setText(table.getValueAt(row, 2).toString());
//		population.setText(table.getValueAt(row, 3).toString());
//		if (comment != null && age != null) {
//			comment.setText(table.getValueAt(row, 4).toString());
//			age.setText(table.getValueAt(row, 5).toString());
//		}
		System.out.println(row);
	}
//	public static void fillR(JTextField firstname, JTextField lastname, JTextField age, JTextField comment) {
//		
//	}
	
}
