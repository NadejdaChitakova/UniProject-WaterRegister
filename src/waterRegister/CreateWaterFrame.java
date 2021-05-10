package waterRegister;

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

import waterRegister.CreateRegionFrame.TableListener;

public class CreateWaterFrame extends MainFrame {
	
	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);
	
	public static int id = -1;
	
	final String TABLE_AREA_NAME = "AREA";
	final String TABLE_WATER_NAME = "WATER";
	final String SQL = "SELECT WATER.WATER_ID,WATER.NAME,WATER.SPACE,WATER.DEPTH,AREA.AREA_NAME FROM WATER JOIN AREA ON "
			+ "WATER.AREA_ID = AREA.AREA_ID";
	
	//------------------------- Water panels
	JPanel formPanel = new JPanel();
	JPanel buttonsPanel = new JPanel();
	JPanel tablePanel = new JPanel();
	
	//------------------------- Water labels
	JLabel nameL = new JLabel("Име:");
	JLabel spaceL = new JLabel("Площ");
	JLabel depthL = new JLabel("Дълбочина");
	JLabel areaL = new JLabel("Област");
	
	//-------------------------  Text fields
	JTextField nameTF = new JTextField();
	JTextField spaceTF = new JTextField();
	JTextField depthTF = new JTextField();
	JComboBox<String> areaCombo = new JComboBox<String>();

	//-------------------------  Buttons
	JButton AddButton = new JButton("Добави");
	JButton DelButton = new JButton("Изтрии");
	JButton EditButton = new JButton("Редактирай");
	
	
//	public String getAreaCombo() {
//		return areaCombo.getSelectedItem().toString();
//	}
//	
	
	public CreateWaterFrame() {
		
		//-------------------------  Set text fields on formPanel
		formPanel.setLayout(new GridLayout(4,2));
		
		formPanel.add(nameL);
		formPanel.add(nameTF);
		formPanel.add(spaceL);
		formPanel.add(spaceTF);
		formPanel.add(depthL);
		formPanel.add(depthTF);
		formPanel.add(areaL);
		formPanel.add(areaCombo);
		
		reservoir.add(formPanel);
		
		//-------------------------  fill combo box
		ArrayList<String> areaValues = new ArrayList<String>(CrudWaterSource.getAll());
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addAll(areaValues);
		areaCombo.setModel(model);

		//-------------------------  add buttons on button panel
		buttonsPanel.add(AddButton);
		buttonsPanel.add(DelButton);
		buttonsPanel.add(EditButton);
		
		reservoir.add(buttonsPanel);
		//-------------------------  Buttons event listeners
		AddButton.addActionListener(new AddAction());
		DelButton.addActionListener(new DelAction());
		EditButton.addActionListener(new EditAction());
		//-------------------------  Table panel
		tablePanel.add(table);
		table.addMouseListener(new TableListener());
		table.setModel(DBHelper.getAllData(SQL));
		scroller.setPreferredSize(new Dimension( 450,160));
		
		reservoir.add(tablePanel);

	}

	class AddAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {		
			CrudWaterSource.insertData(nameTF, spaceTF, depthTF, areaCombo);
			System.out.println("SUCCESSFULLY ADD");	
			clearForm(nameTF, spaceTF, depthTF);
			table.setModel(DBHelper.getAllData(SQL));
			
		}

		
	}	
	
	class DelAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			DBHelper.deleteDataFromTable(TABLE_WATER_NAME);
			table.setModel(DBHelper.getAllData(SQL));
		}
		
		
	}
	class EditAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CrudWaterSource.editData(nameTF, spaceTF, depthTF, areaCombo);
			table.setModel(DBHelper.getAllData(SQL));
		}
		
	}
	
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			table.getValueAt(row, 0);

			fillTextField(row,table, nameTF, spaceTF, depthTF,null);

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
