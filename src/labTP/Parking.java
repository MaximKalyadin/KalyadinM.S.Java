package labTP;
import java.awt.Color;
import java.awt.Graphics;

public class Parking <T extends Object&ITransport> {
	
	private T[] _places;
    private int PictureWidth;
    private int PictureHeight;
    private final static int _placeSizeWidth = 210;
    private final static int _placeSizeHeight = 80;
    
    public Parking(int sizes, int pictureWidth, int pictureHeight)
    {
        _places = (T[]) new Object[sizes];
        PictureWidth = pictureWidth;
        PictureHeight = pictureHeight;
        for (int i = 0; i < _places.length; i++)
        {
            _places[i] = null;
        }
    }

    public int addTruck(T truck)
    {
    	if(notequally(truck)) {
    		for(int i = 0; i<_places.length; i++) {
    			if(CheckFreePlace(i)) {
    				_places[i] = truck;
    				_places[i].SetPosition(i / 5 * _placeSizeWidth - 10,  i % 5 * _placeSizeHeight + 10, PictureWidth,
    						PictureHeight);
    				return i;
    			}
    		}
    	}
    	return -1;
    }
    
    public T SubTruck(int index)
    {
        if (index < 0 || index > _places.length)
        {
            return null;
        }
        if (!CheckFreePlace(index))
        {
            T truck = _places[index];
            _places[index] = null;
            return truck;
        }
        return null;
    }
    
    
    public int getindex(T truck) {
    	System.out.println(truck);
    	if (equally(truck)) {
    		T equalltruck = null;
    		for (int i = 0; i < _places.length; i++) {
    			equalltruck = _places[i];
    			if(equalltruck != null) {
    				if(truck.getBaseColor().toString().equals(equalltruck.getBaseColor().toString()) && truck.getDopColor() == null && equalltruck.getDopColor() == null)
    					return i;
    				if(equalltruck.getDopColor() != null)
    					if (truck.getBaseColor().toString().equals(equalltruck.getBaseColor().toString()) && truck.getDopColor().toString().equals(equalltruck.getDopColor().toString()))
    						return i;
    			}
    		}
    	}
    	return -1;
    }
    
    public boolean equally(T truck) {
    	T equalltruck = null;
    	for (int i = 0; i < _places.length; i++) {
    		equalltruck = _places[i];
    		if(equalltruck != null) {
    			if(truck.getBaseColor().toString().equals(equalltruck.getBaseColor().toString()) && truck.getDopColor() == null && equalltruck.getDopColor() == null)
    				return true;
    			if(equalltruck.getDopColor() != null)
    				if (truck.getBaseColor().toString().equals(equalltruck.getBaseColor().toString()) && truck.getDopColor().toString().equals(equalltruck.getDopColor().toString()))
    					return true;
    		}
    	}
    	return false;
    }
    
    public boolean notequally(T truck) {
    	T equalltruck = null;
    	for (int i = 0; i < _places.length; i++) {
    		equalltruck = _places[i];
    		if(equalltruck != null) {
    			if(truck.getBaseColor().toString().equals(equalltruck.getBaseColor().toString()) && truck.getDopColor() == null && equalltruck.getDopColor() == null)
    				return false;
    			if(equalltruck.getDopColor() != null)
    				if (truck.getBaseColor().toString().equals(equalltruck.getBaseColor().toString()) && truck.getDopColor().toString().equals(equalltruck.getDopColor().toString()))
    					return false;
    		}
    	}
    	return true;
    }
    private boolean CheckFreePlace(int index)
    {
        return _places[index] == null;
    }

    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < _places.length; i++)
        {
            if (!CheckFreePlace(i))
            {
                _places[i].DrawTruck(g);
            }
        }
    }

    private void DrawMarking(Graphics g)
    {
    	g.setColor(Color.BLACK);
        g.drawRect(0, 0, (_places.length / 5) * _placeSizeWidth, 480);
        for (int i = 0; i < _places.length / 5; i++)
        {
            for (int j = 0; j < 6; ++j)
            {
                g.drawLine(i * _placeSizeWidth  , j * _placeSizeHeight, i * _placeSizeWidth + 140, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
        }
    }
}