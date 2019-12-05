package labTP;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class TransferHandlerClass extends TransferHandler {
	
	private ITransport truck;
	private IWheel wheel;
	
	public boolean canImport(TransferSupport supp) {
		return supp.isDataFlavorSupported(DataFlavor.stringFlavor);
	}
	
	public ITransport getShip() {
		return truck;
	}
	
	public boolean importData(TransferSupport supp) {
		if(!supp.isDrop())
			return false;
		PanelTransfer paneltruck = (PanelTransfer)supp.getComponent();
		String data;
		try {
			data = (String)supp.getTransferable().getTransferData(DataFlavor.stringFlavor);
			switch(data) {
				case "Обычные":
						paneltruck.removeWheel();
						paneltruck.AddWheel(new OneDiskWheel(Color.yellow));
					
					break;
				case "Круглые":
						paneltruck.removeWheel();
						paneltruck.AddWheel(new TwoDiskWheel(Color.green));
					break;
				case "Квадратные":
						paneltruck.removeWheel();
						paneltruck.AddWheel(new ClassWheel(Color.black));
					break;
				case "Обычный":
					paneltruck.removeAll();
					truck = new BaseTruck(10, Color.green, 20);
					truck.SetPosition(paneltruck.getX() - 90, paneltruck.getY() + 40, paneltruck.getWidth(), paneltruck.getHeight());
					paneltruck.AddTruck(truck);
					break;
				case "Full":
					paneltruck.removeAll();
					truck = new FullTruck(10, Color.green, 20,Color.yellow, true, true, true);
					truck.SetPosition(paneltruck.getX() - 110, paneltruck.getY() + 40, paneltruck.getWidth(), paneltruck.getHeight());
					paneltruck.AddTruck(truck);
					break;
			}
		}catch(Exception e) {
			return false;
		}
		paneltruck.repaint();
		return true;
	}
}