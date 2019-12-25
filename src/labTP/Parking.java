package labTP;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class Parking <T extends Object&ITransport, G extends Object&IWheel> {
	
	private HashMap<Integer, T> _places;
	private HashMap<Integer, G> _placesWithWheel;
    private int PictureWidth;
    private int PictureHeight;
    private final static int _placeSizeWidth = 210;
    private final static int _placeSizeHeight = 80;
    int size;
    public Parking(int sizes, int pictureWidth, int pictureHeight)
    {
    	_places = new HashMap<Integer, T>(sizes);
    	_placesWithWheel = new HashMap<Integer, G>(sizes);
    	this.size = sizes;
        PictureWidth = pictureWidth;
        PictureHeight = pictureHeight;
    }

    public int addTruck(T truck)
    {
    	for (int i = 0; i < size; i++)
        {
            if (!CheckFreePlace(i))
            {
                _places.put(i, truck);
                _places.get(i).SetPosition(i / 5 * _placeSizeWidth,  i % 5 * _placeSizeHeight + 10, PictureWidth,
                        PictureHeight);
                return i;
            }
        }
        return -1;
    }
    public int addTruckall(T truck, G wheel) {
    	for (int i = 0; i < size; i++)
        {
            if (!CheckFreePlace(i))
            {
                _places.put(i, truck);
                _places.get(i).SetPosition(i / 5 * _placeSizeWidth,  i % 5 * _placeSizeHeight + 10, PictureWidth, PictureHeight);
                _placesWithWheel.put(i, wheel);
                _placesWithWheel.get(i).SetPosition(_places.get(i).getPosX(), _places.get(i).getPosY());
                return i;
            }
        }
        return -1;
    }
    public int addTruck(T truck, int index)
    {
            if (!CheckFreePlace(index))
            {
                _places.put(index, truck);
                _places.get(index).SetPosition(index / 5 * _placeSizeWidth,  index % 5 * _placeSizeHeight + 10, PictureWidth,
                        PictureHeight);
                return index;
            }
        return -1;
    }
    public int addTruckall(T truck, G wheel, int index) {
            if (!CheckFreePlace(index))
            {
                _places.put(index, truck);
                _places.get(index).SetPosition(index / 5 * _placeSizeWidth,  index % 5 * _placeSizeHeight + 10, PictureWidth, PictureHeight);
                _placesWithWheel.put(index, wheel);
                _placesWithWheel.get(index).SetPosition(_places.get(index).getPosX(), _places.get(index).getPosY());
                return index;
            }
        return -1;
    }
    public T getTruck(int index) {
    	return _places.get(index);
    }
    
    public G getWheel(int index) {
    	return _placesWithWheel.get(index);
    }
    
    public T SubTruck(int index)
    {
        if (CheckFreePlace(index))
        {
            T truck = _places.get(index);
            _places.remove(index);
            return truck;
        }
        return null;
    }
    
    public G SubWheel(int index) {
        if (_placesWithWheel.containsKey(index))
        {
            G wheel = _placesWithWheel.get(index);
            _placesWithWheel.remove(index);
            return wheel;
        }
        return null;
    }
    
    private boolean CheckFreePlace(int index)
    {
    	return _places.containsKey(index);
    }

    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < size; i++)
        {
            if (CheckFreePlace(i))
            {
                _places.get(i).DrawTruck(g);
                if(_placesWithWheel.containsKey(i))
                	_placesWithWheel.get(i).Draw(g, Wenum.wheel2);
            }
        }
    }

    private void DrawMarking(Graphics g)
    {
    	g.setColor(Color.BLACK);
        g.drawRect(0, 0, (size / 5) * _placeSizeWidth, 480);
        for (int i = 0; i < size / 5; i++)
        {
            for (int j = 0; j < 6; ++j)
            {
                g.drawLine(i * _placeSizeWidth  , j * _placeSizeHeight, i * _placeSizeWidth + 170, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
        }
    }
}