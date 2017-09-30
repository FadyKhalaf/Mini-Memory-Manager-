import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JPanel;

public class ProcessPanel extends JPanel{
	
	private HolesPanel holesPanel;
	private SubmetProcess submitProcess;
	
	public ProcessPanel() {
		holesPanel = new HolesPanel();
		submitProcess = new SubmetProcess();
		
		setSize(300, 800);
		setLayout(new BorderLayout());
		add(holesPanel, BorderLayout.NORTH);
		add(submitProcess, BorderLayout.WEST);
	}
	public void setHolesListener(HolesPanelListener listener) {
		holesPanel.setListener(listener);
	}
	public void setProcessListener(ProcessListener listener) {
		submitProcess.AddListener(listener);
	}
}
