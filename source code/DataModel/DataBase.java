package DataModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class DataBase {
	private LinkedList<int[]> processDuration = new LinkedList<>();  //conatins starts and ends of each process
	private ArrayList<int[]> holes = new ArrayList<>();
	private LinkedList<int[]> processes = new LinkedList<>();  //only process duration + 1 integer to represent if it's departed
	public LinkedList<int[]> getprocesses() {
		return processes;
	}
	public void setprocesses(LinkedList<int[]> processes) {
		this.processes = processes;
	}
	public ArrayList<int[]> getHoles() {
		return holes;
	}
	public void setHoles(ArrayList<int[]> holes) {
		this.holes = holes;
	}
	public void addProcess(int[] size) {
		processes.add(size);
	}
	public void addHole(int[] hole) {
		holes.add(hole);
	}
	public void addHole(int index, int[] hole) {
		holes.add(index, hole);
	}
	public LinkedList<int[]> getProcessDuration() {
		return processDuration;
	}
	public void setProcessDuration(LinkedList<int[]> processDuration) {
		this.processDuration = processDuration;
	}
	public LinkedList<int[]> getProcesses() {
		return processes;
	}
	public void setProcesses(LinkedList<int[]> processes) {
		this.processes = processes;
	}
	public void setProcess(int[] duration) {
		processDuration.add(duration);
	}
	public void DeallocateProcess(int index) {
		processDuration.remove(index);
	}
}
