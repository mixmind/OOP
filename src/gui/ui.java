package gui;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import DataBase.csvBase;
import Filter.FilterDate;
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
import javax.swing.event.MenuKeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Button;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class ui {

	private JFrame frame;
	private Network nt;
	JFileChooser fileChooser = new JFileChooser();
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private JTextField textFieldBefore;
	private JTextField textFieldAfter;
	private JTextField textFieldMac;
	private JTextField textFieldRadius;
	private JTextField textFieldLat;
	private JTextField textFieldLon;
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

	/**s
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 699, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		JMenuBar menuBar = new JMenuBar();
		JMenu mnInputoutput = new JMenu("File");
		JMenuItem mntmNewBase = new JMenuItem("New Base");
		JMenuItem mntmSaveBase = new JMenuItem("Save Base");
		JMenuItem mntmOpenBase = new JMenuItem("Open Base");

		JButton btnClear = new JButton("Clear");
		JButton btnAlgoritms = new JButton("Algoritms");
		JButton btnOpenFile = new JButton("Load file");
		JButton btnOpenFolder = new JButton("Load folder");
		JButton btnIo = new JButton("I/O");
		JButton btnFilters = new JButton("Filters");
		JButton btnFilter = new JButton("Filter");

		JCheckBox chckbxDate = new JCheckBox("Date");
		JCheckBox chckbxRadius = new JCheckBox("Radius");
		JCheckBox chckbxMac = new JCheckBox("Mac");

		JLabel lblDateBefore = new JLabel("Date before");
		JLabel lblDateAfter = new JLabel("Date after");
		JLabel lblMac = new JLabel("Mac");
		JLabel lblRadius = new JLabel("Radius");
		JLabel lblLat = new JLabel("Lat");
		JLabel lblLon = new JLabel("Lon");
		JLabel lblFormatOfDate = new JLabel("Format of Date:yyyy-MM-dd HH:mm");

		textFieldMac = new JTextField();
		textFieldRadius = new JTextField();
		textFieldLat = new JTextField();
		textFieldLon = new JTextField();
		textFieldBefore = new JTextField();
		textFieldAfter = new JTextField();

		btnClear.setBounds(526, 359, 155, 37);
		frame.getContentPane().add(btnClear);

		menuBar.setBounds(0, 0, 31, 45);
		frame.getContentPane().add(menuBar);
		menuBar.add(mnInputoutput);
		btnOpenFile.setBounds(270, 8, 155, 37);
		btnOpenFolder.setBounds(447, 8, 176, 37);
		btnIo.setBounds(0, 69, 99, 37);
		btnFilters.setBounds(0, 122, 99, 37);
		btnAlgoritms.setBounds(0, 183, 131, 37);

		frame.getContentPane().add(lblMac);


		textFieldMac.setBounds(360, 147, 206, 35);
		frame.getContentPane().add(textFieldMac);
		textFieldMac.setColumns(10);

		lblMac.setBounds(306, 150, 49, 29);
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

		lblFormatOfDate.setBounds(260, 111, 389, 29);
		frame.getContentPane().add(lblFormatOfDate);
		frame.getContentPane().add(btnFilters);
		frame.getContentPane().add(chckbxDate);
		frame.getContentPane().add(chckbxRadius);
		frame.getContentPane().add(chckbxMac);

		chckbxRadius.setBounds(163, 187, 109, 37);
		chckbxDate.setBounds(163, 65, 89, 37);

		chckbxMac.setBounds(163, 146, 89, 37);
		lblDateBefore.setBackground(Color.DARK_GRAY);
		lblDateBefore.setBounds(323, 40, 127, 29);
		frame.getContentPane().add(lblDateBefore);

		lblDateAfter.setBounds(491, 40, 109, 29);
		frame.getContentPane().add(lblDateAfter);

		btnFilter.setBounds(239, 316, 61, 52);
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

		lblDateBefore.setVisible(false);
		lblDateAfter.setVisible(false);
		lblMac.setVisible(false);
		lblLat.setVisible(false);
		lblLon.setVisible(false);
		lblFormatOfDate.setVisible(false);
		lblRadius.setVisible(false);

		textFieldMac.setVisible(false);
		textFieldRadius.setVisible(false);
		textFieldLat.setVisible(false);
		textFieldLon.setVisible(false);
		textFieldBefore.setVisible(false);
		textFieldAfter.setVisible(false);

		chckbxRadius.setVisible(false);
		chckbxDate.setVisible(false);
		chckbxMac.setVisible(false);

		mntmSaveBase.setEnabled(false);

		mnInputoutput.add(mntmNewBase);
		mnInputoutput.add(mntmOpenBase);
		mnInputoutput.add(mntmSaveBase);
		
		JRadioButton rdbtnAnd = new JRadioButton("and");
		rdbtnAnd.setBounds(163, 303, 54, 25);
		frame.getContentPane().add(rdbtnAnd);
		
		JRadioButton rdbtnOr = new JRadioButton("or");
		rdbtnOr.setBounds(163, 357, 54, 25);
		frame.getContentPane().add(rdbtnOr);
		
		JRadioButton rdbtnNot = new JRadioButton("not");
		rdbtnNot.setBounds(163, 330, 54, 25);
		frame.getContentPane().add(rdbtnNot);
		
		
		
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
				}
			}
		});

		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
				fileChooser.showOpenDialog(null);
				if(fileChooser.getSelectedFile()!=null) {
					csvBase.check(fileChooser.getSelectedFile(),nt);
					JOptionPane.showMessageDialog(null, "Imported "+nt.getReal_size()+" lines");
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
					nt=csvBase.readCSV(folder);
					JOptionPane.showMessageDialog(null, "Imported "+nt.getReal_size()+" lines");
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
				}
				fileChooser.setFileFilter(new FileNameExtensionFilter("Saved base *.out", "out"));
				fileChooser.showOpenDialog(null);
				FileInputStream fis;
				try {

					if(fileChooser.getSelectedFile()!=null)
					{
						fis = new FileInputStream(fileChooser.getSelectedFile());
							ObjectInputStream oin = new ObjectInputStream(fis);
							nt=(Network)oin.readObject();
							JOptionPane.showMessageDialog(null, "Loaded "+nt.getReal_size()+" lines");
							oin.close();
							fis.close();
						
						
					}
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

						fos = new FileOutputStream("temp.out");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						JOptionPane.showMessageDialog(null, "Saved "+nt.getReal_size()+" lines in the temp.out file.");
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
					btnOpenFile.setVisible(!btnOpenFile.isVisible());
					btnOpenFolder.setVisible(!btnOpenFolder.isVisible());
				}
				if(chckbxDate.isVisible()&&chckbxMac.isVisible()&&chckbxRadius.isVisible())
				{
					chckbxDate.setVisible(false);
					chckbxMac.setVisible(false);
					chckbxRadius.setVisible(false);
				}

			}
		});

		btnAlgoritms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});


		btnFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnOpenFile.isVisible()&&btnOpenFolder.isVisible()) {
					btnOpenFile.setVisible(!btnOpenFile.isVisible());
					btnOpenFolder.setVisible(!btnOpenFolder.isVisible());
				}
				if(!chckbxDate.isVisible()&&!chckbxMac.isVisible()&&!chckbxRadius.isVisible())
				{
					chckbxDate.setVisible(true);
					chckbxMac.setVisible(true);
					chckbxRadius.setVisible(true);
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
				if(chckbxDate.isSelected())
				{
					if(!textFieldBefore.getText().isEmpty()&&!textFieldAfter.getText().isEmpty())
					{
						try {
							FilterDate date=new FilterDate(format.parse(textFieldBefore.getText()), format.parse(textFieldAfter.getText()));
							nt=date.runOn(nt);
							JOptionPane.showMessageDialog(null, "The Base was filtered with Date");
							
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
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

		chckbxMac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxMac.isSelected())
				{
					textFieldMac.setVisible(true);
					lblMac.setVisible(true);
				}
				else
				{
					textFieldMac.setVisible(false);
					lblMac.setVisible(false);
				}
			}
		});
		

	}
}
