import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class genericFunctions {
	/**
	 * from https://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java 
	 * Gets path of a file.  
	 * Return the number of lines in it.
	 * we changed the origin code that will work even when their is no enter in the end of the file
	 * @param filename
	 * @return 1 or number of lines
	 * @throws IOException
	 */
	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {                 // try
			byte[] c = new byte[1024];
			int count = 1;
			int readChars = 0;
			boolean empty = true;
			byte last='1';
			while ((readChars = is.read(c)) != -1) {
				if(last=='\n'){
					++count;
				}
				last=c[readChars-1];
				empty = false;
				for (int i = 0; i < readChars-1; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			is.close();
			return (count == 0 && !empty) ? 1 : count;
		} catch(IOException e) {
			is.close();
			return 0;

		}
	}
	/**
	 * add all the sameScanWifi that in tempSameScanWifiVector into  wifis
	 * if their was an ellement from the same scan it units them,
	 *  else it add it to the end of the vector
	 * @param wifis
	 * @param tempSameScanWifiVector
	 */
	public static void unit(Vector<sameScanWifi> wifis, Vector<sameScanWifi> tempSameScanWifiVector) {
		// TODO Auto-generated method stub
		for(int i=0;i<tempSameScanWifiVector.size();i++){
			boolean add=false;
			for(int j=0;j<wifis.size();j++){
				if(tempSameScanWifiVector.elementAt(i).compare(wifis.elementAt(j))){
					wifis.elementAt(j).insert(tempSameScanWifiVector.elementAt(i));
					add=true;
					break;
				}
			}
			if(!add){
				wifis.add(tempSameScanWifiVector.elementAt(i));
			}
		}
	}
}
