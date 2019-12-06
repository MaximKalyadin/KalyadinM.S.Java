package labTP;

import java.awt.Color;
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
		wheel = null;
	}
	
	public void AddTruck(ITransport truck, IWheel wheel) {
		this.truck = truck;
		this.wheel = wheel;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		super.paint(g);
		if(truck != null && wheel != null) {
			truck.DrawTruck(g);
			wheel.SetPosition(truck.getPosX(), truck.getPosY());
			wheel.Draw(g, Wenum.wheel2);
		}else if(truck != null)
			truck.DrawTruck(g);
	}
}
