

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

import DataModel.DataBase;

public class GraphicalMemory extends JComponent {

	private DataBase dataBase;
	private int xPosition = this.getX();
	private int yPosition = this.getY();
	private int memorySize = -1;

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(xPosition + 100, yPosition + 40, 150, 400);
		float ratio = (float) 400 / memorySize;
		g.setFont(new Font("sanserif", Font.PLAIN, 10));
		for (int[] hole : dataBase.getHoles()) {
			g.drawRect(xPosition + 100, yPosition + 40 + (int) (ratio * hole[0]), 150,
					(int) (ratio * (hole[1] - hole[0])));
			g.drawString("Hole" + (dataBase.getHoles().indexOf(hole) + 1), xPosition + 255,
					 yPosition + 40 + (int) (ratio * hole[1]));
		}
		for (int[] process : dataBase.getProcessDuration()) {
			g.drawRect(xPosition + 100, yPosition + 40 + (int) (ratio * process[0]), 150,
					(int) (ratio * (process[1] - process[0])));
			g.drawString("p" + (dataBase.getProcessDuration().indexOf(process) + 1), xPosition + 150,
					yPosition + 40 + (int) (ratio * process[1]));
		}

	}

	public void setDataBase(DataBase db) {
		dataBase = db;
	}

	public void setMemorySize(int MemSize) {
		memorySize = MemSize;
	}

	public void Refresh() {
		repaint();
	}

}
