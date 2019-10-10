package labTP;

import java.awt.Color;
import java.awt.Graphics;

public class ClassWheel {
	
	private int _startPosX;
    private int _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    private Color ColorWheel;
    private Wenum temp;
    public static boolean WLife; 
    
    
    public ClassWheel(Color colorwheel, boolean life, Wenum t) {
    	ColorWheel = colorwheel;
    	WLife = life;
    	temp = t;
    }
    public void SetPosition(int x, int y, int width, int height){
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }
    public void DrawWheel(Graphics g, int x) {
    	if (WLife) {
	    	g.setColor(ColorWheel);
	        g.fillOval(_startPosX + 110, _startPosY + 40, 23, 23);
	        g.fillOval(_startPosX + x + 20, _startPosY + 40, 23, 23);
    	}
    }
    public void Draw(Graphics g) {
    	int x = 0;
    	switch (temp) 
    	{
    	case wheel2:
    		DrawWheel(g, x);
    		break;
    	case wheel3:
    		DrawWheel(g, x);
    		DrawWheel(g, x + 50);
    		break;
    	case wheel4:
    		DrawWheel(g, x);
    		DrawWheel(g, x + 25);
    		DrawWheel(g, x + 50);
    		break;
    	}
    }
    
    
}
