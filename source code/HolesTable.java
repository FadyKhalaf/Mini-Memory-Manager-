import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class HolesTable extends JPanel {
	
	private JTable table;
	private HolesTableModel tableModel;
	private ArrayList<int[]> holes;
	
	public HolesTable() {
		tableModel = new HolesTableModel();
		table = new JTable(tableModel);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(ArrayList<int[]> holes) {
		tableModel.setData(holes);
	}
	public void Refresh() {
		tableModel.fireTableDataChanged();
	}
}
