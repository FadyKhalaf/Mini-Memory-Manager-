import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import DataModel.Algorithms;
import DataModel.DataBase;

public class MainFrame extends JFrame {
	
	private ProcessPanel processPanel;
	private HolesTable holesTable;
	private ProcessTable processTable;
	private JTabbedPane tabedPane;
	private GraphicalMemory graphicalMemory;
	private HolesPanelListener listener;
	private ProcessListener PListener;
	private ProcessTableListener PTlistener;
	private DataBase database;
	private String AllocationAlgorithm = "First Fit";
	
	public MainFrame(){
		super("Memory Management Mini project");
		setLayout(new BorderLayout());
		database = new DataBase();
		
		//components
		processPanel = new ProcessPanel();
		add(processPanel, BorderLayout.WEST);
		
		
		holesTable = new HolesTable();
		holesTable.setData(database.getHoles());
		processTable = new ProcessTable();
		processTable.setData(database.getProcessDuration());
		graphicalMemory = new GraphicalMemory();
		graphicalMemory.setDataBase(database);
		tabedPane = new JTabbedPane();
		tabedPane.add("Holes Table", holesTable);
		tabedPane.add("Allocation Table", processTable);
		tabedPane.add("Graphical Memory", graphicalMemory);
		add(tabedPane, BorderLayout.CENTER);
		
		PTlistener = new ProcessTableListener() {
			
			public void RowDeleted(int index) {
				Algorithms.Deallocateprocess(index, database);
				holesTable.Refresh();
				graphicalMemory.Refresh();
			}
		};
		processTable.setProcessTableListener(PTlistener);
		
		listener = new HolesPanelListener() {
			
			public void SubmetHoles(HolesEvent e) {
				int size;
				int holes;
				try{
						holes =Integer.parseInt(e.getHoles());
						size = Integer.parseInt(e.getMemSize());
						AllocationAlgorithm = e.getAllocationMethod();
					}catch(NumberFormatException ev){
						holes = 1;
						size = 1024;
						AllocationAlgorithm = "First Fit";
					}
				AllocationAlgorithm = e.getAllocationMethod();
				for(int i = 0; i < holes; i++) {
					int[] hole = {0,0};
					database.addHole(hole);
				}
				graphicalMemory.setMemorySize(size);
				graphicalMemory.Refresh();
				holesTable.Refresh();
			}
			
		};
		processPanel.setHolesListener(listener);
		

		PListener = new ProcessListener() {
			public void ProcessAdded(ProcessEvent e) {
				int size;
				try {
					size = Integer.parseInt(e.getPsize());
				}catch(NumberFormatException ev) {
					size = 1;
				}
				int[] process = {size, 0};
				database.addProcess(process);
				if(AllocationAlgorithm == "First Fit") {
					if(!Algorithms.FirstFit(database.getHoles(), database.getprocesses(), database))
						JOptionPane.showMessageDialog(MainFrame.this, "cannot allocate this process using first fit algorithm");
				}
				else if(AllocationAlgorithm == "Best Fit") {
					if(!Algorithms.BestFit(database.getHoles(), database.getprocesses(),database))
						JOptionPane.showMessageDialog(MainFrame.this, "cannot allocate this process using best fit algorithm");
				}
				else {
					if(!Algorithms.WorstFit(database.getHoles(), database.getprocesses(),database))
						JOptionPane.showMessageDialog(MainFrame.this, "cannot allocate this process using worst fit algorithm");
				}
				graphicalMemory.Refresh();
				holesTable.Refresh();
				processTable.Refresh();
			}
		};
		processPanel.setProcessListener(PListener);
		
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
