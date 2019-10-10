package labTP;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TruckDesigner {

	
	
	private JFrame frame;
	private JButton btnNewButtonRight;
	private JButton btnNewButtonDown;
	private JButton btnNewButtonLeft;
	private JButton btnNewButtonUp;
	private JButton btnCreate;
	private JPanel panelMain;
	private ClassTruck truck = new ClassTruck(false, 10, 20, Color.blue, Color.orange, true, true, true);
	private ClassWheel wheel = new ClassWheel(Color.black, false, Wenum.wheel4);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TruckDesigner window = new TruckDesigner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TruckDesigner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		panelMain = new PanelTrack(truck, wheel);
		panelMain.setBounds(0, 0, 882, 603);
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(null);
		
		
		
		btnCreate = new JButton("Cоздать");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassWheel.WLife = true;
				ClassTruck.Life = true;
				truck.SetPosition(100, 100, frame.getWidth(), frame.getHeight());
				wheel.SetPosition(100, 100, frame.getWidth(), frame.getHeight());
				panelMain.repaint();
			}
		});
		btnCreate.setBounds(12, 13, 102, 33);
		panelMain.add(btnCreate);
		
		btnNewButtonRight = new JButton(">");
		btnNewButtonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				truck.MoveTransport(Tenum.Right);
				wheel.SetPosition(truck._startPosX, truck._startPosY, frame.getWidth(), frame.getHeight());
				panelMain.repaint();
			}
		});
		btnNewButtonRight.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButtonRight.setBounds(831, 551, 39, 39);
		panelMain.add(btnNewButtonRight);
		
		btnNewButtonDown = new JButton("v");
		btnNewButtonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				truck.MoveTransport(Tenum.Down);
				wheel.SetPosition(truck._startPosX, truck._startPosY, frame.getWidth(), frame.getHeight());
				panelMain.repaint();
			}
		});
		btnNewButtonDown.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButtonDown.setBounds(780, 551, 39, 39);
		panelMain.add(btnNewButtonDown);
		
		btnNewButtonLeft = new JButton("<");
		btnNewButtonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				truck.MoveTransport(Tenum.Left);
				wheel.SetPosition(truck._startPosX, truck._startPosY, frame.getWidth(), frame.getHeight());
				panelMain.repaint();
			}
		});
		btnNewButtonLeft.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButtonLeft.setBounds(729, 551, 39, 39);
		panelMain.add(btnNewButtonLeft);
		
		btnNewButtonUp = new JButton("^");
		btnNewButtonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				truck.MoveTransport(Tenum.Up);
				wheel.SetPosition(truck._startPosX, truck._startPosY, frame.getWidth(), frame.getHeight());
				panelMain.repaint();
			}
		});
		btnNewButtonUp.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButtonUp.setBounds(780, 499, 39, 39);
		panelMain.add(btnNewButtonUp);
	}
}
