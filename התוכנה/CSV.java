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
public class CSV {
	private static final Exception Exception = null;


	/** 
	 *  gets vector of the info and PrintWriter to wifi.csv (format)
	 *  printing into the new csv file all the data
	 * 
	 * @param info-
	 * @param outs-
	 */
	public static void printFileFromDataBaseToCSV(Vector<sameScanWifi> dataBase,String path){
		FileWriter fw;             // statement
		try {                 //   try write the file 
			fw = new FileWriter(path+"\\wifi.csv");
			PrintWriter outs = new PrintWriter(fw);
			outs.println("Time, ID, Lat, Lon, Alt, #WiFi networks (up to 10), SSID1, MAC1, Frequncy1, Signal1,SSID2, MAC2, Frequncy2, Signal2, SSID3, MAC3, Frequncy3, Signal3,SSID4, MAC4, Frequncy4, Signal4, SSID5, MAC5, Frequncy5, Signal5,SSID6, MAC6, Frequncy6, Signal6, SSID7, MAC7, Frequncy7, Signal7,SSID8, MAC8, Frequncy8, Signal8, SSID9, MAC9, Frequncy9, Signal9,SSID10, MAC10, Frequncy10, Signal10");
			for(int i=0;i<dataBase.size();i++){
				outs.println(dataBase.elementAt(i).toStringForCsv());
			}

			fw.close();      // close fw writer
			outs.close();     //close outs writer
		} catch (IOException e) {     // exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * collect the info into Vector<sameScanWifi> with the filter
	 * @param path of csv file that our function made
	 * @return data base from it
	 * @throws Exception if the csv file is'nt good
	 */
	public static Vector<sameScanWifi> collectInfoFromCSV(String path) throws Exception{
		Vector<sameScanWifi> dataBase=new Vector<sameScanWifi>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str;
		str = br.readLine();
		if (!str.equals("Time, ID, Lat, Lon, Alt, #WiFi networks (up to 10), SSID1, MAC1, Frequncy1, Signal1,SSID2, MAC2, Frequncy2, Signal2, SSID3, MAC3, Frequncy3, Signal3,SSID4, MAC4, Frequncy4, Signal4, SSID5, MAC5, Frequncy5, Signal5,SSID6, MAC6, Frequncy6, Signal6, SSID7, MAC7, Frequncy7, Signal7,SSID8, MAC8, Frequncy8, Signal8, SSID9, MAC9, Frequncy9, Signal9,SSID10, MAC10, Frequncy10, Signal10" )){
			br.close();
			throw Exception;
		}
		for (int i=1;i<genericFunctions.countLines(path);i++){
			sameScanWifi tempSameScanWifi=new sameScanWifi();
			String[] parts = br.readLine().split(",");
			tempSameScanWifi.setAltitude(parts[4]);
			tempSameScanWifi.setID(parts[1]);
			tempSameScanWifi.setLatitude(parts[2]);
			tempSameScanWifi.setLongitude(parts[3]);
			tempSameScanWifi.setTime(parts[0]);
			for(int j=6;j<Integer.parseInt(parts[5])*4+6;j+=4){
				wifi tempWifi=new wifi();
				tempWifi.setChannel(parts[j+2]);
				tempWifi.setMAC(parts[j+1]);
				tempWifi.setRSSI(parts[j+3]);
				tempWifi.setSSID(parts[j]);
				tempSameScanWifi.insert(tempWifi);
			}
			dataBase.add(tempSameScanWifi);
		}
		
		br.close();
		return dataBase;
	}
	
}
