package Data;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.h2.util.DbDriverActivator;


public class RegionPanel extends JPanel{
	
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
	JLabel errorLabel = new JLabel();
	JLabel comboSearchL = new JLabel("Намери област:");
	
	//------------------------- Region Text Fields
	
	JTextField RegionNameTF = new JTextField();
	JTextField RegionSpaceTF = new JTextField(); 
	JTextField regionPopulationTF = new JTextField();
	JComboBox<String> searchCombo = new JComboBox<String>();
	
	//------------------------- Region Buttons
	
	JButton regionAdd = new JButton("Добави");
	JButton regionDel = new JButton("Изтрии");
	JButton regionEdit = new JButton("Редактирай");
	JButton regionSearch = new JButton("Търси");
	
	
	public RegionPanel(){
		
		//------------------------- Add labels and text fields to formPanel
		formPanel.setLayout(new GridLayout(5,2));
		
		formPanel.add(regionNameL);
		formPanel.add(RegionNameTF);
		formPanel.add(regionSpaceL);
		formPanel.add(RegionSpaceTF);
		formPanel.add(regionPopulationL);
		formPanel.add(regionPopulationTF);
		formPanel.add(errorLabel);
		formPanel.add(comboSearchL);
		formPanel.add(searchCombo);
		formPanel.add(regionSearch);
		
		this.add(formPanel);
		
		//------------------------- Add values to combobox
 
		RegionRepository.RefreshCombo(searchCombo);
		
		//------------------------- Add buttons to buttonPanel
		
		buttonPanel.add(regionAdd);
		buttonPanel.add(regionDel);
		buttonPanel.add(regionEdit);
		
		this.add(buttonPanel);
		
		//------------------------- Add event listeners to buttons
		
		regionAdd.addActionListener(new AddAction());
		regionDel.addActionListener(new DelAction());
		regionEdit.addActionListener(new EditAction());
		regionSearch.addActionListener(new SearchAction());
		
		//------------------------- Add table and scroller to down panel
		tablePanel.add(table);
		table.addMouseListener(new TableListener());
		table.setModel(DBHelper.refreshRegionTable());
		
		scroller.setPreferredSize(new Dimension( 450,160));
		this.add(tablePanel);
		
	}
	
	
	class AddAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			boolean condition = RegionNameTF.getText().equals("") || RegionSpaceTF.getText().equals("")||
							regionPopulationTF.getText().equals("");

			if (condition) {
				errorLabel.setText("Моля попълните формата! ");
			}else {
				RegionRepository.Save(RegionNameTF, RegionSpaceTF, regionPopulationTF, table);
				RegionRepository.RefreshCombo(searchCombo);
				Frame.clearForm(RegionNameTF, RegionSpaceTF, regionPopulationTF,null);	
				}
			}
		}
	
	class DelAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			RegionRepository.Delete(table);
			RegionRepository.RefreshCombo(searchCombo);
			Frame.clearForm(RegionNameTF, RegionSpaceTF, regionPopulationTF,null);
		}
		
	}
	
	class EditAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			RegionRepository.Edit(RegionNameTF, RegionSpaceTF, regionPopulationTF,table);
			RegionRepository.RefreshCombo(searchCombo);
			Frame.clearForm(RegionNameTF, RegionSpaceTF, regionPopulationTF,null);
		}
		
	}
	
	class SearchAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String comboValue = searchCombo.getSelectedItem().toString();
			if (comboValue == null || comboValue == "") {
				table.setModel(DBHelper.refreshRegionTable());
			}else {
				RegionRepository.Search(searchCombo,table);
				//table.setModel(DBHelper.getAllData(SEARCH_SQL));
			}
		}
	}

	class TableListener implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			table.getValueAt(row, 0);
			Frame.fillTextField(row,table, RegionNameTF, RegionSpaceTF, regionPopulationTF,null,null);
			
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
}	


	
	
	



