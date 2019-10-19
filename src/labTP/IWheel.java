package labTP;

import java.awt.Graphics;

public interface IWheel {
	public void SetPosition(int x, int y);
	public void DrawWheel(Graphics g, int k);
	public void Draw(Graphics g, Wenum temp);
}
