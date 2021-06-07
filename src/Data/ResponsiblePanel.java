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


public class ResponsiblePanel extends JPanel {
	
	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);
	
	public static int id = -1;

	//------------------------- panels

	JPanel formPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel tablePanel = new JPanel();

	//------------------------- labels
	JLabel firstName = new JLabel("Име:");
	JLabel lastName = new JLabel("Фамилия:");
	JLabel age = new JLabel("Възраст:");
	JLabel comment = new JLabel("Коментар:");
	JLabel comboWaterSourceL = new JLabel("Водоем");
	JLabel searchL = new JLabel("Търси по водоем");
	JLabel errorMessage = new JLabel();

	
	//------------------------- text fields
	JTextField firstNameTF = new JTextField();
	JTextField lastNameTF = new JTextField();
	JTextField ageTF = new JTextField();
	JTextField commentTF = new JTextField();
	JComboBox<String> comboWaterSource = new JComboBox<String>();

	
	//------------------------- buttons
	JButton addButton = new JButton("Добави");
	JButton delButton = new JButton("Изтрии");
	JButton editButton = new JButton("Редактирай");
	JButton searchButton = new JButton("");
	
	
	public ResponsiblePanel(){
		formPanel.setLayout(new GridLayout(3,2));
		//------------------------- add labels and text fields to form panel
		formPanel.add(firstName);
		formPanel.add(firstNameTF);
		formPanel.add(lastName);
		formPanel.add(lastNameTF);
		formPanel.add(age);
		formPanel.add(ageTF);
		formPanel.add(comment);
		formPanel.add(commentTF);
		formPanel.add(comboWaterSourceL);
		formPanel.add(comboWaterSource);
		formPanel.add(errorMessage);
		
		this.add(formPanel);
		
		//------------------------- buttons
		buttonPanel.add(addButton);
		buttonPanel.add(delButton);
		buttonPanel.add(editButton);
		
		this.add(buttonPanel);
		
		ArrayList<String> waterValues = new ArrayList<String>(ResponsibleRepository.getAll());
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addAll(waterValues);
		comboWaterSource.setModel(model);
		//------------------------- add table to panel
		tablePanel.add(table);
		table.addMouseListener(new TableListener());
		table.setModel(DBHelper.refreshResponsibleTable());
		scroller.setPreferredSize(new Dimension( 450,160));

		//------------------------- add event listeners to buttons
		addButton.addActionListener(new AddAction());
		delButton.addActionListener(new DelAction());
		editButton.addActionListener(new EditAction());
		this.add(tablePanel);
	}
	
	class AddAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			boolean condition = firstNameTF.getText().equals("") || lastNameTF.getText().equals("") || 
					age.getText().equals("") || comment.getText().equals("") || comboWaterSource.getSelectedItem().equals("");						
			if (condition) {
				errorMessage.setText("Моля попълнете всички полета!");
			}else {
				ResponsibleRepository.Save(firstNameTF, lastNameTF, ageTF,commentTF,comboWaterSource,table);
				Frame.clearForm(firstNameTF,lastNameTF,ageTF,commentTF);
			}
		}	
	}	
	
	class DelAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ResponsibleRepository.Delete(table);
		}
		
	}
	
	class EditAction implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ResponsibleRepository.Edit(firstNameTF, lastNameTF, ageTF, commentTF, comboWaterSource, table);
			Frame.clearForm(firstNameTF,lastNameTF,ageTF,commentTF);
		}
		
	}	
		class SearchAction implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String comboValue = comboWaterSource.getSelectedItem().toString();
				if (comboValue == null || comboValue == "") {
					//table.setModel(DBHelper.getAllData(SQL));
				}else {
					//RepositoryRegion.getRegionByName(comboWaterSource);
					String SEARCH_SQL = "SELECT * FROM WATER WHERE NAME='"+comboValue+"'";
					table.setModel(DBHelper.getAllData(SEARCH_SQL));
				}
				
			}
			
		}
	
	
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			System.out.println(row + "row");
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			table.getValueAt(row, 0);
			Frame.fillTextField(row,table, firstNameTF, lastNameTF, ageTF,commentTF,null);

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
