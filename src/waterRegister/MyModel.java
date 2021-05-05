package waterRegister;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;




public class MyModel extends AbstractTableModel {
	
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResultSet result;	
	private int rowCount;	
	private int columnCount;
	private ArrayList<Object> data=new ArrayList<Object>();
	 
	 public MyModel(ResultSet rs) throws Exception
	 {
		 setRS(rs);
	 }// end constructor
	 
	 public void setRS(ResultSet rs) throws Exception
	 {
		 this.result=rs;
		 ResultSetMetaData metaData=rs.getMetaData();
		 rowCount=0;
		 columnCount=metaData.getColumnCount();
		 
		 while(rs.next()){
			 Object[] row=new Object[columnCount];
			 for(int j=0;j<columnCount;j++){
			 row[j]=rs.getObject(j+1);
			 }			 
			 data.add(row);
			 rowCount++;
		}// while
	 }// end setRS - filling ArrayList with ResultSet values
	 
//	 @Override
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		// TODO Auto-generated method stub
//		 
//		return true;
//	}
//	 
//	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
////		if(columnIndex == 0) {
////			data.set(rowIndex, aValue);
////			fireTableCellUpdated(rowIndex, rowIndex);
////		}
//		 MyModel.set(rowIndex,aValue);
//		 fireTableCellUpdated(rowIndex, rowIndex);
//	 }
//	 
	 public int getColumnCount(){
		 return columnCount;
	 }
	 
	 public int getRowCount(){
		 return rowCount;
	 }
	 
	 public Object getValueAt(int rowIndex, int columnIndex){
		 Object[] row=(Object[]) data.get(rowIndex);
		 return row[columnIndex];
	 }
	 
	 public String getColumnName(int columnIndex){
		 try{
		 ResultSetMetaData metaData=result.getMetaData();
		 return metaData.getColumnName(columnIndex+1);
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }
	 }// end getColumnName


	 
	 
} // end class MyModel 