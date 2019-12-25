package labTP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;
import java.util.Stack;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
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
	private Logger logger;
	private Logger loggererror;
	
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize()  {
		
		logger = Logger.getLogger(TruckDesigner.class.getName() + "1");
		loggererror = Logger.getLogger(TruckDesigner.class.getName()  + "2");
		
		try {
			FileHandler fh = null;
			FileHandler fh_error = null;
			fh = new FileHandler("D:\\log7laba\\file_info.txt");
			fh_error = new FileHandler("D:\\log7laba\\file_error.txt");
			logger.addHandler(fh);
			loggererror.addHandler(fh_error);
			logger.setUseParentHandlers(false);
			loggererror.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			fh_error.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setTitle("Парковка");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		parking = new MultiLevelParcing(5, frame.getWidth(), frame.getHeight() - 100);
		
		PanelParking panel = new PanelParking(parking.getParking(0));
		panel.setBounds(0, 0, 520, 510);
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
				try {
					String str = textField.getText();
					String str2 = textField_1.getText();
					if(str != "" && str2 != "") {
						int index = Integer.parseInt(str);
						int indexLevel = Integer.parseInt(str2); 
						truck = parking.getITransport(indexLevel, index);
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
		                    logger.info("Удален бензовоз по месту " + index);
		               
		                	paneltaketruck.repaint();
		               
					}
				} catch (ParkingNotFoundException ex) {
					String str = textField.getText();
					int index = Integer.parseInt(str);
					loggererror.warning("Не найден бензовоз по месту "+ index);
					JOptionPane.showMessageDialog(null, "Не найден бензовоз по месту " + index,
							"Exception", 0, null);
				} catch (Exception ex) {
					loggererror.warning("Неизвестная ошибка");
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка",
							"Exception", JOptionPane.ERROR_MESSAGE);
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
				try {
					TransferDisigned newFrame = new TransferDisigned();
					newFrame.frame.setVisible(true);
					newFrame.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					newFrame.setBaseVariables(panel, parking, list);
				} catch (ParkingOverflowException ex) {
					loggererror.warning("Парковка переполнена");
					JOptionPane.showMessageDialog(null, "Парковка переполнена");
				}
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
				
				try {
					JFileChooser fc = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
					fc.setFileFilter(filter);
					int result = fc.showOpenDialog(null);
					if(result == fc.APPROVE_OPTION) {
						if(parking.LoadData(fc.getSelectedFile().getPath())) {
							JOptionPane.showMessageDialog(null, "Загрузка прошла успешно!");
							panel.SetParking(parking.getParking(list.getSelectedIndex()));
							panel.repaint();
							logger.info("Загрузка прошла успешно");
						} else {
							logger.info("Не удалось загрузить");
							JOptionPane.showMessageDialog(null, "Не удалось загрузить");
						}
					}
					} catch(ParkingOccupiedPlaceException ex) {
						loggererror.warning("Ошибка загрузки" + ex.getMessage());
						JOptionPane.showMessageDialog(frame, ex.getMessage(),
								"Exception", JOptionPane.ERROR_MESSAGE);
					} catch(Exception ex) {
						loggererror.warning("Неизвестная ошибка загрузки");
						JOptionPane.showMessageDialog(frame, "Неизвестная ошибка загрузки",
								"Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menu.add(menuLoad);
		
		JMenuItem menuSave = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		menuSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser fc = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
					fc.setFileFilter(filter);
					int result = fc.showSaveDialog(null);
					if(result == fc.APPROVE_OPTION) {
						parking.SaveData(fc.getSelectedFile().getPath());
						logger.info("Cохранение прошло успешно");
						JOptionPane.showMessageDialog(null, "Сохранение прошло успешно");
					}
				} catch (Exception e1) {
					loggererror.warning("Неизвестная ошибка");
					JOptionPane.showMessageDialog(null, "Неизвестная ошибка");
				}
				
			}
		});
		menu.add(menuSave);
		
		JMenuItem menuSaveLvl = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		menuSaveLvl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser fc = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
					fc.setFileFilter(filter);
					int result = fc.showSaveDialog(null);
					if(result == fc.APPROVE_OPTION) {
						parking.SaveDataLvl(fc.getSelectedFile().getPath(), list.getSelectedIndex());
						logger.info("Сохранение уровня прошло успешно");
						JOptionPane.showMessageDialog(null, "Cохранение уровня прошло успешно");
					}
				} catch (Exception ex) {
					loggererror.warning("Неизвестная ошибка сохранения");
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка сохранения",
							"Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menu.add(menuSaveLvl);
		
		JMenuItem menuLoadLvl = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u0443\u0440\u043E\u0432\u0435\u043D\u044C");
		menuLoadLvl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
				fc.setFileFilter(filter);
				int result = fc.showOpenDialog(null);
				if(result == fc.APPROVE_OPTION) {
						if(parking.LoadDataLvl(fc.getSelectedFile().getPath())) {
							JOptionPane.showMessageDialog(null, "Загрузка уровня прошла успешна!");
							panel.SetParking(parking.getParking(list.getSelectedIndex()));
							logger.info("Загрузка уровня прошла успешно");
							panel.repaint();
						} else {
							logger.info("Не удалось загрузить");
							JOptionPane.showMessageDialog(null, "Не удалось загрузить");
						}
						}
					} catch (IOException ex) {
						loggererror.warning("Неизвестная ошибка загрузки " + ex.getMessage());
						JOptionPane.showMessageDialog(frame, "Неизвестная ошибка загрузки" + ex.getMessage(),
								"Exception", JOptionPane.ERROR_MESSAGE);
					} catch (ParkingOccupiedPlaceException ex) {
						loggererror.warning("Неизвестная ошибка" + ex.getMessage());
						JOptionPane.showMessageDialog(frame, ex.getMessage(),
								"Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menu.add(menuLoadLvl);
	}
}