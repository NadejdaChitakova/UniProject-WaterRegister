package waterRegister;


import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;




public class CreateWaterFrame extends MainFrame {
	
	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);
	
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
		
		//-------------------------  Set text fields on formPanel
		buttonsPanel.add(AddButton);
		buttonsPanel.add(DelButton);
		buttonsPanel.add(EditButton);
		
		reservoir.add(buttonsPanel);
		
		//-------------------------  Table panel
		
	}
}
