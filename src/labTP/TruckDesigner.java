package labTP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TruckDesigner {

	private JFrame frame;
	private ITransport truck;
	private IWheel wheel;
	private JTextField textField;
	private Parking<ITransport> parking;

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
		frame.setBounds(100, 100, 1099, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		parking = new Parking<ITransport>(5, frame.getWidth(), frame.getHeight());
		
		PanelParking panel = new PanelParking(parking);
		panel.setBounds(0, 0, 658, 510);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JButton buttonaddtruck = new JButton("Бензовоз");
		buttonaddtruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(frame, "Основной цвет", Color.cyan);
				if(color != null) {
					truck = new BaseTruck(10, color, 20, wheel = new TwoDiskWheel(Color.BLACK));
					parking.addTruck(truck);
					panel.repaint();
				}
			}
		});
		buttonaddtruck.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonaddtruck.setBounds(702, 13, 143, 71);
		frame.getContentPane().add(buttonaddtruck);
		
		JButton buttonaddBase = new JButton("FullБензовоз");
		buttonaddBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(frame, "Основной цвет", Color.blue);
				if(color != null) {
					Color colorDop = JColorChooser.showDialog(frame, "Дополнительный цвет", Color.blue);
					if(colorDop != null) {
						truck = new FullTruck(10, color, 20, wheel = new TwoDiskWheel(Color.BLACK), colorDop, true, true, true);
						parking.addTruck(truck);
						panel.repaint();
					}
				}
			}
		});
		buttonaddBase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonaddBase.setBounds(879, 13, 143, 71);
		frame.getContentPane().add(buttonaddBase);
		
		PanelTrack paneltaketruck = new PanelTrack();
		paneltaketruck.setBounds(692, 399, 246, 128);
		frame.getContentPane().add(paneltaketruck);
		paneltaketruck.setLayout(null);
		
		JButton buttontaketruck = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		buttontaketruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = textField.getText();
				if(s != "") {
					int index = Integer.parseInt(s);
	                if (parking.getTruck(index) != null) {
	                	paneltaketruck.RemoveTruck();
	                	paneltaketruck.repaint();
	                	truck = parking.RemoveTruck(index);
	                	paneltaketruck.addTruck(truck);
	                    truck.SetPosition(16, 50, paneltaketruck.getWidth(), paneltaketruck.getHeight());
	                    paneltaketruck.repaint();
	                    panel.repaint();
	                }
	                else
	                {
	                	paneltaketruck.RemoveTruck();
	                	paneltaketruck.repaint();
	                }
				}
			}
		});
		buttontaketruck.setBounds(765, 360, 89, 31);
		frame.getContentPane().add(buttontaketruck);
		
		JLabel label = new JLabel("\u041F\u043E\u0437\u0438\u0446\u0438\u044F ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(755, 324, 113, 23);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(842, 326, 45, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}