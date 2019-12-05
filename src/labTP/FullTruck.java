package labTP;

import java.awt.Color;
import java.awt.Graphics;

public class FullTruck extends BaseTruck{
	 public Color DopColor;
	 public boolean Vat;
	 public boolean Stairs;
	 public boolean Strip;
	 
	 public FullTruck(int maxSpeed, Color mainColor, int weight, Color dopcolor, boolean vat, boolean stairs, boolean strip) {
		 super(maxSpeed, mainColor, weight);
		 DopColor = dopcolor;
		 Vat = vat;
		 Stairs = stairs;
		 Strip = strip;
	 }
	 
	 public void setBaseColor(Color color) {
	    	MainColor = color;
	    }
	    
	    public void setDopColor(Color color) {
	    	DopColor = color;
	    }

	@Override
	 public void DrawTruck(Graphics g) {
		 super.DrawTruck(g);
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
	 @Override
	    public String toString() {
	   	 return "FullTruck";
	    }
}
