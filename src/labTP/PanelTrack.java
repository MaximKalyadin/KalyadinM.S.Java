package labTP;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelTrack extends JPanel {
	private ITransport truck;
	private IWheel wheel;
	
	public void addTruck(ITransport truck) {
		this.truck = truck;
	}
	
	public void RemoveTruck() {
		truck = null;
	}
	
	void setTruck(ITransport truck) {
		this.truck = truck;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (truck != null) {
			truck.DrawTruck(g);
		}
	}
}
