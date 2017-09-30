import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class HolesPanel extends JPanel {
	
	private JButton SubmetHoles;
	private JTextField HolesNumber;
	private JTextField MemSize;
	private HolesPanelListener listener;
	private JComboBox<String> allocationMethod;
	
	public HolesPanel() {
		SubmetHoles = new JButton("Submet");
		allocationMethod = new JComboBox<>();
		allocationMethod.addItem("First Fit");
		allocationMethod.addItem("Best Fit");
		allocationMethod.addItem("Worst Fit");
		allocationMethod.setSelectedIndex(0);
		
		HolesNumber = new JTextField(10);
		HolesNumber.setText("1");
		MemSize = new JTextField(10);
		MemSize.setText("1024");
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(270, 250));
		Border innerBorder = BorderFactory.createTitledBorder("Holes Panel");
		Border outerBorder = BorderFactory.createEmptyBorder();
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		SubmetHoles.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String HolesNum = HolesNumber.getText();
				String size = MemSize.getText();
				String allocation = (String)allocationMethod.getSelectedItem();
				HolesEvent e = new HolesEvent(HolesPanel.this, HolesNum, size, allocation);
				listener.SubmetHoles(e);
			}
		});
		
		//layout part
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,10);
		add(new JLabel("Holes Number:"), gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx++;
		gc.insets = new Insets(0,0,0,0);
		add(HolesNumber, gc);
		//////////////new line//////////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,10);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Memory Size(MB):"), gc);
		
		gc.gridx++;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(MemSize, gc);
		//////////////new line//////////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,10);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Allocation Algorithm:"), gc);
		
		gc.gridx++;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(allocationMethod, gc);
		//////////////new line//////////////////
		gc.weightx = 10;
		gc.weighty = 10;
		
		gc.gridy++;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(SubmetHoles, gc);
	}
	
	public void setListener(HolesPanelListener listener) {
		this.listener = listener;
	}
}
