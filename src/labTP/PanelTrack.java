package labTP;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelTrack extends JPanel {
	public PanelTrack() {
	}
	private ITransport truck;
	private IWheel wheel;

	void setTruck(ITransport truck) {
		this.truck = truck;
	}
	
	void setWheel(IWheel wheel) {
		this.wheel = wheel;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (truck != null && wheel != null) {
			truck.DrawTruck(g);
			wheel.Draw(g, Wenum.wheel4);
		}
	}
}
