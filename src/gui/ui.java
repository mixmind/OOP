package gui;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import DataBase.csvBase;
import Filter.FilterDate;
import Filter.FilterId;
import Filter.FilterRadius;
import WiFi_data.GeoModDat;
import WiFi_data.Network;
import de.micromata.opengis.kml.v_2_2_0.Folder;

import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algo.AlgoFIndMac;
import Convert.toCSV;
import Convert.toKML;

import javax.swing.event.MenuKeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ui {

	private JFrame frame;
	private Network nt=new Network();
	private Network undo=new Network();
	private JFileChooser fileChooser = new JFileChooser();
	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private JTextField textFieldBefore;
	private JTextField textFieldAfter;
	private JTextField TextFieldID;
	private JTextField textFieldRadius;
	private JTextField textFieldLat;
	private JTextField textFieldLon;
	private JTextField textFieldFmac;
	private JLabel labelLat = new JLabel("");
	private JLabel labelLon = new JLabel("");
	private JLabel labelAlt = new JLabel("");
	private Filter temp=new Filter();
	private boolean filterTest=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ui window = new ui();
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
	public ui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 699, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Button.background"));
		JMenu mnInputoutput = new JMenu("File");
		mnInputoutput.setBackground(UIManager.getColor("Button.background"));
		JMenuItem mntmNewBase = new JMenuItem("New Base");
		JMenuItem mntmSaveBase = new JMenuItem("Save Base");
		JMenuItem mntmOpenBase = new JMenuItem("Open Base");

		JButton btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon("C:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\src\\gui\\clear.png"));
		JButton btnAlgoritms = new JButton("Algoritms");
		JButton btnOpenFile = new JButton("Load file");
		btnOpenFile.setIcon(new ImageIcon("C:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\src\\gui\\file.png"));
		JButton btnOpenFolder = new JButton("Load folder");
		btnOpenFolder.setIcon(new ImageIcon("C:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\src\\gui\\folder.png"));
		JButton btnIo = new JButton("I/O");
		JButton btnFilters = new JButton("Filters");
		btnFilters.setIcon(new ImageIcon("C:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\src\\gui\\filter.png"));
		JButton btnFilter = new JButton("Filter it");
		JButton btnTokml = new JButton("toKml");
		JButton btnUndoLastFilter = new JButton("Undo last filter");
		btnTokml.setHorizontalAlignment(SwingConstants.LEFT);
		btnTokml.setIcon(new ImageIcon("C:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\src\\gui\\kml.png"));
		JButton btnTocsv = new JButton("toCsv");
		btnTocsv.setIcon(new ImageIcon("C:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\src\\gui\\csv_icon.png"));
		JButton btnAlgo = new JButton("Algo 1");
		JButton btnAlgo_1 = new JButton("Algo 2");
		JButton btnSaveFilter = new JButton("Save filter");
		btnSaveFilter.setHorizontalAlignment(SwingConstants.LEFT);
		btnSaveFilter.setIcon(new ImageIcon("C:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\src\\gui\\save.png"));

		JButton btnLoadFilter = new JButton("Load filter");

		JRadioButton rdbtnAnd = new JRadioButton("and");
		JRadioButton rdbtnOr = new JRadioButton("or");
		JRadioButton rdbtnNot = new JRadioButton("not");

		JCheckBox chckbxDate = new JCheckBox("Date");
		JCheckBox chckbxRadius = new JCheckBox("Radius");
		JCheckBox chckbxID = new JCheckBox("ID");



		JLabel lblDateBefore = new JLabel("Date before");
		JLabel lblDateAfter = new JLabel("Date after");
		JLabel lblID = new JLabel("ID");
		JLabel lblRadius = new JLabel("Radius");
		JLabel lblLat = new JLabel("Lat");
		JLabel lblLon = new JLabel("Lon");
		JLabel lblFormatOfDate = new JLabel("Format of Date:yyyy-MM-dd HH:mm");
		JLabel lblMacToFind = new JLabel("Mac to find his place");

		JLabel label_2 = new JLabel("Lat");
		JLabel label = new JLabel("Lon");
		JLabel label_1 = new JLabel("Alt");


		textFieldFmac = new JTextField();
		TextFieldID = new JTextField();
		textFieldRadius = new JTextField();
		textFieldLat = new JTextField();
		textFieldLon = new JTextField();
		textFieldBefore = new JTextField();
		textFieldAfter = new JTextField();

		btnClear.setBounds(569, 353, 105, 37);
		frame.getContentPane().add(btnClear);

		menuBar.setBounds(0, 0, 31, 45);
		frame.getContentPane().add(menuBar);
		menuBar.add(mnInputoutput);
		btnOpenFile.setBounds(272, 150, 135, 37);
		btnOpenFolder.setBounds(458, 150, 135, 37);
		btnIo.setBounds(17, 69, 103, 37);
		btnFilters.setBounds(17, 122, 103, 37);
		btnAlgoritms.setBounds(17, 175, 103, 37);

		frame.getContentPane().add(lblID);


		TextFieldID.setBounds(360, 147, 206, 35);
		frame.getContentPane().add(TextFieldID);
		TextFieldID.setColumns(10);

		lblID.setBounds(306, 150, 31, 29);
		lblRadius.setBounds(329, 191, 70, 29);
		frame.getContentPane().add(lblRadius);


		textFieldRadius.setBounds(421, 188, 206, 35);
		frame.getContentPane().add(textFieldRadius);
		textFieldRadius.setColumns(10);


		lblLat.setBounds(272, 230, 33, 29);
		frame.getContentPane().add(lblLat);


		lblLon.setBounds(507, 230, 38, 29);
		frame.getContentPane().add(lblLon);


		textFieldLat.setBounds(184, 259, 206, 35);
		frame.getContentPane().add(textFieldLat);
		textFieldLat.setColumns(10);


		textFieldLon.setBounds(417, 259, 206, 35);
		frame.getContentPane().add(textFieldLon);
		textFieldLon.setColumns(10);

		lblFormatOfDate.setBounds(260, 111, 206, 29);
		frame.getContentPane().add(lblFormatOfDate);
		frame.getContentPane().add(btnFilters);
		frame.getContentPane().add(chckbxDate);
		frame.getContentPane().add(chckbxRadius);
		frame.getContentPane().add(chckbxID);

		chckbxRadius.setBounds(163, 187, 70, 37);
		chckbxDate.setBounds(163, 65, 54, 37);

		chckbxID.setBounds(163, 146, 54, 37);
		lblDateBefore.setBackground(Color.DARK_GRAY);
		lblDateBefore.setBounds(323, 40, 76, 29);
		frame.getContentPane().add(lblDateBefore);

		lblDateAfter.setBounds(491, 40, 62, 29);
		frame.getContentPane().add(lblDateAfter);

		btnFilter.setBounds(260, 307, 73, 39);
		frame.getContentPane().add(btnFilter);


		textFieldBefore.setBounds(285, 65, 176, 35);
		frame.getContentPane().add(textFieldBefore);
		textFieldBefore.setColumns(10);


		textFieldAfter.setBounds(473, 65, 176, 35);
		frame.getContentPane().add(textFieldAfter);
		textFieldAfter.setColumns(10);

		frame.getContentPane().add(btnOpenFile);
		frame.getContentPane().add(btnOpenFolder);
		frame.getContentPane().add(btnIo);
		frame.getContentPane().add(btnAlgoritms);



		btnIo.setVisible(false);
		btnOpenFile.setVisible(false);
		btnOpenFolder.setVisible(false);
		btnFilters.setVisible(false);
		btnAlgoritms.setVisible(false);
		btnFilter.setVisible(false);
		btnTocsv.setVisible(false);
		btnAlgo.setVisible(false);
		btnAlgo_1.setVisible(false);
		btnSaveFilter.setVisible(false);
		btnLoadFilter.setVisible(false);


		lblDateBefore.setVisible(false);
		lblDateAfter.setVisible(false);
		lblID.setVisible(false);
		lblLat.setVisible(false);
		lblLon.setVisible(false);
		lblFormatOfDate.setVisible(false);
		lblRadius.setVisible(false);
		lblMacToFind.setVisible(false);
		label.setVisible(false);
		label_1.setVisible(false);
		label_2.setVisible(false);
		labelLat.setBackground(Color.ORANGE);
		labelLat.setForeground(Color.ORANGE);
		labelLat.setVisible(false);
		labelAlt.setBackground(Color.ORANGE);
		labelAlt.setForeground(Color.ORANGE);
		labelAlt.setVisible(false);
		labelLon.setBackground(Color.ORANGE);
		labelLon.setForeground(Color.ORANGE);
		labelLon.setVisible(false);

		TextFieldID.setVisible(false);
		textFieldRadius.setVisible(false);
		textFieldLat.setVisible(false);
		textFieldLon.setVisible(false);
		textFieldBefore.setVisible(false);
		textFieldAfter.setVisible(false);
		textFieldFmac.setVisible(false);

		chckbxRadius.setVisible(false);
		chckbxDate.setVisible(false);
		chckbxID.setVisible(false);

		rdbtnAnd.setVisible(false);
		rdbtnNot.setVisible(false);
		rdbtnOr.setVisible(false);

		btnFilter.setEnabled(false);
		mntmSaveBase.setEnabled(false);
		btnUndoLastFilter.setEnabled(false);

		mnInputoutput.add(mntmNewBase);
		mnInputoutput.add(mntmOpenBase);
		mnInputoutput.add(mntmSaveBase);

		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undo=new Network(nt);
				//filterTest=true;
				btnUndoLastFilter.setEnabled(true);
				if(chckbxDate.isSelected()&&!chckbxID.isSelected()&&!chckbxRadius.isSelected()) 
				{
					if(!textFieldBefore.getText().isEmpty()&&!textFieldAfter.getText().isEmpty()&&!rdbtnNot.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText()); format.parse(textFieldAfter.getText());
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							nt=date.runOn(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered with Date");

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Enter correct Date");
						}
					}
					else if(!textFieldBefore.getText().isEmpty()&&!textFieldAfter.getText().isEmpty()&&rdbtnNot.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText()); format.parse(textFieldAfter.getText());
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							nt=date.runNot(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered with Date");

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Enter correct Date");
						}
					}
					else JOptionPane.showMessageDialog(null, "Cant Filter without settings");
				}
				else if(chckbxID.isSelected()&&!chckbxDate.isSelected()&&!chckbxRadius.isSelected())
				{
					if(!TextFieldID.getText().isEmpty()&&!rdbtnNot.isSelected())
					{
						FilterId mac=new FilterId(TextFieldID.getText());
						nt=mac.runOn(nt);
						JOptionPane.showMessageDialog(null, "The Base was filtered with mac");
					}
					else if(!TextFieldID.getText().isEmpty()&&rdbtnNot.isSelected())
					{
						FilterId mac=new FilterId(TextFieldID.getText());
						nt=mac.runNot(nt);
						JOptionPane.showMessageDialog(null, "The Base was filtered without mac");
					}
					else JOptionPane.showMessageDialog(null, "Cant Filter without settings");
				}
				else if(chckbxRadius.isSelected()&&!chckbxDate.isSelected()&&!chckbxID.isSelected())
				{
					if(!textFieldLat.getText().isEmpty()&&!textFieldLon.getText().isEmpty()&&
							!textFieldRadius.getText().isEmpty()&&!rdbtnNot.isSelected())
					{
						try {
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							nt=radius.runOn(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered with Radius");
						}
						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Enter correct parameters");
						}
					}
					else if(!textFieldLat.getText().isEmpty()&&!textFieldLon.getText().isEmpty()&&
							!textFieldRadius.getText().isEmpty()&&rdbtnNot.isSelected())
					{
						try {
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							nt=radius.runNot(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered without Radius");
						}
						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Enter correct parameters");
						}

					}
					else JOptionPane.showMessageDialog(null, "Cant Filter without settings");
				}
				else if(chckbxDate.isSelected()&&chckbxID.isSelected()&&!chckbxRadius.isSelected())
				{
					if(rdbtnAnd.isSelected()&&!rdbtnNot.isSelected()&&!rdbtnOr.isSelected()) {
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							FilterId mac=new FilterId(TextFieldID.getText());
							nt=date.runOn(nt);
							nt=mac.runOn(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered with Date and Mac");

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Enter correct Date");
						}
					}
					else if(rdbtnOr.isSelected()&&!rdbtnAnd.isSelected()&&!rdbtnNot.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							FilterId mac=new FilterId(TextFieldID.getText());
							int size=nt.getReal_size();
							nt=date.runOn(nt);
							if(nt.getReal_size()==size) 
							{
								nt=mac.runOn(nt);
								JOptionPane.showMessageDialog(null, "The Base was filtered with Mac");
							}
							else JOptionPane.showMessageDialog(null, "The Base was filtered with Date");

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Enter correct Date");
						}
					}
					else if(rdbtnNot.isSelected()&&!rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							FilterId mac=new FilterId(TextFieldID.getText());
							nt=date.runNot(nt);
							nt=mac.runNot(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered with Date and Mac");

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Enter correct Date");
						}
					}
					else if(!rdbtnAnd.isSelected()&&rdbtnNot.isSelected()&&rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							FilterId mac=new FilterId(TextFieldID.getText());
							int size=nt.getReal_size();
							nt=date.runNot(nt);
							if(nt.getReal_size()==size) 
							{
								nt=mac.runNot(nt);
								JOptionPane.showMessageDialog(null, "The Base was filtered with Mac");
							}
							else JOptionPane.showMessageDialog(null, "The Base was filtered with Date");

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Enter correct Date");
						}
					}
					else JOptionPane.showMessageDialog(null, "Cant Filter without settings");
				}
				else if(chckbxDate.isSelected()&&!chckbxID.isSelected()&&chckbxRadius.isSelected())
				{
					if(rdbtnAnd.isSelected()&&!rdbtnNot.isSelected()&&!rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							nt=radius.runOn(nt);
							nt=date.runOn(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered with Date and Radius");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Date or Radius parameters");
						}
					}
					else if(rdbtnNot.isSelected()&&!rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							nt=radius.runNot(nt);
							nt=date.runNot(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered without Date and Radius");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Date or Radius parameters");
						}
					}
					else if(!rdbtnAnd.isSelected()&&!rdbtnNot.isSelected()&&rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							int size=nt.getReal_size();
							nt=date.runOn(nt);
							if(nt.getReal_size()==size) 
							{
								nt=radius.runOn(nt);
								JOptionPane.showMessageDialog(null, "The Base was filtered with Radius");
							}
							else JOptionPane.showMessageDialog(null, "The Base was filtered with Date");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Date or Radius parameters");
						}

					}
					else if(!rdbtnAnd.isSelected()&&rdbtnNot.isSelected()&&rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							int size=nt.getReal_size();
							nt=date.runNot(nt);
							if(nt.getReal_size()==size) 
							{
								nt=radius.runNot(nt);
								JOptionPane.showMessageDialog(null, "The Base was filtered without Radius");
							}
							else JOptionPane.showMessageDialog(null, "The Base was filtered without Date");

						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Date or Radius parameters");
						}
					}
					else JOptionPane.showMessageDialog(null, "Cant Filter without settings");

				}
				else if(!chckbxDate.isSelected()&&chckbxID.isSelected()&&chckbxRadius.isSelected())
				{
					if(rdbtnAnd.isSelected()&&!rdbtnNot.isSelected()&&!rdbtnOr.isSelected())
					{
						try {

							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterId mac=new FilterId(TextFieldID.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							nt=radius.runOn(nt);
							nt=mac.runOn(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered with Mac and Radius");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Radius parameters");
						}
					}
					else if(rdbtnNot.isSelected()&&!rdbtnOr.isSelected())
					{
						try {
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterId mac=new FilterId(TextFieldID.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							nt=radius.runNot(nt);
							nt=mac.runNot(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered without Mac and Radius");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Radius parameters");
						}
					}
					else if(!rdbtnAnd.isSelected()&&!rdbtnNot.isSelected()&&rdbtnOr.isSelected())
					{
						try {
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterId mac=new FilterId(TextFieldID.getText());
							int size=nt.getReal_size();
							nt=mac.runOn(nt);
							if(nt.getReal_size()==size) 
							{
								nt=radius.runOn(nt);
								JOptionPane.showMessageDialog(null, "The Base was filtered with Radius");
							}
							else JOptionPane.showMessageDialog(null, "The Base was filtered with Mac");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Radius parameters");
						}
					}
					else if(!rdbtnAnd.isSelected()&&rdbtnNot.isSelected()&&rdbtnOr.isSelected())
					{
						try {
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterId mac=new FilterId(TextFieldID.getText());
							int size=nt.getReal_size();
							nt=mac.runNot(nt);
							if(nt.getReal_size()==size) 
							{
								nt=radius.runNot(nt);
								JOptionPane.showMessageDialog(null, "The Base was filtered without Radius");
							}
							else JOptionPane.showMessageDialog(null, "The Base was filtered without Mac");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Radius parameters");
						}
					}
					else JOptionPane.showMessageDialog(null, "Cant Filter without settings");

				}
				else if(chckbxDate.isSelected()&&chckbxID.isSelected()&&chckbxRadius.isSelected())
				{
					if(rdbtnAnd.isSelected()&&!rdbtnNot.isSelected()&&!rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterId mac=new FilterId(TextFieldID.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							nt=date.runOn(nt);
							nt=radius.runOn(nt);
							nt=mac.runOn(nt);

							JOptionPane.showMessageDialog(null, "The Base was filtered with Mac,Date and Radius");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Radius ot Date parameters");
						}
					}
					else if(rdbtnNot.isSelected()&&!rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterId mac=new FilterId(TextFieldID.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							nt=date.runNot(nt);
							nt=radius.runNot(nt);
							nt=mac.runNot(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered without Mac,Date and Radius");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Radius or Date parameters");
						}
					}
					else if(!rdbtnAnd.isSelected()&&!rdbtnNot.isSelected()&&rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterId mac=new FilterId(TextFieldID.getText());
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));

							int size=nt.getReal_size();
							nt=mac.runOn(nt);
							if(nt.getReal_size()==size) 
							{
								size=nt.getReal_size();
								nt=radius.runOn(nt);
								if(size==nt.getReal_size())
								{
									nt=date.runOn(nt);
									JOptionPane.showMessageDialog(null, "The Base was filtered with Date");
								}
								else JOptionPane.showMessageDialog(null, "The Base was filtered with Radius");

							}
							else JOptionPane.showMessageDialog(null, "The Base was filtered with Mac");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Radius or Date parameters");
						}
					}
					else if(!rdbtnAnd.isSelected()&&rdbtnNot.isSelected()&&rdbtnOr.isSelected())
					{
						try {
							format.parse(textFieldBefore.getText());
							format.parse(textFieldAfter.getText());
							Double.parseDouble(textFieldRadius.getText());
							Double.parseDouble(textFieldLat.getText());
							Double.parseDouble(textFieldLon.getText());
							FilterRadius radius=new FilterRadius(Double.parseDouble(textFieldRadius.getText()), 
									Double.parseDouble(textFieldLat.getText())
									, Double.parseDouble(textFieldLon.getText()));
							FilterId mac=new FilterId(TextFieldID.getText());
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));

							int size=nt.getReal_size();
							nt=mac.runNot(nt);
							if(nt.getReal_size()==size) 
							{
								size=nt.getReal_size();
								nt=radius.runNot(nt);
								if(size==nt.getReal_size())
								{
									nt=date.runNot(nt);
									JOptionPane.showMessageDialog(null, "The Base was filtered with Date");
								}
								else JOptionPane.showMessageDialog(null, "The Base was filtered with Radius");

							}
							else JOptionPane.showMessageDialog(null, "The Base was filtered with Mac");
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter correct Radius or Date parameters");
						}
					}
				}
				else JOptionPane.showMessageDialog(null, "Cant Filter without settings");
			}


		});

		rdbtnAnd.setBounds(163, 303, 54, 25);
		frame.getContentPane().add(rdbtnAnd);
		rdbtnAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFilter.setEnabled(true);
				if(rdbtnOr.isSelected())
				{
					rdbtnOr.setSelected(false);
				}
			}
		});


		rdbtnOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFilter.setEnabled(true);
				if(rdbtnAnd.isSelected())
				{
					rdbtnAnd.setSelected(false);

				}
			}
		});
		rdbtnOr.setBounds(163, 357, 54, 25);
		frame.getContentPane().add(rdbtnOr);


		rdbtnNot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFilter.setEnabled(true);
			}
		});
		rdbtnNot.setBounds(163, 330, 54, 25);
		frame.getContentPane().add(rdbtnNot);


		btnTokml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileFilter(new FileNameExtensionFilter("CSV filtered files", "csv"));
				fileChooser.showOpenDialog(null);
				int size=0;
				if(fileChooser.getSelectedFile()!=null) {
					try {
						toKML temp=new toKML(fileChooser.getSelectedFile().getPath());
						size= temp.size();
					} catch (ParseException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Parsed to Kml "+size+" lines");
				}
			}
		});
		btnTokml.setBounds(17, 259, 107, 45);
		frame.getContentPane().add(btnTokml);

		btnTocsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toCSV temp=new toCSV(nt);
				JOptionPane.showMessageDialog(null, "Parsed to Csv "+nt.getReal_size()+" lines");
			}
		});
		btnTocsv.setBounds(17, 330, 107, 45);
		frame.getContentPane().add(btnTocsv);


		btnAlgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldFmac.getText().isEmpty())
				{
					GeoModDat temp=AlgoFIndMac.routerPlaceAlgo1(nt, textFieldFmac.getText());
					if(temp!=null)
					{
						labelLat.setText(Double.toString(temp.getLat()));
						labelLon.setText(Double.toString(temp.getLon()));
						labelAlt.setText(Double.toString(temp.getAlt()));
					}
				}
				else JOptionPane.showMessageDialog(null, "Input mac to find his plase");
			}
		});
		btnAlgo.setBounds(163, 101, 70, 25);
		frame.getContentPane().add(btnAlgo);


		btnAlgo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAlgo_1.setBounds(163, 128, 70, 25);
		frame.getContentPane().add(btnAlgo_1);

		textFieldFmac.setColumns(10);
		textFieldFmac.setBounds(260, 102, 116, 22);
		frame.getContentPane().add(textFieldFmac);


		lblMacToFind.setBounds(401, 105, 127, 16);
		frame.getContentPane().add(lblMacToFind);

		labelLat.setBounds(269, 46, 116, 16);
		frame.getContentPane().add(labelLat);


		labelLon.setBounds(405, 46, 116, 16);
		frame.getContentPane().add(labelLon);


		labelAlt.setBounds(551, 46, 116, 16);
		frame.getContentPane().add(labelAlt);


		label.setBounds(404, 26, 56, 16);
		frame.getContentPane().add(label);


		label_1.setBounds(550, 26, 56, 16);
		frame.getContentPane().add(label_1);


		label_2.setBounds(268, 26, 56, 16);
		frame.getContentPane().add(label_2);


		btnSaveFilter.setBounds(272, 8, 140, 37);
		frame.getContentPane().add(btnSaveFilter);



		btnLoadFilter.setBounds(466, 8, 140, 37);
		frame.getContentPane().add(btnLoadFilter);

		
		btnUndoLastFilter.setIcon(new ImageIcon("C:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\src\\gui\\undo.png"));
		btnUndoLastFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(undo.getReal_size()>0)
				{
					if(btnUndoLastFilter.isEnabled()) 
					{
						btnUndoLastFilter.setEnabled(false);
						nt=new Network(undo);
						filterTest=false;
						JOptionPane.showMessageDialog(null, "Reversed last filter");
					}
					else
						JOptionPane.showMessageDialog(null, "Nothing to undo");
				}
				else
					JOptionPane.showMessageDialog(null, "Nothing to undo");

			}
		});
		btnUndoLastFilter.setBounds(513, 307, 160, 39);
		frame.getContentPane().add(btnUndoLastFilter);

		JButton btnSizeOfData = new JButton("Size of data");
		btnSizeOfData.setIcon(new ImageIcon("C:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\src\\gui\\db.png"));
		btnSizeOfData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nt!=null)
					JOptionPane.showMessageDialog(null, "Base "+nt.getReal_size()+" lines");
				else JOptionPane.showMessageDialog(null, "Base is empty");
			}
		});
		btnSizeOfData.setBounds(365, 307, 140, 39);
		frame.getContentPane().add(btnSizeOfData);




		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nt=new Network();
				JOptionPane.showMessageDialog(null, "All cleared");
			}
		});

		mntmNewBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmSaveBase.setEnabled(true);
				nt=new Network();
				if(!btnIo.isVisible()&&!btnFilters.isVisible()&&!btnAlgoritms.isVisible()) {
					btnIo.setVisible(true);
					btnFilters.setVisible(true);
					btnAlgoritms.setVisible(true);
					btnTocsv.setVisible(true);
					btnTokml.setVisible(true);
				}
			}
		});

		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
				fileChooser.showOpenDialog(null);
				if(fileChooser.getSelectedFile()!=null) {
					int size=nt.getReal_size();
					csvBase.check(fileChooser.getSelectedFile(),nt);
					if(size!=nt.getReal_size())
						JOptionPane.showMessageDialog(null, "Imported "+(nt.getReal_size()-size)+" lines");
					else JOptionPane.showMessageDialog(null, "Imported nothing");
				}



			}
		});

		btnOpenFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showOpenDialog(null);
				String folder="";
				if(fileChooser.getSelectedFile()!=null) {
					folder=fileChooser.getSelectedFile().getAbsolutePath();
					Network temp=csvBase.readCSV(folder);
					int size=temp.getReal_size();
					nt.add(temp);
					if(size!=0)
						JOptionPane.showMessageDialog(null, "Imported "+size+" lines");
					else JOptionPane.showMessageDialog(null, "Imported nothing");
				}
			}
		});

		mntmOpenBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmSaveBase.setEnabled(true);
				if(!btnIo.isVisible()&&!btnFilters.isVisible()&&!btnAlgoritms.isVisible()) {
					btnIo.setVisible(true);
					btnFilters.setVisible(true);
					btnAlgoritms.setVisible(true);
					btnTocsv.setVisible(true);
					btnTokml.setVisible(true);
				}
				FileInputStream fis;
				try {
					fis = new FileInputStream("base.out");
					ObjectInputStream oin = new ObjectInputStream(fis);
					nt=(Network)oin.readObject();
					JOptionPane.showMessageDialog(null, "Loaded "+nt.getReal_size()+" lines");
					oin.close();
					fis.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "The file is empty");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		mntmSaveBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!btnIo.isVisible()&&!btnFilters.isVisible()&&!btnAlgoritms.isVisible()) {
					btnIo.setVisible(true);
					btnFilters.setVisible(true);
					btnAlgoritms.setVisible(true);
				}
				FileOutputStream fos;
				if(nt.getReal_size()>0) {
					try {

						fos = new FileOutputStream("base.out");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						JOptionPane.showMessageDialog(null, "Saved "+nt.getReal_size()+" lines in the base.out file.");
						oos.writeObject(nt);
						oos.flush();
						oos.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}
				else JOptionPane.showMessageDialog(null, "The base is empty");
			}
		});

		btnIo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!btnOpenFile.isVisible()&&!btnOpenFolder.isVisible()) {
					btnOpenFile.setVisible(true);
					btnOpenFolder.setVisible(true);
				}
				if(chckbxDate.isVisible()||chckbxID.isVisible()||chckbxRadius.isVisible()||rdbtnAnd.isVisible()||
						rdbtnNot.isVisible()||rdbtnOr.isVisible()||btnAlgo.isVisible())
				{
					chckbxDate.setVisible(false);
					chckbxID.setVisible(false);
					chckbxRadius.setVisible(false);
					rdbtnAnd.setVisible(false);
					rdbtnNot.setVisible(false);
					rdbtnOr.setVisible(false);
					textFieldBefore.setVisible(false);
					textFieldAfter.setVisible(false);
					lblDateBefore.setVisible(false);
					lblDateAfter.setVisible(false);
					lblFormatOfDate.setVisible(false);
					TextFieldID.setVisible(false);
					lblID.setVisible(false);
					lblRadius.setVisible(false);
					lblLon.setVisible(false);
					lblLat.setVisible(false);
					textFieldLon.setVisible(false);
					textFieldLat.setVisible(false);
					textFieldRadius.setVisible(false);
					chckbxDate.setSelected(false);
					chckbxID.setSelected(false);
					chckbxRadius.setSelected(false);
					btnFilter.setVisible(false);
					btnFilter.setEnabled(false);
					btnAlgo.setVisible(false);
					btnAlgo_1.setVisible(false);
					lblMacToFind.setVisible(false);
					textFieldFmac.setVisible(false);
					label.setVisible(false);
					label_1.setVisible(false);
					label_2.setVisible(false);
					labelLat.setVisible(false);
					labelAlt.setVisible(false);
					labelLon.setVisible(false);

					btnSaveFilter.setVisible(false);
					btnLoadFilter.setVisible(false);
				}

			}
		});

		btnAlgoritms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!btnAlgo.isVisible()&&!btnAlgo_1.isVisible())
				{
					btnAlgo.setVisible(true);
					btnAlgo_1.setVisible(true);
					lblMacToFind.setVisible(true);
					textFieldFmac.setVisible(true);
					label.setVisible(true);
					label_1.setVisible(true);
					label_2.setVisible(true);
					labelLat.setVisible(true);
					labelAlt.setVisible(true);
					labelLon.setVisible(true);

				}
				if(chckbxDate.isVisible()||chckbxID.isVisible()||chckbxRadius.isVisible()||rdbtnAnd.isVisible()||
						rdbtnNot.isVisible()||rdbtnOr.isVisible()||btnOpenFile.isVisible()||btnOpenFolder.isVisible())
				{
					chckbxDate.setVisible(false);
					chckbxID.setVisible(false);
					chckbxRadius.setVisible(false);
					rdbtnAnd.setVisible(false);
					rdbtnNot.setVisible(false);
					rdbtnOr.setVisible(false);
					btnOpenFile.setVisible(false);
					btnOpenFolder.setVisible(false);
					textFieldBefore.setVisible(false);
					textFieldAfter.setVisible(false);
					lblDateBefore.setVisible(false);
					lblDateAfter.setVisible(false);
					lblFormatOfDate.setVisible(false);
					TextFieldID.setVisible(false);
					lblID.setVisible(false);
					lblRadius.setVisible(false);
					lblLon.setVisible(false);
					lblLat.setVisible(false);
					textFieldLon.setVisible(false);
					textFieldLat.setVisible(false);
					textFieldRadius.setVisible(false);
					chckbxDate.setSelected(false);
					chckbxID.setSelected(false);
					chckbxRadius.setSelected(false);
					btnFilter.setVisible(false);
					btnFilter.setEnabled(false);

					btnSaveFilter.setVisible(false);
					btnLoadFilter.setVisible(false);



				}
			}
		});


		btnFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnOpenFile.isVisible()||btnOpenFolder.isVisible()||btnAlgo.isVisible()) {
					btnOpenFile.setVisible(false);
					btnOpenFolder.setVisible(false);
					btnAlgo.setVisible(false);
					btnAlgo_1.setVisible(false);
					btnAlgo.setVisible(false);
					btnAlgo_1.setVisible(false);
					lblMacToFind.setVisible(false);
					textFieldFmac.setVisible(false);
					label.setVisible(false);
					label_1.setVisible(false);
					label_2.setVisible(false);
					labelLat.setVisible(false);
					labelAlt.setVisible(false);
					labelLon.setVisible(false);
				}
				if((!chckbxDate.isVisible()&&!chckbxID.isVisible()
						&&!chckbxRadius.isVisible()&&!rdbtnAnd.isVisible()
						&&!rdbtnNot.isVisible()&&!rdbtnOr.isVisible()))
				{
					rdbtnAnd.setVisible(true);
					rdbtnNot.setVisible(true);
					rdbtnOr.setVisible(true);
					chckbxDate.setVisible(true);
					chckbxID.setVisible(true);
					chckbxRadius.setVisible(true);
					btnFilter.setVisible(true);

					btnSaveFilter.setVisible(true);
					btnLoadFilter.setVisible(true);

				}
			}
		});

		chckbxDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxDate.isSelected())
				{
					textFieldBefore.setVisible(true);
					textFieldAfter.setVisible(true);
					lblDateBefore.setVisible(true);
					lblDateAfter.setVisible(true);
					lblFormatOfDate.setVisible(true);
				}
				else
				{
					textFieldBefore.setVisible(false);
					textFieldAfter.setVisible(false);
					lblDateBefore.setVisible(false);
					lblDateAfter.setVisible(false);
					lblFormatOfDate.setVisible(false);
				}

			}
		});

		chckbxRadius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxRadius.isSelected())
				{
					lblRadius.setVisible(true);
					lblLon.setVisible(true);
					lblLat.setVisible(true);
					textFieldLon.setVisible(true);
					textFieldLat.setVisible(true);
					textFieldRadius.setVisible(true);
				}
				else
				{
					lblRadius.setVisible(false);
					lblLon.setVisible(false);
					lblLat.setVisible(false);
					textFieldLon.setVisible(false);
					textFieldLat.setVisible(false);
					textFieldRadius.setVisible(false);
				}
			}
		});

		chckbxID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxID.isSelected())
				{
					TextFieldID.setVisible(true);
					lblID.setVisible(true);
				}
				else
				{
					TextFieldID.setVisible(false);
					lblID.setVisible(false);
					lblRadius.setVisible(false);
					lblLon.setVisible(false);
					lblLat.setVisible(false);
					textFieldLon.setVisible(false);
					textFieldLat.setVisible(false);
					textFieldRadius.setVisible(false);
				}
			}
		});

		btnSaveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				temp.setChckbxDate(chckbxDate);
				temp.setchckbxID(chckbxID);
				temp.setChckbxRadius(chckbxRadius);
				temp.setRdbtnAnd(rdbtnAnd);
				temp.setRdbtnNot(rdbtnNot);
				temp.setRdbtnOr(rdbtnOr);
				temp.setTextFieldAfter(textFieldAfter);
				temp.setTextFieldBefore(textFieldBefore);
				temp.setTextFieldLat(textFieldLat);
				temp.setTextFieldLon(textFieldLon);
				temp.setTextFieldID(TextFieldID);
				temp.setTextFieldRadius(textFieldRadius);
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("filter.out");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(temp);
					oos.flush();
					oos.close();
					JOptionPane.showMessageDialog(null, "Saved filter");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
		btnLoadFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileInputStream fis;
				try {

					fis = new FileInputStream("filter.out");
					ObjectInputStream oin = new ObjectInputStream(fis);
					temp=(Filter)oin.readObject();
					oin.close();
					fis.close();
					rdbtnAnd.setSelected(temp.getRdbtnAnd().isSelected());
					rdbtnOr.setSelected(temp.getRdbtnOr().isSelected());
					rdbtnNot.setSelected(temp.getRdbtnNot().isSelected());
					chckbxDate.setSelected(temp.getChckbxDate().isSelected());
					chckbxRadius.setSelected(temp.getChckbxRadius().isSelected());
					chckbxID.setSelected(temp.getchckbxID().isSelected());
					if(chckbxDate.isSelected()) 
					{
						textFieldBefore.setVisible(true);
						textFieldAfter.setVisible(true);
						lblDateBefore.setVisible(true);
						lblDateAfter.setVisible(true);
						lblFormatOfDate.setVisible(true);
					}
					else
					{
						textFieldBefore.setVisible(false);
						textFieldAfter.setVisible(false);
						lblDateBefore.setVisible(false);
						lblDateAfter.setVisible(false);
						lblFormatOfDate.setVisible(false);
					}
					if(chckbxID.isSelected())
					{
						TextFieldID.setVisible(true);
						lblID.setVisible(true);
					}
					else
					{
						TextFieldID.setVisible(false);
						lblID.setVisible(false);
					}
					if(chckbxRadius.isSelected())
					{
						lblRadius.setVisible(true);
						lblLon.setVisible(true);
						lblLat.setVisible(true);
						textFieldLon.setVisible(true);
						textFieldLat.setVisible(true);
						textFieldRadius.setVisible(true);
					}
					else
					{
						lblRadius.setVisible(false);
						lblLon.setVisible(false);
						lblLat.setVisible(false);
						textFieldLon.setVisible(false);
						textFieldLat.setVisible(false);
						textFieldRadius.setVisible(false);
					}
					if(rdbtnOr.isSelected()||rdbtnAnd.isSelected()||rdbtnNot.isSelected())
					{
						btnFilter.setEnabled(true);
					}
					textFieldBefore.setText(temp.getTextFieldBefore().getText());
					textFieldAfter.setText(temp.getTextFieldAfter().getText());
					TextFieldID.setText(temp.getTextFieldID().getText());
					textFieldRadius.setText(temp.getTextFieldRadius().getText());
					textFieldLat.setText(temp.getTextFieldLat().getText());
					textFieldLon.setText(temp.getTextFieldLon().getText());

					JOptionPane.showMessageDialog(null, "Loaded filter");
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "The file is empty");

				}
			}
		});
	}
}
