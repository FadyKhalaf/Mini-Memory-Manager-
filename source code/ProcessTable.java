import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProcessTable extends JPanel {
	
	private JTable processTable;
	private ProcessTableModel processModel = new ProcessTableModel();
	private JPopupMenu menu;
	private ProcessTableListener listener;
	
	public ProcessTable() {
		processTable = new JTable(processModel);
		menu = new JPopupMenu();
		JMenuItem removeProcess = new JMenuItem("De-allocate Process");
		menu.add(removeProcess);
		setLayout(new BorderLayout());
		add(new JScrollPane(processTable), BorderLayout.CENTER);
		processTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int index = processTable.rowAtPoint(e.getPoint());
				processTable.getSelectionModel().setSelectionInterval(index, index);
				if(e.getButton() == MouseEvent.BUTTON3)
					menu.show(processTable, e.getX(), e.getY());
			}
		});
		removeProcess.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int index = processTable.getSelectedRow();
				listener.RowDeleted(index);
				processModel.fireTableRowsDeleted(index, index);
			}
		});
		
	}
	public void setData(LinkedList<int[]> processes) {
		processModel.setData(processes);
	}
	public void Refresh() {
		processModel.fireTableDataChanged();
	}
	public void setProcessTableListener(ProcessTableListener listener) {
		this.listener = listener;
	}
}
