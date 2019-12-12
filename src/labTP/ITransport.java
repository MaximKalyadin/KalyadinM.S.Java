package labTP;
import java.awt.Color;
import java.awt.Graphics;
public interface ITransport {
	void SetPosition(int x, int y, int width, int height);
	void MoveTransport(Tenum direction);
    void DrawTruck(Graphics g);
    int getPosX();
    int getPosY();
    String getConfig();
}