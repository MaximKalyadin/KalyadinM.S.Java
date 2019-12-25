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
	private static final int countPlaces = 5;
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
	public Parking<ITransport, IWheel> getParking(int index){

		if (index > -1 && index < parkingstages.size())
		{
			return parkingstages.get(index);
		}
		return null;
	}
	
	public boolean SaveData(String filename)
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
	
	public boolean SaveDataLvl(String filename, int ind)
    {
		if(ind <= -1 && ind >= parkingstages.size())
			return false;
		try(FileWriter writer = new FileWriter(filename)){
			writer.write("Level:" + ind + System.getProperty("line.separator"));
				Parking<ITransport, IWheel> current = parkingstages.get(ind);
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
		}catch(IOException e) {
			return false;
		}
		return true;
    }

    public boolean LoadData(String filename) throws ParkingOccupiedPlaceException, IOException
    {
    	String buff = "";
    	int count = -1;
        try (FileReader fr = new FileReader(filename))
        {
        	BufferedReader reader = new BufferedReader(fr);
            buff = reader.readLine();
            if (buff.split(":")[0].equals("CountLevels"))
            {
                int countLevel = Integer.parseInt(buff.split(":")[1]);
                if (parkingstages != null)
                	parkingstages.clear();
                parkingstages = new ArrayList<Parking<ITransport, IWheel>>(countLevel);
            }
            else
                return false;
            while (reader.ready())
            {
                buff = reader.readLine();
                ITransport truck = null;
                IWheel wheel = null;
                if (buff.equals("Level"))
                {
                    count++;
                    parkingstages.add(new Parking<ITransport, IWheel>(countPlaces, pictureWidth, pictureHeight));
                    continue;
                }else if (buff.split(":")[1].equals("BaseTruck"))
                {
                	truck = new BaseTruck(buff.split(":")[3]);
                    if(buff.split(":")[2].equals("ClassWheel"))
                    	wheel = new ClassWheel(Color.black);
                    else if (buff.split(":")[2].equals("OneDiskWheel"))
                    	wheel = new OneDiskWheel(Color.black);
                    else if (buff.split(":")[2].equals("TwoDiskWheel"))
                    	wheel = new TwoDiskWheel(Color.black);
                    parkingstages.get(count).addTruckall(truck, wheel, Integer.parseInt(buff.split(":")[0]));
                }else if (buff.split(":")[1].equals("FullTruck"))
                {
                	truck = new FullTruck(buff.split(":")[3]);
                    if(buff.split(":")[2].equals("ClassWheel"))
                    	wheel = new ClassWheel(Color.black);
                    else if (buff.split(":")[2].equals("OneDiskWheel"))
                    	wheel = new OneDiskWheel(Color.black);
                    else if (buff.split(":")[2].equals("TwoDiskWheel"))
                    	wheel = new TwoDiskWheel(Color.black);
                    parkingstages.get(count).addTruckall(truck, wheel, Integer.parseInt(buff.split(":")[0]));
                }
            }
            reader.close();
        }catch(ParkingOccupiedPlaceException | IOException ex) {
        	if (ex instanceof ParkingOccupiedPlaceException) {
				throw new ParkingOccupiedPlaceException(count);
			} else throw new IOException();
        }
        return true;
    }
    
	
	public boolean LoadDataLvl(String filename) throws IOException, ParkingOccupiedPlaceException
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
                    truck = new BaseTruck(buff.split(":")[3]);
                    if(buff.split(":")[2].equals("ClassWheel"))
                    	wheel = new ClassWheel(Color.black);
                    else if (buff.split(":")[2].equals("OneDiskWheel"))
                    	wheel = new OneDiskWheel(Color.black);
                    else if (buff.split(":")[2].equals("TwoDiskWheel"))
                    	wheel = new TwoDiskWheel(Color.black);
                    parkingstages.get(LevelNumber).addTruckall(truck, wheel, Integer.parseInt(buff.split(":")[0]));
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
        }catch(ParkingOccupiedPlaceException ex) {
        	throw ex;
        }catch (IOException ex) {
			throw ex;
		}
        return true;
    }
	public ITransport getITransport(int i, int j) {
		if(i > -1 && i < parkingstages.size()) {
			if(j > -1 && j < parkingstages.get(i).size) {
				ITransport truck = parkingstages.get(i).getTruck(j);
				try {
					parkingstages.get(i).SubTruck(j);
					return truck;
				} catch(ParkingNotFoundException ex) {
					throw new ParkingNotFoundException(j);
				}
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