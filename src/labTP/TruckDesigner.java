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
	private JButton btnNewButtonUp; ;
	private JButton btnCreate;
	private PanelTrack panelMain;
	private ITransport truck;
	private IWheel wheel;
	private JButton btnCreateFull;
	
	
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
				truck = new BaseTruck(10, Color.blue, 20, wheel = new TwoDiskWheel(Color.BLACK));
				panelMain.setTruck(truck);
				truck.SetPosition(100, 100, frame.getWidth(), frame.getHeight());
				panelMain.repaint();
			}
		});
		btnCreate.setBounds(12, 13, 102, 33);
		panelMain.add(btnCreate);
		
		btnNewButtonRight = new JButton(">");
		btnNewButtonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (truck != null && wheel != truck) {
					truck.MoveTransport(Tenum.Right);
					wheel.SetPosition(truck.getPosX(), truck.getPosY());
					panelMain.repaint();
				}
			}
		});
		btnNewButtonRight.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButtonRight.setBounds(831, 551, 39, 39);
		panelMain.add(btnNewButtonRight);
		
		btnNewButtonDown = new JButton("v");
		btnNewButtonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (truck != null && wheel != truck) {
				truck.MoveTransport(Tenum.Down);
				wheel.SetPosition(truck.getPosX(), truck.getPosY());
				panelMain.repaint();
			}
			}
		});
		btnNewButtonDown.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButtonDown.setBounds(780, 551, 39, 39);
		panelMain.add(btnNewButtonDown);
		
		btnNewButtonLeft = new JButton("<");
		btnNewButtonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (truck != null && wheel != truck) {
					truck.MoveTransport(Tenum.Left);
					wheel.SetPosition(truck.getPosX(), truck.getPosY());
					panelMain.repaint();
				}
			}
		});
		btnNewButtonLeft.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButtonLeft.setBounds(729, 551, 39, 39);
		panelMain.add(btnNewButtonLeft);
		
		btnNewButtonUp = new JButton("^");
		btnNewButtonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (truck != null && wheel != truck) {
					truck.MoveTransport(Tenum.Up);
					wheel.SetPosition(truck.getPosX(), truck.getPosY());
					panelMain.repaint();
				}
			}
		});
		btnNewButtonUp.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButtonUp.setBounds(780, 499, 39, 39);
		panelMain.add(btnNewButtonUp);
		
		btnCreateFull = new JButton("Создать Full");
		btnCreateFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				truck = new FullTruck(10, Color.blue, 20, wheel = new TwoDiskWheel(Color.BLACK), Color.orange, true, true, true);
				panelMain.setTruck(truck);
				truck.SetPosition(100, 100, frame.getWidth(), frame.getHeight());
				panelMain.repaint();
			}
		});
		btnCreateFull.setBounds(125, 13, 147, 33);
		panelMain.add(btnCreateFull);
	}
}
