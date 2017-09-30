import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class HolesTableModel extends AbstractTableModel {
	
	private String names[] = {"Hole Number", "Starting Adress", "Ending Adress"};
	private ArrayList<int[]> holes;

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return holes.size();
	}

	public Object getValueAt(int row, int col) {
		int[] hole = holes.get(row);
		int index = holes.indexOf(hole);
		switch(col) {
		case 0:
			return index;
		case 1:
			return hole[0];
		case 2:
			return hole[1];
		}
		return null;
	}
	
	public boolean isCellEditable(int row, int col) {
		switch(col) {
		case 0:
			return false;
		default:
			return true;
		}
	}
	
	public void setValueAt(Object val, int row, int col) {
		if(holes.size() == 0) return;
		int[] hole = holes.get(row);
		switch(col) {
		case 0:
			return;
		case 1:
			hole[0] = Integer.parseInt((String)val);
			return;
		case 2:
			hole[1] = Integer.parseInt((String)val);;
			return;
		}
	}

	public String getColumnName(int col) {
		return names[col];
	}
	public void setData(ArrayList<int[]> holes) {
		this.holes = holes;
	}
}
