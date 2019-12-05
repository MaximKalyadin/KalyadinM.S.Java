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
						truck.SetBaseColor(Color.yellow);
						break;
					case "Черный":
						truck.SetBaseColor(Color.black);
						break;
					case "Красный":
						truck.SetBaseColor(Color.red);
						break;
					case "Голубой":
						truck.SetBaseColor(Color.blue);
						break;
					case "Зеленый":
						truck.SetBaseColor(Color.green);
						break;
					case "Розовый":
						truck.SetBaseColor(Color.pink);
						break;
					case "Малиновый":
						truck.SetBaseColor(Color.magenta);
						break;
					case "Серый":
						truck.SetBaseColor(Color.GRAY);
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
