package labTP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class TransferDisigned {

	public JFrame frame;
	private PanelTransfer panelTruck;
	private PanelParking basePanel;
	private MultiLevelParcing baseParking;
	private JList<String> baseList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferDisigned window = new TransferDisigned();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void setBaseVariables(PanelParking basePanel, MultiLevelParcing baseParking, JList<String> baseList) {
		this.baseParking = baseParking;
		this.basePanel = basePanel;
		this.baseList = baseList;
	}
	/**
	 * Create the application.
	 */
	public TransferDisigned() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Окно добавления");
		frame.setBounds(100, 100, 646, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelTruck = new PanelTransfer();
		panelTruck.setBackground(Color.WHITE);
		panelTruck.setBounds(121, 34, 234, 137);
		panelTruck.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelTruck.setTransferHandler(new TransferHandlerClass());
		frame.getContentPane().add(panelTruck);
		
		JList<String> listTruck = new JList<String>();
		listTruck.setBounds(10, 43, 99, 61);
		listTruck.setBorder(new LineBorder(new Color(0, 0, 0)));
		listTruck.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		dlm.addElement("Обычный");
		dlm.addElement("Full");
		listTruck.setModel(dlm);
		listTruck.setDragEnabled(true);
		frame.getContentPane().add(listTruck);
		
		JLabel labelTypeOfTruck = new JLabel("Тип бензовоза");
		labelTypeOfTruck.setBounds(10, 18, 110, 14);
		frame.getContentPane().add(labelTypeOfTruck);
		
		JLabel labelMainColor = new JLabel("\u041E\u0441\u043D\u043E\u0432\u043D\u043E\u0439 \u0446\u0432\u0435\u0442");
		labelMainColor.setBounds(149, 177, 162, 23);
		labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);
		labelMainColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		TransferColorMain thMainColor = new TransferColorMain();
		thMainColor.setPanel(panelTruck);
		labelMainColor.setTransferHandler(thMainColor);
		frame.getContentPane().add(labelMainColor);
		
		JLabel labelDopColor = new JLabel("\u0414\u043E\u043F\u043E\u043B\u043D\u0438\u0442\u0435\u043B\u044C\u043D\u044B\u0439 \u0446\u0432\u0435\u0442");
		labelDopColor.setBounds(149, 213, 162, 23);
		labelDopColor.setHorizontalAlignment(SwingConstants.CENTER);
		labelDopColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		TransferColorDop thDopColor = new TransferColorDop();
		thDopColor.setPanel(panelTruck);
		labelDopColor.setTransferHandler(thDopColor);
		frame.getContentPane().add(labelDopColor);
		
		JLabel labelTypeOfWheel = new JLabel("Тип колес");
		labelTypeOfWheel.setBounds(382, 18, 79, 14);
		frame.getContentPane().add(labelTypeOfWheel);
		
		JList<String> listwheel = new JList<String>();
		listwheel.setBounds(380, 43, 99, 61);
		DefaultListModel<String> dlmBox = new DefaultListModel<String>();
		dlmBox.addElement("Обычные");
		dlmBox.addElement("Круглые");
		dlmBox.addElement("Квадратные");
		listwheel.setModel(dlmBox);
		listwheel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listwheel.setDragEnabled(true);
		listwheel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(listwheel);
		
		JLabel labelColor = new JLabel("\u0426\u0432\u0435\u0442\u0430");
		labelColor.setBounds(492, 18, 79, 14);
		frame.getContentPane().add(labelColor);
		
		JScrollPane scrollPaneColors = new JScrollPane();
		scrollPaneColors.setBounds(483, 43, 103, 164);
		frame.getContentPane().add(scrollPaneColors);
		
		JList<String> listColors = new JList<String>();
		scrollPaneColors.setViewportView(listColors);
		DefaultListModel<String> dlmColor = new DefaultListModel<String>();
		dlmColor.addElement("Желтый");
		dlmColor.addElement("Черный");
		dlmColor.addElement("Красный");
		dlmColor.addElement("Голубой");
		dlmColor.addElement("Зеленый");
		dlmColor.addElement("Розовый");
		dlmColor.addElement("Малиновый");
		dlmColor.addElement("Серый");
		listColors.setModel(dlmColor);
		listColors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listColors.setDragEnabled(true);
		listColors.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		
		JButton btnAccept = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panelTruck.getTruck() != null && panelTruck.getWheel() != null) {
					baseParking.getParking(baseList.getSelectedIndex()).addTruckall(panelTruck.getTruck(), panelTruck.getWheel());
					basePanel.repaint();
				}else if(panelTruck.getTruck() != null && panelTruck.getWheel() == null) {
					baseParking.getParking(baseList.getSelectedIndex()).addTruck(panelTruck.getTruck());
					basePanel.repaint();
				}
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnAccept.setBounds(10, 112, 99, 23);
		frame.getContentPane().add(btnAccept);
		
		JButton buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		buttonCancel.setBounds(10, 148, 99, 23);
		frame.getContentPane().add(buttonCancel);
	}

}
