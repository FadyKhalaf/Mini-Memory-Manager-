import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

public class ProcessTableModel extends AbstractTableModel {
	
	private LinkedList<int[]> processes;
	private String[] names = {"Process ID", "Starting Adress", "Ending Adress"};

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return processes.size();
	}

	public Object getValueAt(int row, int col) {
		int[] process = processes.get(row);
		int index = processes.indexOf(process);
		switch(col) {
		case 0:
			return index;
		case 1:
			return process[0];
		case 2:
			return process[1];
		}
		return null;
	}
	
	public String getColumnName(int col) {
		return names[col];
	}

	public void setData(LinkedList<int[]> processes) {
		this.processes = processes;
	}

}
