package labTP;

import java.awt.Color;
import java.awt.Graphics;

public class ClassWheel implements IWheel{
	
	static int _startPosX;
    static int _startPosY;
    private Color ColorWheel;
    
    
    public ClassWheel(Color colorwheel) {
    	ColorWheel = colorwheel;
    }
    public void SetPosition(int x, int y){
        _startPosX = x;
        _startPosY = y;
    }
    public void DrawWheel(Graphics g, int k) {
    		int x = 0;
    		g.setColor(ColorWheel);
	        g.fillOval(_startPosX + 110, _startPosY + 40, 23, 23);
    		for (int i = 0; i < k; i++) {
		        g.fillOval(_startPosX + x + 20, _startPosY + 40, 23, 23);
    			x += 25;
    		}
    }
    public void Draw(Graphics g, Wenum temp ) {
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
    @Override
	public String toString() {
		return "ClassWheel";
	}
    
}