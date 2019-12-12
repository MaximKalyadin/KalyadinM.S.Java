package labTP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Random;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import javax.swing.JOptionPane;

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
		frame.setTitle("Парковка");
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
		btnListTake.setBounds(537, 146, 150, 23);
		frame.getContentPane().add(btnListTake);
		
		JLabel label_1 = new JLabel("Уровень");
		label_1.setBounds(552, 338, 55, 14);
		frame.getContentPane().add(label_1);
		
		JButton btnAddNewTruck = new JButton("Добавить");
		btnAddNewTruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransferDisigned newFrame = new TransferDisigned();
				newFrame.frame.setVisible(true);
				newFrame.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				newFrame.setBaseVariables(panel, parking, list);
				
			}
		});
		btnAddNewTruck.setBounds(537, 169, 150, 23);
		frame.getContentPane().add(btnAddNewTruck);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(menu);
		
		JMenuItem menuLoad = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C");
		menuLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
				fc.setFileFilter(filter);
				int result = fc.showOpenDialog(null);
				if(result == fc.APPROVE_OPTION) {
					if(parking.LoadData(fc.getSelectedFile().getPath())) {
						JOptionPane.showMessageDialog(null, "!");
						panel.SetParking(parking.getParking(list.getSelectedIndex()));
						panel.repaint();
					}else
						JOptionPane.showMessageDialog(null, "Не успешно!");
				}
			}
		});
		menu.add(menuLoad);
		
		JMenuItem menuSave = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		menuSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
				fc.setFileFilter(filter);
				int result = fc.showSaveDialog(null);
				if(result == fc.APPROVE_OPTION) {
					if(parking.SaveData(fc.getSelectedFile().getPath(), list.getSelectedIndex())) {
						JOptionPane.showMessageDialog(null, "Успешно!");
					}else
						JOptionPane.showMessageDialog(null, "Не успешно!");
				}
			}
		});
		menu.add(menuSave);
		
		JMenuItem menuSaveLvl = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		menuSaveLvl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
				fc.setFileFilter(filter);
				int result = fc.showSaveDialog(null);
				if(result == fc.APPROVE_OPTION) {
					if(parking.SaveDataLvl(fc.getSelectedFile().getPath(), list.getSelectedIndex())) {
						JOptionPane.showMessageDialog(null, "Успешно!");
					}else
						JOptionPane.showMessageDialog(null, "Не успешно!");
				}
			}
		});
		menu.add(menuSaveLvl);
		
		JMenuItem menuLoadLvl = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		menuLoadLvl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
				fc.setFileFilter(filter);
				int result = fc.showOpenDialog(null);
				if(result == fc.APPROVE_OPTION) {
					if(parking.LoadDataLvl(fc.getSelectedFile().getPath())) {
						JOptionPane.showMessageDialog(null, "Успешно!");
						panel.SetParking(parking.getParking(list.getSelectedIndex()));
						panel.repaint();
					}else
						JOptionPane.showMessageDialog(null, "Не успешно!");
				}
			}
		});
		menu.add(menuLoadLvl);
	}
}