package labTP;

import java.awt.Color;
import java.awt.Graphics;

public class ClassTruck {

	static boolean Life;
	static int _startPosX;
    static int _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    private int truckWidth = 100;
    private int truckHeight = 60;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color DopColor;
    public boolean Vat;
    public boolean Stairs;
    public boolean Strip;
    
    public ClassTruck(boolean life, int maxspeed, int weight, Color maincolor, Color dopcolor, boolean vat, boolean stairs, boolean strip) { 
    	Life = life;
    	MaxSpeed = maxspeed;
    	Weight = weight;
    	MainColor = maincolor;
    	DopColor = dopcolor;
    	Vat = vat;
    	Stairs = stairs;
    	Strip = strip;
    }
    public void SetPosition(int x, int y, int width, int height){
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }
    public void MoveTransport(Tenum direction) {
    	float step =  MaxSpeed * 100 / Weight;
        switch (direction)
        {
            case Right:
                if (_startPosX + step < _pictureWidth - truckWidth - 50)
                {
                    _startPosX += step;
                }
                break;
            case Left:
                if (_startPosX - step > -30)
            {
                    _startPosX -= step;
                }
                break;
            case Up:
                if (_startPosY - step > 0)
                {
                    _startPosY -= step;
                }
                break;
            case Down:
                if (_startPosY + step < _pictureHeight - truckHeight - 60)
                {
                    _startPosY += step;
                }
                break;
        }
    }
    public  void DrawTruck(Graphics g) {
    	if (Life) {
	    	//кабина
	    	g.setColor(Color.BLACK);
	    	g.drawRect(_startPosX + 100, _startPosY - 6, 50, 55);
	    	g.setColor(MainColor);
	        g.fillRect(_startPosX + 100, _startPosY - 5, 50, 55);
	        g.setColor(Color.white);
	        g.drawRect(_startPosX + 125, _startPosY + 3, 21, 14);
	        g.fillRect(_startPosX + 125, _startPosY + 3, 21, 14);
	        //дверь
	        g.setColor(Color.BLACK);
	        g.drawRect(_startPosX + 104, _startPosY +3, 12, 25);
	        //фары
	        g.drawRect(_startPosX + 143, _startPosY +35, 7, 7);
	        g.setColor(Color.yellow);
	        g.fillRect(_startPosX + 143, _startPosY + 35, 7, 7);
	        //платформа 
	    	g.setColor(Color.BLACK);
	        g.fillRect(_startPosX + 15, _startPosY + 34, 85, 10);
	        if (Vat)
	        {
	            //крышка 
	        	g.setColor(Color.BLACK);
	            g.drawOval(_startPosX + 40, _startPosY - 6, 20, 10);
	            g.fillOval(_startPosX + 40, _startPosY - 6, 20, 10);
	            //сама цистерна
	            g.drawRect(_startPosX + 16, _startPosY , 80, 31);
	
	            g.setColor(DopColor);
	            g.fillRect(_startPosX + 16, _startPosY, 80, 31);
	        }
	        if (Stairs)
	        {
	            //лестница
	        	g.setColor(Color.BLACK);
	            g.fillRect(_startPosX + 10, _startPosY - 3, 3, 50);
	            g.fillRect(_startPosX + 10, _startPosY - 3, 10, 3);
	        }
	        if (Strip)
	        {
	            //полоса на кузове
	        	g.setColor(Color.BLACK);
	            g.drawRect(_startPosX + 16, _startPosY + 10, 80, 10);
	            g.setColor(MainColor);
	            g.fillRect(_startPosX + 16, _startPosY + 10, 80, 10);
	        }
    	}
    }
}
