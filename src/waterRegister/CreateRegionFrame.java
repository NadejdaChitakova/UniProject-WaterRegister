package waterRegister;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CreateRegionFrame extends MainFrame{
	
	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);
	
	public static  int id  = -1;

	//------------------------- Region panels
	
	JPanel formPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel tablePanel = new JPanel();

	//------------------------- Region Labels
	
	JLabel regionNameL = new JLabel("име:");
	JLabel regionSpaceL = new JLabel("площ:");
	JLabel regionPopulationL = new JLabel("население:");
	
	//------------------------- Region Text Fields
	
	JTextField RegionNameTF = new JTextField();
	JTextField RegionSpaceTF = new JTextField(); 
	JTextField regionPopulationTF = new JTextField();
	
	//------------------------- Region Buttons
	
	JButton regionAdd = new JButton("Добави");
	JButton regionDel = new JButton("Изтрии");
	JButton regionEdit = new JButton("Редактирай");
	
	
	public CreateRegionFrame(){
		
		//------------------------- Add labels and text fields to formPanel
		formPanel.setLayout(new GridLayout(3,2));
		
		formPanel.add(regionNameL);
		formPanel.add(RegionNameTF);
		formPanel.add(regionSpaceL);
		formPanel.add(RegionSpaceTF);
		formPanel.add(regionPopulationL);
		formPanel.add(regionPopulationTF);
		
		region.add(formPanel);
		
		//------------------------- Add buttons to buttonPanel
		
		buttonPanel.add(regionAdd);
		buttonPanel.add(regionDel);
		buttonPanel.add(regionEdit);
		
		region.add(buttonPanel);
		
		//------------------------- Add event listeners to buttons
		
		regionAdd.addActionListener(new AddAction());
		regionDel.addActionListener(new DelAction());
		regionEdit.addActionListener(new EditAction());
		
		//------------------------- Add table and scroller to down panel
		tablePanel.add(table);
		table.addMouseListener(new TableListener());
		table.setModel(DBHelper.getAllData("AREA"));
		
		scroller.setPreferredSize(new Dimension( 450,160));
		region.add(tablePanel);
		
	}
	
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			table.getValueAt(row, 0);

			fillTextField(row,table, RegionNameTF, RegionSpaceTF, regionPopulationTF,null);

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
	
	
	class AddAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {		
			CrudRegion.insertIntoArea(RegionNameTF, RegionSpaceTF, regionPopulationTF);
			table.setModel(DBHelper.getAllData("AREA"));
			System.out.println("add");	
			clearForm(RegionNameTF, RegionSpaceTF, regionPopulationTF);
		}

		
	}	
	
	class DelAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			DBHelper.deleteDataFromTable("area");
			table.setModel(DBHelper.getAllData("AREA"));
		}
		
	}
	
	class EditAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CrudRegion.editDataRegion(RegionNameTF, RegionSpaceTF, regionPopulationTF);
			table.setModel(DBHelper.getAllData("AREA"));
		}
		
	}
}
	
	
	


