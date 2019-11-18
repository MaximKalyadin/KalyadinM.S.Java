package labTP;

import java.util.ArrayList;
import java.util.Stack;

public class MultiLevelParking {
	ArrayList<Parking<ITransport, IWheel>> parkingstages;
	private static final int countPlaces = 20;
	public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight)
	{
		parkingstages = new ArrayList<Parking<ITransport, IWheel>>();
		for (int i = 0; i < countStages; ++i)
		{
			parkingstages.add(new Parking<ITransport, IWheel>(countPlaces, pictureWidth, pictureHeight));
		}
	}
	
	public Parking<ITransport, IWheel> getParking(int index){

		if (index > -1 && index < parkingstages.size())
		{
			return parkingstages.get(index);
		}
		return null;
	}
	
	public ITransport getITransport(int i, int j) {
		if(i > -1 && i < parkingstages.size()) {
			if(j > -1 && j < parkingstages.get(i).size) {
				ITransport ship = parkingstages.get(i).getTruck(j);
				parkingstages.get(i).SubTruck(j);
				return ship;
			}
		}
		return null;
	}
	
	public IWheel getWheel(int i, int j) {
		if(i > -1 && i < parkingstages.size()) {
			if(j > -1 && j < parkingstages.get(i).size) {
				IWheel box = parkingstages.get(i).getWheel(j);
				parkingstages.get(i).SubWheel(j);
				return box;
			}
		}
		return null;
	}
}
