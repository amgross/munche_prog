import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
/**
 * 
 *       
 * @author arye gross and avi wasserberger
 */
/**
 * This program reads csv files in a given directory and builds a CSV file with with the
 * most up to 10 WiFi strongest available points in the following format:
 *   Time, ID, Latitude, Longitude, Altitude, #Wifi_network, SSID1, MAC1, Frequency1, Signal1, ...
 * Gets path of folder. 
 * Print a file name wifi.csv to the given path, with the information 
 * collected in this application
 * 
 * @param path-
 */
public class toCsv {
	public static void writeCsv(String path){
		FileWriter fw;             // statement
		try {                 //   try write the file 
			fw = new FileWriter(path+"\\wifi.csv");
			PrintWriter outs = new PrintWriter(fw);
			String[] pathes=whichFiles(path);
			Vector<sameScanWifi> wifiInfo=collectInfo(pathes);
			printFile(wifiInfo,outs);
			fw.close();      // close fw writer
			outs.close();     //close outs writer
		} catch (IOException e) {     // exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	/**
	 * Gets path of folder.
	 * Returns array of path names of relevant files in the folder.
	 * @param path- A path name
	 * @return array of path names to the relevant files.   
	 */
	private static String[] whichFiles(String path){
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		String[] pathes=new String[listOfFiles.length];
		int counter=0;
		for (int i=0;i<listOfFiles.length;i++) {
			File file=listOfFiles[i];
			if (file.getName().endsWith(".csv")&&file.isFile()&&!file.getName().equals("wifi.csv")) {
				pathes[counter] = file.getPath();
				counter++;   // another relevant file 
			}
		}
		String[] finalPathes=new String[counter];
		for(int i=0;i<counter;i++){
			finalPathes[i]=pathes[i];
		}
		return finalPathes;
	}
	/** 
	 *  gets array[][] of the info and PrintWriter to wifi.csv (format)
	 *  printing into the new csv file all the data
	 * 
	 * @param info-
	 * @param outs-
	 */
	private static void printFile(Vector<sameScanWifi> wifis,PrintWriter outs){

		outs.println("Time, ID, Lat, Lon, Alt, #WiFi networks (up to 10), SSID1, MAC1, Frequncy1, Signal1,SSID2, MAC2, Frequncy2, Signal2, SSID3, MAC3, Frequncy3, Signal3,SSID4, MAC4, Frequncy4, Signal4, SSID5, MAC5, Frequncy5, Signal5,SSID6, MAC6, Frequncy6, Signal6, SSID7, MAC7, Frequncy7, Signal7,SSID8, MAC8, Frequncy8, Signal8, SSID9, MAC9, Frequncy9, Signal9,SSID10, MAC10, Frequncy10, Signal10");
		for(int i=0;i<wifis.size();i++){
			outs.println(wifis.elementAt(i).toStringForCsv());
		}
	}
	
	/**
	 * Get an array of the relevant path names. 
	 * Return the relevant info from the files in String array[][], The order is:
	 * 0 MAC,1 SSID,2 Time,3 Channel,4 RSSI,5 Latitude,6 Longitude,7 Altitude,8 Type,9 ID
	 * @param pathes-
	 * @return info-matrix of parameters 
	 */
	private static Vector<sameScanWifi> collectInfo(String[] pathes){
		int [] num_of_lines=new int[pathes.length];

		for(int i=0;i<pathes.length;i++){
			try {
				num_of_lines[i]=genericFunctions.countLines(pathes[i])-2;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Vector<sameScanWifi> wifis=new Vector<sameScanWifi>();
		for(int i=0;i<pathes.length;i++){
			try {
				int j;
				FileReader fr = new FileReader(pathes[i]);
				BufferedReader br = new BufferedReader(fr);
				Scanner scanner = new Scanner(br.readLine());
				Scanner sc=scanner.useDelimiter("\\s*,\\s*");
				sc.next();sc.next();sc.next();sc.next();sc.next();
				String display=sc.next();
				display=display.substring(display.lastIndexOf('=') + 1);
				br.readLine();
				Vector<sameScanWifi> tempSameScanWifiVector=new Vector<sameScanWifi>();
				for(j=0 ; j<num_of_lines[i] ; j++){
					sameScanWifi tempSameScanWifi=new sameScanWifi();
					wifi tempWifi=new wifi();
					scanner = new Scanner(br.readLine());
					sc=scanner.useDelimiter("\\s*,\\s*");
					tempWifi.setMAC(sc.next());
					tempWifi.setSSID(sc.next());
					sc.next();
					tempSameScanWifi.setTime(sc.next());
					tempWifi.setChannel(sc.next());
					tempWifi.setRSSI(sc.next());

					tempSameScanWifi.setLatitude(sc.next());
					tempSameScanWifi.setLongitude(sc.next());
					tempSameScanWifi.setAltitude(sc.next());
					sc.next();
					if(!sc.next().equals("WIFI")){
						continue;
					}
					tempSameScanWifi.setID(display);
					tempSameScanWifi.insert(tempWifi);
					if(tempSameScanWifiVector.size()>0&&tempSameScanWifi.compare(tempSameScanWifiVector.lastElement())){
						tempSameScanWifiVector.lastElement().insert(tempSameScanWifi);

					}
					else{
						tempSameScanWifiVector.add(tempSameScanWifi);
					}
				}
				unit(wifis,tempSameScanWifiVector);
				scanner.close();
				br.close();
			} catch (Exception e) {               // if their is a problem with the csv file, it won't convert to the new csv file
				// TODO Auto-generated catch block
			}
		}
		return wifis;

	}
	private static void unit(Vector<sameScanWifi> wifis, Vector<sameScanWifi> tempSameScanWifiVector) {
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
