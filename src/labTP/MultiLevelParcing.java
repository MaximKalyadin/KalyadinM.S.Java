package labTP;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class MultiLevelParcing {
	ArrayList<Parking<ITransport, IWheel>> parkingstages;
	private static final int countPlaces = 10;
	private int pictureWidth;
	private int pictureHeight;
	public MultiLevelParcing(int countStages, int pictureWidth, int pictureHeight)
	{
		parkingstages = new ArrayList<Parking<ITransport, IWheel>>();
		for (int i = 0; i < countStages; ++i)
		{
			parkingstages.add(new Parking<ITransport, IWheel>(countPlaces, pictureWidth, pictureHeight));
		}
		this.pictureHeight = pictureHeight;
		this.pictureWidth = pictureWidth;
	}
	public boolean SaveData(String filename, int index)
    {
		try(FileWriter writer = new FileWriter(filename)){
			writer.write("CountLevels:" + parkingstages.size() + System.getProperty("line.separator"));
			for(Parking<ITransport, IWheel> level : parkingstages)
			{
				writer.write("Level" + System.getProperty("line.separator"));
				for (int i = 0; i < countPlaces; i++)
				{
					ITransport truck = level.getTruck(i);
					IWheel wheel = level.getWheel(i);
					if (truck != null)
					{
						if (truck.toString() == "BaseTruck")
						{
							writer.write(i + ":BaseTruck:" + wheel.toString() + ":" + truck.getConfig() + System.getProperty("line.separator"));
						}
						if (truck.toString() == "FullTruck")
						{
							writer.write(i + ":FullTruck:" + wheel.toString() + ":" + truck.getConfig() + System.getProperty("line.separator"));
							
						}
					}
				}
			}
		}catch(IOException e) {
			return false;
		}
		return true;
    }
	
	public boolean SaveDataLvl(String filename, int index)
    {
		try(FileWriter writer = new FileWriter(filename)){
			writer.write("Level:" + index + System.getProperty("line.separator"));
			if(index > -1 && index < parkingstages.size()) {
				Parking<ITransport, IWheel> current = parkingstages.get(index);
				for(int i = 0; i < countPlaces; i++) {
					ITransport truck = current.getTruck(i);
					IWheel wheel = current.getWheel(i);
					if (truck != null)
					{
						if (truck.toString() == "BaseTruck")
						{
							writer.write(i + ":BaseTruck:" + wheel.toString() + ":" + truck.getConfig() + System.getProperty("line.separator"));
						}
						if (truck.toString() == "FullTruck")
						{
							writer.write(i + ":FullTruck:" + wheel.toString() + ":" + truck.getConfig() + System.getProperty("line.separator"));
						}
					}
				}
			}
		}catch(IOException e) {
			return false;
		}
		return true;
    }

    public boolean LoadData(String filename)
    {
        String buff = "";
        try (FileReader fr = new FileReader(filename))
        {
        	BufferedReader reader = new BufferedReader(fr);
            buff = reader.readLine();
            int LevelNumber;
            if (buff.split(":")[0].equals("Level"))
            {
            	LevelNumber = Integer.parseInt(buff.split(":")[1]);
            	parkingstages.set(LevelNumber, new Parking<ITransport, IWheel>(countPlaces, pictureWidth, pictureHeight));
            }
            else
                return false;
            while (reader.ready())
            {
                buff = reader.readLine();
                ITransport truck = null;
                IWheel wheel = null;
                if (buff.split(":")[1].equals("BaseTruck"))
                {
                    truck = new BaseTruck(buff.split(":")[2]);
                    if(buff.split(":")[2].equals("ClassWheel"))
                    	wheel = new ClassWheel(Color.black);
                    else if (buff.split(":")[2].equals("OneDiskWheel"))
                    	wheel = new OneDiskWheel(Color.black);
                    else if (buff.split(":")[2].equals("TwoDiskWheel"))
                    	wheel = new TwoDiskWheel(Color.black);
                    parkingstages.get(LevelNumber).addTruck(truck, Integer.parseInt(buff.split(":")[0]));
                }else if (buff.split(":")[1].equals("FullTruck"))
                {
                    truck = new FullTruck(buff.split(":")[3]);
                    if(buff.split(":")[2].equals("ClassWheel"))
                    	wheel = new ClassWheel(Color.black);
                    else if (buff.split(":")[2].equals("OneDiskWheel"))
                    	wheel = new OneDiskWheel(Color.black);
                    else if (buff.split(":")[2].equals("TwoDiskWheel"))
                    	wheel = new TwoDiskWheel(Color.black);
                    parkingstages.get(LevelNumber).addTruckall(truck, wheel, Integer.parseInt(buff.split(":")[0]));
                }
            }
        }catch(IOException e) {
        	return false;
        }
        return true;
    }
    
	public Parking<ITransport, IWheel> getParking(int index){

		if (index > -1 && index < parkingstages.size())
		{
			return parkingstages.get(index);
		}
		return null;
	}
	public boolean LoadDataLvl(String filename)
    {
        String buff = "";
        try (FileReader fr = new FileReader(filename))
        {
        	BufferedReader reader = new BufferedReader(fr);
            buff = reader.readLine();
            int LevelNumber;
            if (buff.split(":")[0].equals("Level"))
            {
            	LevelNumber = Integer.parseInt(buff.split(":")[1]);
            	parkingstages.set(LevelNumber, new Parking<ITransport, IWheel>(countPlaces, pictureWidth, pictureHeight));
            }
            else
                return false;
            while (reader.ready())
            {
                buff = reader.readLine();
                ITransport truck = null;
                IWheel wheel = null;
                if (buff.split(":")[1].equals("BaseTruck"))
                {
                    truck = new BaseTruck(buff.split(":")[2]);
                    if(buff.split(":")[2].equals("ClassWheel"))
                    	wheel = new ClassWheel(Color.black);
                    else if (buff.split(":")[2].equals("OneDiskWheel"))
                    	wheel = new OneDiskWheel(Color.black);
                    else if (buff.split(":")[2].equals("TwoDiskWheel"))
                    	wheel = new TwoDiskWheel(Color.black);
                    parkingstages.get(LevelNumber).addTruck(truck, Integer.parseInt(buff.split(":")[0]));
                }else if (buff.split(":")[1].equals("ContainerShipLuxe"))
                {
                    truck = new FullTruck(buff.split(":")[3]);
                    if(buff.split(":")[2].equals("ClassWheel"))
                    	wheel = new ClassWheel(Color.black);
                    else if (buff.split(":")[2].equals("OneDiskWheel"))
                    	wheel = new OneDiskWheel(Color.black);
                    else if (buff.split(":")[2].equals("TwoDiskWheel"))
                    	wheel = new TwoDiskWheel(Color.black);
                    parkingstages.get(LevelNumber).addTruckall(truck, wheel, Integer.parseInt(buff.split(":")[0]));
                }
            }
        }catch(IOException e) {
        	return false;
        }
        return true;
    }
	public ITransport getITransport(int i, int j) {
		if(i > -1 && i < parkingstages.size()) {
			if(j > -1 && j < parkingstages.get(i).size) {
				ITransport truck = parkingstages.get(i).getTruck(j);
				parkingstages.get(i).SubTruck(j);
				return truck;
			}
		}
		return null;
	}
	
	public IWheel getIWheel(int i, int j) {
		if(i > -1 && i < parkingstages.size()) {
			if(j > -1 && j < parkingstages.get(i).size) {
				IWheel wheel = parkingstages.get(i).getWheel(j);
				parkingstages.get(i).SubWheel(j);
				return wheel;
			}
		}
		return null;
	}
}