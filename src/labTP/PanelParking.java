package labTP;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelParking extends JPanel {
	private Parking<ITransport, IWheel> parking;
		
		public PanelParking(Parking<ITransport, IWheel> parking) {
			this.parking = parking;
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			parking.Draw(g);
		}
}
