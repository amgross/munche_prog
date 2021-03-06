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

	/** 
	 *  print File From Data Base of vector of samaScanWifi object To CSV
	 *  gets vector of the info and PrintWriter to csv (format)
	 *  printing into the new csv file all the data
	 * 
	 * @param info-
	 * @param outs-
	 */
	public static void printFileFromDataBaseToCSV(Vector<sameScanWifi> dataBase,String path){
		FileWriter fw;             // statement
		try {                 //   try write the file 
			fw = new FileWriter(path);
			PrintWriter outs = new PrintWriter(fw);
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
	 * print File From data base of WifiWithCoordinate To CSV format
	 * @param dataBase of Vector<wifiWithCoordinate>
	 * @param path to print all the data from th database in it
	 */
	public static void printFileFromWifiWithCoordinateToCSV(Vector<wifiWithCoordinate> dataBase,String path){
		FileWriter fw;             // statement
		try {                 //   try write the file 
			fw = new FileWriter(path);
			PrintWriter outs = new PrintWriter(fw);
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
	 * collect the info into Vector<sameScanWifi> 
	 * @param path of csv file that our function made
	 * @return data base from it
	 * @throws Exception if the csv file or path is'nt good
	 */
	public static Vector<sameScanWifi> collectInfoFromCSV(String path) throws Exception{
		Vector<sameScanWifi> dataBase=new Vector<sameScanWifi>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		for (int i=0;i<genericFunctions.countLines(path);i++){
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
	/**
	 * print File From vocter of WifiWithCoordinate object To CSV format
	 * @param writing_path to print in this folder the places of the routers
	 * @param realPlaces vector of wifiWithCoordinate 
	 * print the routers with their coordinates into csv file
	 */
	public static void printFileFromWifiWithCoordinateToCSV(String writing_path, Vector<wifiWithCoordinate> realPlaces) {
		// TODO Auto-generated method stub
		FileWriter fw;             // statement
		try {                 //   try write the file 
			fw = new FileWriter(writing_path+"\\wifi.csv");
			PrintWriter outs = new PrintWriter(fw);
			for(int i=0;i<realPlaces.size();i++){
				outs.println(realPlaces.elementAt(i).toStringForCsv());
			}

			fw.close();      // close fw writer
			outs.close();     //close outs writer
		} catch (IOException e) {     // exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
