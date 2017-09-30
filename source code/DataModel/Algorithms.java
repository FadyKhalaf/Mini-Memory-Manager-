package DataModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class Algorithms {
	public static boolean FirstFit(ArrayList<int[]> holes, LinkedList<int[]> processes, DataBase db) {
		boolean processAllocated = false;
		for(int[] process : processes) {
			processAllocated = false;
			if(process[1] == 1)
				continue;
			for(int[] hole : holes) {
				if((hole[1]-hole[0]) > process[0]) {
					int arrival = hole[0];
					hole[0] += process[0];
					int end = hole[0];
					int[] newProcess = {arrival, end};
					db.setProcess(newProcess);
					process[1] = 1;
					processAllocated = true;
					break;
				}
				else if((hole[1]-hole[0]) == process[0]) {
					int arrival = hole[0];
					hole[0] += process[0];
					int end = hole[0];
					int[] newProcess = {arrival, end};
					db.setProcess(newProcess);
					int index = holes.indexOf(hole);
					holes.remove(index);
					process[1] = 1;
					processAllocated = true;
					break;
				}
			}
			if(processAllocated == true)
				break;
		}
		if(processAllocated == false) {
			return false;
		}
		return true;
	}
	
	public static boolean BestFit(ArrayList<int[]> holes, LinkedList<int[]> processes, DataBase db) {
		boolean processAllocated = false;
		int BestIndex = -1;
		int lowestSpace = 0;
		boolean flag = true;
		for(int[] process : processes) {
			processAllocated = false;
			BestIndex = -1;
			lowestSpace = 0;
			flag = true;
			if(process[1] == 1)
				continue;
			for(int[] hole : holes) {
				if(flag && (hole[1]- hole[0] > process[0])) {
					BestIndex = holes.indexOf(hole);
					lowestSpace = hole[1]-hole[0];
					flag = false;
				}
				if((hole[1]-hole[0]) > process[0]) {
					if((hole[1]-hole[0] < lowestSpace) && !flag) {
						BestIndex = holes.indexOf(hole);
						lowestSpace = hole[1]-hole[0];
					}
				}
				else if((hole[1]-hole[0] == process[0])) {
					int arrival = hole[0];
					hole[0] += process[0];
					int end = hole[0];
					int[] newProcess = {arrival, end};
					db.setProcess(newProcess);
					int index = holes.indexOf(hole);
					holes.remove(index);
					process[1] = 1;
					processAllocated = true;
					break;
				}
			}
			if(processAllocated)
				break;
			if(BestIndex != -1 && lowestSpace != 0) {
				int[] bestHole = holes.get(BestIndex);
				int arrival = bestHole[0];
				bestHole[0] += process[0];
				int end = bestHole[0];
				int[] newProcess = {arrival,end};
				db.setProcess(newProcess);
				process[1] = 1;
				processAllocated = true;
				break;
			}
		}
		if(processAllocated == false)
			return false;
		return true;
	}
	public static boolean WorstFit(ArrayList<int[]> holes, LinkedList<int[]> processes, DataBase db) {
		boolean processAllocated = false;
		int BestIndex = -1;
		int lowestSpace = 0;
		boolean flag = true;
		for(int[] process : processes) {
			processAllocated = false;
			BestIndex = -1;
			lowestSpace = 0;
			flag = true;
			if(process[1] == 1)
				continue;
			for(int[] hole : holes) {
				if(flag && (hole[1]- hole[0] >= process[0])) {
					BestIndex = holes.indexOf(hole);
					lowestSpace = hole[1]-hole[0];
					flag = false;
				}
				else if((hole[1]-hole[0]) > process[0]) {
					if((hole[1]-hole[0] >= lowestSpace) && !flag) {
						BestIndex = holes.indexOf(hole);
						lowestSpace = hole[1]-hole[0];
					}
				}
			}
			if(BestIndex != -1 && lowestSpace != 0) {
				int[] bestHole = holes.get(BestIndex);
				int arrival = bestHole[0];
				bestHole[0] += process[0];
				int end = bestHole[0];
				int[] newProcess = {arrival,end};
				db.setProcess(newProcess);
				if(bestHole[0] == bestHole[1])
					holes.remove(bestHole);
				process[1] = 1;
				processAllocated = true;
				break;
			}
			if(processAllocated)
				break;
		}
		if(processAllocated == false)
			return false;
		return true;
	}
	
	public static void Deallocateprocess(int index, DataBase db){
		int[] process = db.getProcessDuration().get(index);
		db.getProcessDuration().remove(index);
		db.getProcesses().remove(index);
		
		if(db.getHoles().size() == 0) {
			db.addHole(process);
			return;
		}
		
		boolean HoleSet = false;
		for(int[] hole : db.getHoles()) {
			if(process[1] == hole[0]) {
				hole[0] -= process[1]-process[0];
				HoleSet = true;
				break;
			}
			else if(hole[1] == process[0]) {
				hole[1] += process[1] - process[0];
				HoleSet = true;
				break;
			}
			else if(hole[0] > process[0]) {
				int i = db.getHoles().indexOf(hole);
				db.addHole(i, process);
				HoleSet = true;
				break;
			}
		}
		if(!HoleSet && process[0] < db.getHoles().get(0)[0])
		{
			db.addHole(0,process);
		}
		if(!HoleSet && process[0] > db.getHoles().get(db.getHoles().size()-1)[0])
		{
			db.addHole(db.getHoles().size(),process);
		}
		Prepare(db.getHoles());
	}
	
	private static void Prepare(ArrayList<int[]> holes) {
		for(int i = 1; i < holes.size(); i++) {
			if(holes.get(i)[0] == holes.get(i-1)[1]) {
				holes.get(i-1)[1] += holes.get(i)[1]-holes.get(i)[0];
				holes.remove(i);
			}
		}
	}
}


