package labTP;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelTransfer extends JPanel {
	
	private ITransport truck;
	private IWheel wheel;
	
	public void AddTruck(ITransport truck) {
		this.truck = truck;
	}
	
	public void AddWheel(IWheel wheel) {
		this.wheel = wheel;
	}
	
	public void removeAll() {
		truck = null;
		wheel = null;
	}
	
	public void removeWheel() {
		wheel = null;
	}
	
	public ITransport getTruck() {
		return truck;
	}
	
	public IWheel getWheel() {
		return wheel;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(wheel != null && truck != null) {
			truck.DrawTruck(g);
			wheel.SetPosition(truck.getPosX(), truck.getPosY());
			wheel.Draw(g, Wenum.wheel2);
		}else if(truck != null)
			truck.DrawTruck(g);	
	}
}