package mainProject;

import java.awt.event.*;
import javax.swing.*;

import data.Database;
import inputOutput.SQL;

import java.awt.EventQueue;


public class Menus extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private Database db=new Database();
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	public static String s;
	protected static final Object Button1 = null;
	private JPanel frame;
	private JMenuBar bar;
	private JMenu file;
	private JMenuItem exit;
	private JMenuItem uploadFromCsv;
	private JMenuItem uploadFromWiggle;
	private JMenuItem saveAsCsv;
	private JMenuItem saveAsKml;
	private JMenuItem deleteDB;
	private JMenuItem printRoutersPlaces;
	private JMenuItem getScanPlaceFromString;
	private JMenuItem getScanPlaceFromUserInput;
	private JMenu filter;
	private JMenuItem uploadFilter;
	private JMenuItem placeFilter;
	private JMenuItem IDFilter;
	private JMenuItem TimeFilter;
	private JMenuItem saveFilters;
	private JTextField txtPath;
	private JTextField txtScans;
	private JTextField showFilter;
	private JLabel lblNewLabel;
	protected AbstractButton lblfileName;
	protected String fileName;
	private JMenuItem uploadFromSQL;

	public Menus()
	{
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(104, 145, 46, 14);
		getContentPane().add(lblNewLabel);
		bar = new JMenuBar();
		setJMenuBar(bar);
		txtPath = new JTextField();
		txtPath.setBounds(100, 58, 86, 20);
		getContentPane().add(txtPath);
		txtPath.setColumns(10);

		txtScans = new JTextField();
		txtScans.setBounds(100, 100, 86, 20);
		getContentPane().add(txtScans);
		txtScans.setColumns(10);
		showFilter = new JTextField();
		showFilter.setBounds(100, 150, 600, 20);
		getContentPane().add(showFilter);
		showFilter.setColumns(10);
		Runnable updateDisplay = () -> {
			db.updateGui(txtPath, txtScans, showFilter);
		};
		Thread t1 = new Thread(updateDisplay);
		Runnable updateCSVChange = () -> {
			db.CSVdataChanged();
		};
		Thread t2 = new Thread(updateCSVChange);
		Runnable updateWiggleChange = () -> {
			db.WiggledataChanged();
		};
		Thread t3 = new Thread(updateWiggleChange);
		Runnable updateSQLChange = () -> {
			db.SQLdataChanged();
		};
		Thread t4 = new Thread(updateSQLChange);
		Runnable output = () -> {
			jtextPrint.printToGui();
		};
		Thread t5 = new Thread(output);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		file = new JMenu("File");
		bar.add(file);
		uploadFromSQL = new JMenuItem("upload from sql table");
		uploadFromSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = JOptionPane.showInputDialog("ip");
				int port;
				while(true){
					try{
						port = Integer.parseInt(JOptionPane.showInputDialog("port"));
						break;
					}catch(Exception ex){

					}
				}
				String user = JOptionPane.showInputDialog("user");
				String password = JOptionPane.showInputDialog("password");
				String table_schema = JOptionPane.showInputDialog("table_schema");
				String table = JOptionPane.showInputDialog("table");
				SQL info= new SQL(ip, port, user, password, table_schema, table);
				db.editSQL(info);
			}
		});
		file.add(uploadFromSQL);
		uploadFromCsv = new JMenuItem("upload from csv file");
		uploadFromCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=JOptionPane.showInputDialog("csv file");
				db.editCsv(s);
			}
		});
		file.add(uploadFromCsv);

		uploadFromWiggle = new JMenuItem("upload from directory with wiggle");
		uploadFromWiggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=JOptionPane.showInputDialog("directory");
				db.editWiggle(s);
			}
		});
		file.add(uploadFromWiggle);

		saveAsCsv = new JMenuItem("save to csv format");
		saveAsCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=JOptionPane.showInputDialog("save as CSV to (path)");
				db.saveAsCSV(s);
			}
		});
		file.add(saveAsCsv);

		saveAsKml = new JMenuItem("save to Kml format");
		saveAsKml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String s=JOptionPane.showInputDialog("save as KML to (path)");
				db.saveAsKML(s);
			}
		});
		file.add(saveAsKml);

		deleteDB = new JMenuItem("delete data base");
		deleteDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				db.deleteDatabase();
			}
		});
		file.add(deleteDB);

		printRoutersPlaces = new JMenuItem("print Routers Places");
		printRoutersPlaces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db.printRoutersPlaces();
			}
		});
		file.add(printRoutersPlaces);

		getScanPlaceFromString = new JMenuItem("getScanPlaceFromString");
		getScanPlaceFromString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String withoutCoordinates=JOptionPane.showInputDialog("press path without coordinates");
				db.getScanPlaceFromString(withoutCoordinates);
			}
		});
		file.add(getScanPlaceFromString);

		getScanPlaceFromUserInput = new JMenuItem("getScanPlaceFromUserInput");
		getScanPlaceFromUserInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Mac1=JOptionPane.showInputDialog("press mac1");
				String RSSI1=JOptionPane.showInputDialog("press RSSI1");
				String Mac2=JOptionPane.showInputDialog("press mac2");
				String RSSI2=JOptionPane.showInputDialog("press RSSI2");
				String Mac3=JOptionPane.showInputDialog("press mac3");
				String RSSI3=JOptionPane.showInputDialog("press RSSI3");
				db.getScanPlaceFromUserInput(Mac1, RSSI1, Mac2, RSSI2, Mac3, RSSI3);
			}
		});
		file.add(getScanPlaceFromString);

		exit = new JMenuItem("exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		file.add(exit);

		filter = new JMenu("Filter");
		bar.add(filter);

		uploadFilter = new JMenuItem("uploadFilter");
		uploadFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path=JOptionPane.showInputDialog("Please press path");
				db.uploadFilter(path);
			}
		});
		filter.add(uploadFilter);

		IDFilter = new JMenuItem("filter by ID");
		IDFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=JOptionPane.showInputDialog("Please choose id to filter");
				while(true){
					String s=JOptionPane.showInputDialog("do you want just that id or don't want it? y/n");
					if(s.equals("y")){
						if(db.getFilter()==null||db.getFilter().equals("")) {
							db.deviceFilter(id);
						}
						else{
							while(true){
								String t=JOptionPane.showInputDialog("the current filter came to Comes to add to existing? (or subtract)  y/n");
								if(t.equals("y")){
									db.or_deviceFilter(id);	
									break;
								}
								if(t.equals("n")){
									db.and_deviceFilter(id);	
									break;
								}
							}
						}
						break;
					}
					if(s.equals("n")){
						if(db.getFilter()==null||db.getFilter().equals("")) {
							db.not_deviceFilter(id);
						}
						else{
							while(true){
								String t=JOptionPane.showInputDialog("the current filter came to Comes to add to existing? (or subtract)  y/n");
								if(t.equals("y")){
									db.or_not_DeviceFilter(id);	
									break;
								}
								if(t.equals("n")){
									db.and_not_deviceFilter(id);	
									break;
								}
							}
						}
						break;
					}
				}
			}
		});
		filter.add(IDFilter);

		placeFilter = new JMenuItem("filter by place");
		placeFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String minLon=JOptionPane.showInputDialog("Please press minimum of lon");
				String maxLon=JOptionPane.showInputDialog("Please press maximum of lon");
				String minLat=JOptionPane.showInputDialog("Please press minimum of lat");
				String maxLat=JOptionPane.showInputDialog("Please press maximum of lat");
				if(db.getFilter()==null||db.getFilter().equals("")) {
					db.coordinateFilter(minLon, maxLon, minLat, maxLat);
				}
				else {
					while(true){
						String t=JOptionPane.showInputDialog("the current filter came to Comes to add to existing? (or subtract)  y/n");
						if(t.equals("y")){
							db.or_coordinateFilter(minLon, maxLon, minLat, maxLat);
							break;
						}
						if(t.equals("n")){
							db.and_coordinateFilter(minLon, maxLon, minLat, maxLat);
							break;
						}
					}

				}
			}

		});
		filter.add(placeFilter);

		TimeFilter = new JMenuItem("filter by time");
		TimeFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String begin=JOptionPane.showInputDialog("Please choose the begin time");
				String end=JOptionPane.showInputDialog("Please choose the end time");
				while(true){
					String s=JOptionPane.showInputDialog("do you want just that time or don't want it? y/n");
					if(s.equals("y")){
						if(db.getFilter()==null||db.getFilter().equals("")) {
							db.timeFilter(begin, end);
						}
						else{
							while(true){
								String t=JOptionPane.showInputDialog("the current filter came to Comes to add to existing? (or subtract)  y/n");
								if(t.equals("y")){
									db.or_timeFilter(begin, end);
									break;
								}
								if(t.equals("n")){
									db.and_timeFilter(begin, end);
									break;
								}
							}
						}
						break;
					}
					if(s.equals("n")){
						if(db.getFilter()==null||db.getFilter().equals("")) {
							db.not_timeFilter(begin, end);
						}
						else{
							while(true){
								String t=JOptionPane.showInputDialog("the current filter came to Comes to add to existing? (or subtract)  y/n");
								if(t.equals("y")){
									db.or_not_timeFilter(begin, end);
									break;
								}
								if(t.equals("n")){
									db.and_not_timeFilter(begin, end);
									break;
								}
							}
						}
						break;
					}

				}
			}
		});
		filter.add(TimeFilter);

		saveFilters = new JMenuItem("save the filters");
		saveFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				db.saveFilter(s);
			}
		});
		filter.add(saveFilters);

		getContentPane().setLayout(null);

		JLabel lblNumruoter = new JLabel("num of routers");
		lblNumruoter.setBounds(10, 58, 86, 20);
		getContentPane().add(lblNumruoter);

		JLabel lblScans = new JLabel("num of scans");
		lblScans.setBounds(10, 100, 86, 20);
		getContentPane().add(lblScans);

		JLabel lblFilter = new JLabel("filter");
		lblFilter.setBounds(10, 150, 86, 20);
		getContentPane().add(lblFilter);

		initialize();
	}

	public static void set(String str)
	{
		s=str;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menus window = new Menus();
					window.setVisible(true);
					window.resize(550, 450);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JPanel();
		frame.setBounds(100,100, 450, 300);
	}
	public String mains(String args){
		System.out.println(headerLabel+"   "+statusLabel+"  "+controlPanel );    

		return s;
	}

}


