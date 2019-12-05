package labTP;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;

import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;

public class TransferColorDop extends TransferHandler{

private PanelTransfer panel;
	
	
	public boolean canImport(TransferSupport supp) {
		return supp.isDataFlavorSupported(DataFlavor.stringFlavor);
	}
	
	public void setPanel(PanelTransfer panel) {
		this.panel = panel;
	}
	
	public boolean importData(TransferSupport supp) {
		if(!supp.isDrop())
			return false;
		FullTruck truck = (FullTruck)panel.getTruck();
		String data;
		if(truck != null) {
			try {
				data = (String)supp.getTransferable().getTransferData(DataFlavor.stringFlavor);
				switch(data) {
					case "Желтый":
						truck.setDopColor(Color.yellow);
						break;
					case "Черный":
						truck.setDopColor(Color.black);
						break;
					case "Красный":
						truck.setDopColor(Color.red);
						break;
					case "Голубой":
						truck.setDopColor(Color.blue);
						break;
					case "Зеленый":
						truck.setDopColor(Color.green);
						break;
					case "Розовый":
						truck.setDopColor(Color.pink);
						break;
					case "Малиновый":
						truck.setDopColor(Color.magenta);
						break;
					case "Серый":
						truck.setDopColor(Color.GRAY);
						break;
				}
			}catch(Exception e) {
				return false;
			}
		}
		panel.repaint();
		return true;
	}
}
