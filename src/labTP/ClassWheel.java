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
    public void DrawWheel(Graphics g, int k){
    	if (WLife) {
    		int x = 0;
    		g.setColor(ColorWheel);
	        g.fillOval(_startPosX + 110, _startPosY + 40, 23, 23);
    		for (int i = 0; i < k; i++) {
		        g.fillOval(_startPosX + x + 20, _startPosY + 40, 23, 23);
    			x += 25;
    		}
    	}
    }
    public void Draw(Graphics g) {
    	int k = 0;
    	switch (temp) 
    	{
    	case wheel2:
    		k = 1;
    		break;
    	case wheel3:
    		k = 2;
    		break;
    	case wheel4:
    		k = 3;
    		break;
    	}
    	DrawWheel(g, k);
    }
    
    
}
