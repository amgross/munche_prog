package first;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.desktop.OpenFilesEvent;
import java.awt.desktop.OpenFilesHandler;

import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

public class Menus extends JFrame 
	{
	public static String s;
	protected static final Object Button1 = null;
	final JFileChooser fc = new JFileChooser();
	private JFrame frame;
	private JMenuBar bar;
	private JMenu file;
	private JMenuItem exit;
    private JMenuItem openFile;
	private JMenuItem openDir;
	private JMenuItem saveCsv;
	private JMenuItem saveKml;
	private JMenuItem deleteDB;
	
	private JMenu filter;
	private JMenuItem noFilter;
    private JMenuItem placeFilter;
	private JMenuItem IDFilter;
	private JMenuItem TimeFilter;
	private JTextField txtPath;
	private JLabel lblNewLabel;
	protected AbstractButton lblfileName;
	protected String fileName;

	public Menus()
	{
		 Scanner sc = new Scanner(System.in);
		// int i;
		 lblNewLabel = new JLabel("");
		 lblNewLabel.setBounds(104, 145, 46, 14);
		getContentPane().add(lblNewLabel);
		bar = new JMenuBar();
		setJMenuBar(bar);
		
		file = new JMenu("File");
		bar.add(file);
		final JFileChooser fc = new JFileChooser();

		
		openFile = new JMenuItem("open File...");
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		String[] args = null;
				jfilechooser_javatutorial.JavaMyFrame.main(args);
				System.out.println("hh");
				//txtPath.setText(sc.next());
				String s=JOptionPane.showInputDialog("path");
				//lblNewLabel = new JLabel(s);

				lblNewLabel.setBounds(104, 145, 46, 14);
				while(s==null);
				//System.out.println(s);
				getContentPane().add(lblNewLabel);
			}
		});
		file.add(openFile);
		
		openDir = new JMenuItem("open directory...");
		file.add(openDir);
		
		saveCsv = new JMenuItem("save to csv format...");
		file.add(saveCsv);
		
		saveKml = new JMenuItem("save to Kml...");
		file.add(saveKml);
		
		deleteDB = new JMenuItem("delete data base...");
		file.add(deleteDB);
		
		exit = new JMenuItem("exit");
		file.add(exit);
		
		
	//	bar = new JMenuBar();
		//setJMenuBar(bar);
		
		filter = new JMenu("Filter");
		bar.add(filter);
		
		noFilter = new JMenuItem("no filter");
		filter.add(noFilter);
		
		IDFilter = new JMenuItem("filter by ID...");
		filter.add(IDFilter);
		
		placeFilter = new JMenuItem("filter by place");
		filter.add(placeFilter);
		
		TimeFilter = new JMenuItem("filter by time");
		filter.add(TimeFilter);
		
		
		getContentPane().setLayout(null);
		
		txtPath = new JTextField();
		txtPath.setText("path");
		txtPath.setBounds(10, 58, 86, 20);
		getContentPane().add(txtPath);
		txtPath.setColumns(10);
		
		JLabel lblHhhh = new JLabel("hhhh");
		lblHhhh.setToolTipText("nn");
		lblHhhh.setBounds(74, 42, 76, 82);
		getContentPane().add(lblHhhh);
		
		
		
		/*
		JButton btnButton = new JButton("button1");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (e.getSource() == Button1) {
				        int returnVal = fc.showOpenDialog(FileChooserDemo.this);

				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				            File file = fc.getSelectedFile();
				            //This is where a real application would open the file.
				            log.append("Opening: " + file.getName() + "." + newline);
				        } else {
				            log.append("Open command cancelled by user." + newline);
				        }
				   }
			}
		});
		btnButton.setBounds(10, 11, 89, 23);
		getContentPane().add(btnButton);*/
		
		//frame.add(bar);
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	/*public Menus() {
		initialize();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100,100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
}

