package labTP;

import java.awt.Color;
import java.awt.Graphics;

public class OneDiskWheel implements IWheel{

	private int _startPosX;
    private int _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    private Color ColorWheel;
    
    
    public OneDiskWheel(Color colorwheel) {
    	ColorWheel = colorwheel;
    }
    public void SetPosition(int x, int y){
        _startPosX = x;
        _startPosY = y;
    }
    public void DrawWheel(Graphics g, int k){
    		int x = 0;
    		g.setColor(ColorWheel);
	        g.fillOval(_startPosX + 110, _startPosY + 40, 23, 23);
	        g.setColor(Color.yellow);
	        g.fillOval(_startPosX + x + 113, _startPosY + 43, 17, 17);
    		for (int i = 0; i < k; i++) {
    			g.setColor(ColorWheel);
		        g.fillOval(_startPosX + x + 20, _startPosY + 40, 23, 23);
		        g.setColor(Color.yellow);
		        g.fillOval(_startPosX + x + 23, _startPosY + 43, 17, 17);
    			x += 25;
    		
    	}
    }
    public void Draw(Graphics g, Wenum temp) {
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
