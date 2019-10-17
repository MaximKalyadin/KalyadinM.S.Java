package labTP;

import java.awt.Color;
import java.awt.Graphics;

public abstract class AbstractTruck implements ITransport{
	protected int _startPosX;
    protected int _startPosY;
    protected int _pictureWidth;
    protected int _pictureHeight;
    public int MaxSpeed;
    public Color MainColor;
    public int Weight;
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }
    public abstract void DrawTruck(Graphics g);
    public abstract void MoveTransport(Tenum direction);
}
