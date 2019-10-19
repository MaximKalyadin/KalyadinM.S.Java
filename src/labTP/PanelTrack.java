package labTP;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelTrack extends JPanel {
	private ITransport truck;
	private IWheel wheel;
	public PanelTrack(ITransport truck, IWheel wheel) {
		this.truck = truck;
		this.wheel = wheel;
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
