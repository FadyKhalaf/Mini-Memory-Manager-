import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SubmetProcess extends JPanel {
	
	private JButton Submet;
	private JTextField Process_Size;
	private ProcessListener listener;

	
	public SubmetProcess() {
		Submet = new JButton("ADD");
		Process_Size = new JTextField(10);
		Process_Size.setText("1");
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(270, 300));
		Border innerBorder = BorderFactory.createTitledBorder("Add Process");
		Border outerBorder = BorderFactory.createEmptyBorder();
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		Submet.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String size = Process_Size.getText();
				ProcessEvent e = new ProcessEvent(SubmetProcess.this, size);
				listener.ProcessAdded(e);
			}
		});
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,10);
		add(new JLabel("Process Size(MB):"), gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx++;
		gc.insets = new Insets(0,0,0,0);
		add(Process_Size, gc);
		//////////////new line//////////////////
		gc.weightx = 10;
		gc.weighty = 10;
		
		gc.gridy++;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(Submet, gc);
		}
	public void AddListener(ProcessListener listener) {
		this.listener = listener;
	}
}
