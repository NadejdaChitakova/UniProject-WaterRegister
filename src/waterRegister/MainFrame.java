package waterRegister;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.w3c.dom.Text;

public class MainFrame extends JFrame{
	
	JTabbedPane tab = new JTabbedPane();
	
	
	//-------------------------Tabs
	
		JPanel region = new JPanel();
		JPanel reservoir = new JPanel();
		JPanel responsible = new JPanel();
		
		
	
	public MainFrame() {
		
		//------------------------- Frame setting
		this.setSize(500,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//------------------------- Add panels in tab
		tab.addTab("Регион", region);
		tab.add(reservoir,"Водоем");
		tab.add(responsible,"Отговорник на водоем");

		this.add(tab);
		
		
		this.setVisible(true);
		
	}
	public void clearForm(JTextField name, JTextField space, JTextField population) {
		name.setText("");
		space.setText("");
		population.setText("");
	}
	
	public void fillTextField(int row ,JTable table, JTextField name, JTextField space, JTextField population) {
		name.setText(table.getValueAt(row, 1).toString());
		space.setText(table.getValueAt(row, 2).toString());
		population.setText(table.getValueAt(row, 3).toString());
	}
	
}
