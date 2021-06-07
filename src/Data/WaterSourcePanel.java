package Data;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;

import org.h2.util.geometry.JTSUtils.GeometryTarget;


public class WaterSourcePanel extends JPanel {
	
	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);
	
	public static int id = -1;
	
	//------------------------- panels
	JPanel formPanel = new JPanel();
	JPanel buttonsPanel = new JPanel();
	JPanel tablePanel = new JPanel();
	
	//-------------------------  labels
	JLabel nameL = new JLabel("Име:");
	JLabel spaceL = new JLabel("Площ");
	JLabel depthL = new JLabel("Дълбочина");
	JLabel areaL = new JLabel("Област");
	JLabel errorMessage = new JLabel();
	JLabel searchL = new JLabel("Търси по име на регион");
	
	//-------------------------  Text fields
	JTextField nameTF = new JTextField();
	JTextField spaceTF = new JTextField();
	JTextField depthTF = new JTextField();
	JComboBox<String> comboRegion = new JComboBox<String>();
	JComboBox<String> comboNames = new JComboBox<String>();
	JComboBox<String> comboRegionSearch = new JComboBox<String>();

	//-------------------------  Buttons
	JButton AddButton = new JButton("Добави");
	JButton DelButton = new JButton("Изтрии");
	JButton EditButton = new JButton("Редактирай");
	JButton SearchButton = new JButton("Търси");
	
	public WaterSourcePanel() {
		
		//-------------------------  Set text fields on formPanel
		formPanel.setLayout(new GridLayout(7,2));
		
		formPanel.add(nameL);
		formPanel.add(nameTF);
		formPanel.add(spaceL);
		formPanel.add(spaceTF);
		formPanel.add(depthL);
		formPanel.add(depthTF);
		formPanel.add(areaL);
		formPanel.add(comboRegion);
		formPanel.add(errorMessage);
		formPanel.add(searchL);
		formPanel.add(comboNames);
		formPanel.add(comboRegionSearch);
		formPanel.add(SearchButton);
		this.add(formPanel);
		

		//-------------------------  add buttons on button panel
		buttonsPanel.add(AddButton);
		buttonsPanel.add(DelButton);
		buttonsPanel.add(EditButton);
		
		this.add(buttonsPanel);
		//-------------------------  Table panel
		tablePanel.add(table);
		table.addMouseListener(new TableListener());
		table.setModel(DBHelper.refreshWaterSourceTable());
		scroller.setPreferredSize(new Dimension( 450,160));
		
		//-------------------------  Buttons event listeners
		
		AddButton.addActionListener(new AddAction());
		DelButton.addActionListener(new DelAction());
		EditButton.addActionListener(new EditAction());
		SearchButton.addActionListener(new SearchAction());
		
		//-------------------------  fill combo box
		
		RegionRepository.RefreshCombo(comboRegion);
		RegionRepository.RefreshCombo(comboRegionSearch);
		WaterSourceRepository.RefreshWSCombo(comboNames);


		this.add(tablePanel);

	}

	class AddAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			boolean condition = nameTF.getText().equals("") ||spaceTF.getText().equals("")||
								depthTF.getText().equals("")||comboRegion.getSelectedItem().toString().equals("");
			if (condition) {
				errorMessage.setText("Моля попълнете всички полета!");
			}else {
				
				WaterSourceRepository.Save(nameTF, spaceTF, depthTF, comboRegion, table);
				WaterSourceRepository.RefreshWSCombo(comboNames);
				Frame.clearForm(nameTF, spaceTF, depthTF,null);
			}
		}
	}	
	
	class DelAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			WaterSourceRepository.Delete(table);
			WaterSourceRepository.RefreshWSCombo(comboNames);
		}

	}
	class EditAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			WaterSourceRepository.Edit(nameTF, spaceTF, depthTF, comboRegion,table);
			WaterSourceRepository.RefreshWSCombo(comboNames);

		}
		
	}
	class SearchAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String comboValue = comboNames.getSelectedItem().toString();


			if (comboValue == null || comboValue == "") {
				table.setModel(DBHelper.refreshWaterSourceTable());
			}else {
				WaterSourceRepository.Search(comboNames, comboRegionSearch, table);
				

			}
			
		}
		
	}
	
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			table.getValueAt(row, 0);

			Frame.fillTextField(row,table, nameTF, spaceTF, depthTF,null,null);

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
