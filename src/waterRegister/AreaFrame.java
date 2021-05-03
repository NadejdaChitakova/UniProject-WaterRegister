package waterRegister;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AreaFrame extends JFrame{
	
	Connection conn=null;
	PreparedStatement state=null;
	
	public int id  = -1;
	
	JTabbedPane tab = new JTabbedPane();
	
	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);
	
	//-------------------------Tabs
	
	JPanel area = new JPanel();
	JPanel reservoir = new JPanel();
	JPanel responsible = new JPanel();
	
	
	//------------------------- Area panels
	
	JPanel formPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel tablePanel = new JPanel();

	//------------------------- Area Labels
	
	JLabel areaNameL = new JLabel("име:");
	JLabel areaSpaceL = new JLabel("площ:");
	JLabel areaPopulationL = new JLabel("население:");
	
	//------------------------- Area Text Fields
	
	JTextField areaNameTF = new JTextField();
	JTextField areaSpaceTF = new JTextField();
	JTextField areaPopulationTF = new JTextField();
	
	//------------------------- Area Buttons
	
	JButton areaAdd = new JButton("Добави");
	JButton areaDel = new JButton("Изтрии");
	JButton areaEdit = new JButton("Редактирай");
	
	
	public AreaFrame(){
		
		this.setSize(500,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		area.setLayout(new GridLayout(3,1));
		
		//------------------------- Add labels and text fields to formPanel
		formPanel.setLayout(new GridLayout(3,2));
		
		formPanel.add(areaNameL);
		formPanel.add(areaNameTF);
		formPanel.add(areaSpaceL);
		formPanel.add(areaSpaceTF);
		formPanel.add(areaPopulationL);
		formPanel.add(areaPopulationTF);
		
		area.add(formPanel);
		
		//------------------------- Add buttons to buttonPanel
		
		buttonPanel.add(areaAdd);
		buttonPanel.add(areaDel);
		buttonPanel.add(areaEdit);
		
		area.add(buttonPanel);
		
		//------------------------- Add event listeners to buttons
		
		areaAdd.addActionListener(new AddAction());
		areaDel.addActionListener(new DelAction());
		
		//------------------------- Down panel
		tablePanel.add(table);
		
		scroller.setPreferredSize(new Dimension( 450,160));
		table.setModel(DBHelper.getAllData());
		area.add(tablePanel);
		table.addMouseListener(new TableListener());
		
		
		tab.add(area,"Област");
		tab.add(reservoir,"Водоеми");
		tab.add(responsible,"Отговорник на водоем");
		this.add(tab);
		this.setVisible(true);
	}
	
	public void clearForm() {
		areaNameTF.setText("");
		areaSpaceTF.setText("");
		areaPopulationTF.setText("");
	}
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			System.out.print(id);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class DelAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "delete from area where id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				table.setModel(DBHelper.getAllData());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	class AddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "insert into area values(null,?,?,?)";
			try {
				state= conn.prepareStatement(sql);
				state.setString(1, areaNameTF.getText());
				state.setInt(2,Integer.parseInt(areaSpaceTF.getText()));
				state.setInt(3, Integer.parseInt(areaPopulationTF.getText()));
				state.execute();
				table.setModel(DBHelper.getAllData());
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
			System.out.println("text");	
			clearForm();
		}

		
	}	
}

