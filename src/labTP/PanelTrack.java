package labTP;

import java.awt.Graphics;
import javax.swing.JPanel;

import javax.swing.JPanel;

public class PanelTrack extends JPanel {
	private ClassTruck truck;
	private ClassWheel wheel;
	public PanelTrack(ClassTruck truck, ClassWheel wheel) {
		this.truck = truck;
		this.wheel = wheel;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		truck.DrawTruck(g);
		wheel.Draw(g);
	}
}
