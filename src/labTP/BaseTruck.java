package labTP;

import java.awt.Color;
import java.awt.Graphics;

public class BaseTruck extends AbstractTruck {
    private int truckWidth = 100;
    private int truckHeight = 60;
    private IWheel wheel;
    
    public BaseTruck(int maxSpeed, Color mainColor, int weight, IWheel wheel) {
    	MaxSpeed = maxSpeed;
        MainColor = mainColor;
        Weight = weight;
        this.wheel = wheel;
    }
    @Override
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
    public int getPosX() {
		return _startPosX;
	}

	public int getPosY() {
		return _startPosY;
	}
	@Override
    public void DrawTruck(Graphics g) {
    	wheel.SetPosition(getPosX(), getPosY());
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
	    	wheel.Draw(g, Wenum.wheel2);
    }
    
}
