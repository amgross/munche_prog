import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class gui {





	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public gui(){
		prepareGUI();
	}
	public static void main(String[] args){
		gui  swingControlDemo = new gui();      
		swingControlDemo.showFileChooserDemo();
	}
	private void prepareGUI(){
		mainFrame = new JFrame("Java Swing Examples");
		mainFrame.setSize(400,400);
		mainFrame.setLayout(new GridLayout(3, 1));

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    
		headerLabel = new JLabel("", JLabel.CENTER);        
		statusLabel = new JLabel("",JLabel.CENTER);    
		statusLabel.setSize(350,100);

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);  
	}
	//from https://www.tutorialspoint.com/swing/swing_jfilechooser.htm
	private void showFileChooserDemo(){
		headerLabel.setText("Control in action: JFileChooser"); 
		final JFileChooser  folderDialog = new JFileChooser();
		JButton showFoldeeDialogButton = new JButton("Open Folder");
		showFoldeeDialogButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//from https://stackoverflow.com/questions/10083447/selecting-folder-destination-in-java
				// TODO Auto-generated method stub
				folderDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				folderDialog.showSaveDialog(null);
				System.out.println(folderDialog.getSelectedFile().toString());
			}

		});
		final JFileChooser  fileDialog = new JFileChooser();
		JButton showFileDialogButton = new JButton("Open File");

		showFileDialogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialog.showOpenDialog(mainFrame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					java.io.File file = fileDialog.getSelectedFile();
					statusLabel.setText("File Selected :" + file.getPath());
				} else {
					statusLabel.setText("Open command cancelled by user." );           
				}      
			}
		});

		controlPanel.add(showFileDialogButton);
		controlPanel.add(showFoldeeDialogButton);
		mainFrame.setVisible(true);  


	}
}
