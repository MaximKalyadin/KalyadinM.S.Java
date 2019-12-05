package labTP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Random;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class TruckDesigner {

	private JFrame frame;
	private ITransport truck;
	private IWheel wheel;
	private MultiLevelParcing parking;
	private Stack<ITransport> tableTruck = new Stack<ITransport>();
	private Stack<IWheel> tableWheel = new Stack<IWheel>();
	int pos = 0;
	private JTextField textField_1;
	private JTextField textField;
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
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		parking = new MultiLevelParcing(5, frame.getWidth(), frame.getHeight() - 100);
		
		PanelParking panel = new PanelParking(parking.getParking(0));
		panel.setBounds(0, 0, 542, 510);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(670, 332, 38, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for(int i = 0; i<5; i++) {
			dlm.addElement("Уровень" + i);
		}
		JList<String> list = new JList<String>();
		list.setModel(dlm);
		list.setSelectedIndex(0);
		list.setBounds(543, 0, 144, 115);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = list.getSelectedIndex();
				panel.SetParking(parking.getParking(index));
				panel.repaint();
			}
		});
		frame.getContentPane().add(list);
		
		
		PanelTrack paneltaketruck = new PanelTrack();
		paneltaketruck.setBounds(543, 422, 246, 128);
		frame.getContentPane().add(paneltaketruck);
		paneltaketruck.setLayout(null);
		
		JButton buttontaketruck = new JButton("Забрать");
		buttontaketruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText();
				String str2 = textField_1.getText();
				if(str != "" && str2 != "") {
					int index = Integer.parseInt(str);
					int indexLevel = Integer.parseInt(str2); 
					truck = parking.getITransport(indexLevel, index);
	                if (truck != null)
	                {
	                	paneltaketruck.RemoveTruck();
	                	paneltaketruck.repaint();
	                	wheel = parking.getIWheel(indexLevel, index);
	                	tableTruck.push(truck);
	                	
	                	if(truck == null) {
	                		truck.SetPosition(16, 50, paneltaketruck.getWidth(), paneltaketruck.getHeight());
	                		paneltaketruck.addTruck(truck);
	                	}else {
	                		truck.SetPosition(15, 50, paneltaketruck.getWidth(), paneltaketruck.getHeight());
	                		paneltaketruck.AddTruck(truck, wheel);
	                		tableWheel.push(wheel);
	                	}
	                	paneltaketruck.addTruck(truck);
	                	
	                	pos++;
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
		buttontaketruck.setBounds(552, 388, 89, 23);
		frame.getContentPane().add(buttontaketruck);
		
		JLabel label = new JLabel("Место");
		label.setBounds(552, 363, 55, 14);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(670, 360, 38, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnListTake = new JButton("Полученные");
		btnListTake.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListTake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < pos; i++) {
					System.out.println(tableTruck.pop());
				}
				System.out.println();
				pos = 0;
				System.out.println();
			}
		});
		btnListTake.setBounds(575, 145, 150, 23);
		frame.getContentPane().add(btnListTake);
		
		JLabel label_1 = new JLabel("Уровень");
		label_1.setBounds(552, 338, 55, 14);
		frame.getContentPane().add(label_1);
		
		JButton btnAddNewShip = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAddNewShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransferDisigned newFrame = new TransferDisigned();
				newFrame.frame.setVisible(true);
				newFrame.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				newFrame.setBaseVariables(panel, parking, list);
			}
		});
		btnAddNewShip.setBounds(575, 170, 150, 23);
		frame.getContentPane().add(btnAddNewShip);
	}
}