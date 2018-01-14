package mainProject;
 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
 
/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaMyFrame extends JFrame 
    implements ActionListener{
	public JavaMyFrame() {
	}
 
    JTextArea textArea;
    JButton buttonOpenFile;
    public static String s;
    public static void main(String[] args) {
         
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    public static String gets()
    {
    	return s;
    }
 
    private static void createAndShowGUI() {
        JavaMyFrame myFrame = new JavaMyFrame();
 
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        myFrame.prepareUI();
 
        myFrame.pack();
        myFrame.setVisible(true);
    }
     
    private void prepareUI(){
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane panel = new JScrollPane(textArea);
        panel.setPreferredSize(new Dimension(300, 100));
         
        buttonOpenFile = new JButton("Open File");
        buttonOpenFile.addActionListener(this);
         
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonOpenFile, BorderLayout.PAGE_END);
    }
 public static void set() {
	Menus.set(gets());
 }
    @Override
    public void actionPerformed(ActionEvent e) {
         
        if(e.getSource() == buttonOpenFile){
            final JFileChooser jFileChooser = new JFileChooser();
            int returnVal = jFileChooser.showOpenDialog(JavaMyFrame.this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = jFileChooser.getSelectedFile();
                textArea.setText("Selected file: " + file.getName());
                s=file.getName();
                set();
            }else if(returnVal == JFileChooser.CANCEL_OPTION){
                textArea.setText("Cancelled");
            }else if(returnVal == JFileChooser.ERROR_OPTION){
                textArea.setText("Error!");
            }else{
                textArea.setText("unknown...");
            }
        }
    }
}
