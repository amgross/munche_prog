import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
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
			wifi[] wifiInfo=collectInfo(pathes);
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
	private static void printFile(wifi[] wifis,PrintWriter outs){
		outs.print("Time, ID, Lat, Lon, Alt, #WiFi networks (up to 10), SSID1, MAC1, Frequncy1, Signal1,SSID2, MAC2, Frequncy2, Signal2, SSID3, MAC3, Frequncy3, Signal3,SSID4, MAC4, Frequncy4, Signal4, SSID5, MAC5, Frequncy5, Signal5,SSID6, MAC6, Frequncy6, Signal6, SSID7, MAC7, Frequncy7, Signal7,SSID8, MAC8, Frequncy8, Signal8, SSID9, MAC9, Frequncy9, Signal9,SSID10, MAC10, Frequncy10, Signal10");
		int[] num_of_lines=get_num_of_lines(wifis);
		leaveJustTen(num_of_lines,wifis);      // this method gets the matrix and give just the 10 power
		int lineNum=0;
		int numOfWifi;
		for(int i=0;i<wifis.length;i++){
			if(num_of_lines[i]>lineNum){
				lineNum=num_of_lines[i];
				outs.println();
				numOfWifi=0;
				for(int temp=i;temp<num_of_lines.length&&num_of_lines[temp]<=lineNum;temp++){
					if(num_of_lines[temp]!=0){
						numOfWifi++;
					}
				}
				outs.print(wifis[i].getTime()+","+wifis[i].getID()+","+wifis[i].getLatitude()+","+wifis[i].getLongitude()+","+wifis[i].getAltitude()+","+numOfWifi);
			}
			if(num_of_lines[i]!=0){
				outs.print(","+wifis[i].getSSID()+","+wifis[i].getMAC()+","+wifis[i].getChannel()+","+wifis[i].getRSSI());
			}
		}
	}
	/**
	 * gets array[][] of info and array num_of_lines that symbolize each line in the info in which line in the new file to print it
	 * changing the num_of_line array such that won't be more than ten wifi in line in the new file
	 * 
	 * @param num_of_lines
	 * @param info
	 * 
	 */
	private static void leaveJustTen(int[] num_of_lines, wifi[] info) {
		int start=0;
		while(start<num_of_lines.length&&num_of_lines[start]==0){
			start++;
		}
		int count=1;
		for(int end=start+1;end<num_of_lines.length;end++){
			if(num_of_lines[start]==num_of_lines[end]){
				count++;
				if(count>10){
					zeroMinimum(start,end,info, num_of_lines);
					while(num_of_lines[start]==0){
						start++;
					}
				}
			}
			if(num_of_lines[start]<num_of_lines[end]){
				start=end;
				count=1;
			}
		}

	}
	/**
	 *  gets start point and end point in the num_of_lines array 
	 *  make the one that his rxl the lowest-zero
	 * 
	 * @param start
	 * @param end
	 * @param wifis
	 * @param num_of_lines
	 */
	private static void zeroMinimum(int start, int end, wifi[] wifis,int[] num_of_lines) {
		int lowest = Integer.parseInt(wifis[start].getRSSI()),place=start;
		for (int p = start; p < end; p++)
		{
			if (wifis[p].getType()=="WIFI"&&Integer.parseInt(wifis[p].getRSSI()) < lowest&&num_of_lines[p]!=0)
			{
				lowest = Integer.parseInt(wifis[p].getRSSI());
				place = p;
			}
		}
		num_of_lines[place]=0;
	}
	/**
	 * Gets array[][] of info. 
	 * Return array num_of_lines that symbolize each line in the info in which line in the new file to print it (zero sybolize not to print)
	 * 
	 * @param wifis
	 * @return number of lines in matrix
	 * 
	 */
	private static int[] get_num_of_lines(wifi[] wifis) {
		int[] num_of_lines=new int[wifis.length];
		int numOfCurrentLine=1;
		num_of_lines[0]=1;
		if(!wifis[0].getType().equals("WIFI")){
			num_of_lines[0]=0;
		}
		for(int i=1;i<wifis.length;i++){
			if(!wifis[i].getType().equals("WIFI")){
				num_of_lines[i]=0;
				continue;
			}
			if(wifis[i].getTime().equals(wifis[i-1].getTime())&&wifis[i].getLatitude().equals(wifis[i-1].getLatitude())&&
					wifis[i].getLongitude().equals(wifis[i-1].getLongitude())&&wifis[i].getAltitude().equals(wifis[i-1].getAltitude())){
				num_of_lines[i]=numOfCurrentLine;
			}
			else{
				numOfCurrentLine++;
				num_of_lines[i]=numOfCurrentLine;
			}
		}
		return num_of_lines;
	}
	/**
	 * Get an array of the relevant path names. 
	 * Return the relevant info from the files in String array[][], The order is:
	 * 0 MAC,1 SSID,2 Time,3 Channel,4 RSSI,5 Latitude,6 Longitude,7 Altitude,8 Type,9 ID
	 * @param pathes-
	 * @return info-matrix of parameters 
	 */
	private static wifi[] collectInfo(String[] pathes){
		int sum_of_num_of_lines=0;
		int currentLine=0;
		int [] num_of_lines=new int[pathes.length];

		for(int i=0;i<pathes.length;i++){
			try {
				num_of_lines[i]=genericFunctions.countLines(pathes[i])-2;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sum_of_num_of_lines += num_of_lines[i];
		}
		wifi[] wifis=new wifi[sum_of_num_of_lines];
		for(int i=0;i<wifis.length;i++){
			wifis[i]=new wifi();
		}
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
				for(j=currentLine ; j<num_of_lines[i]+currentLine ; j++){
					scanner = new Scanner(br.readLine());
					sc=scanner.useDelimiter("\\s*,\\s*");
					wifis[j].setMAC(sc.next());
					wifis[j].setSSID(sc.next());
					sc.next();
					wifis[j].setTime(sc.next());
					wifis[j].setChannel(sc.next());
					wifis[j].setRSSI(sc.next());

					wifis[j].setLatitude(sc.next());
					wifis[j].setLongitude(sc.next());
					wifis[j].setAltitude(sc.next());
					sc.next();
					wifis[j].setType(sc.next());			
					
					wifis[j].setID(display);

					check.checkInfo(wifis,j);
				}
				scanner.close();
				br.close();
			} catch (Exception e) {               // if their is a problem with the csv file, it won't convert to the new csv file
				// TODO Auto-generated catch block
				for(int x=currentLine ; x<num_of_lines[i]+currentLine ; x++){
					wifis[x].setType("0");//don't write this line
				}
			}
			finally{
				currentLine+=num_of_lines[i];	
			}
		}
		return wifis;

	}
}
