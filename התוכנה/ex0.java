package arye; 

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimePrimitive;
/**
 * 
 *       
 * @author arye gross and avi wasserberger
 * @version 7/11/17
 */
public class ex0 {

	private static final Exception Exception = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		toCsv("C:\\Download\\arye\\test1");
		writeKml("C:\\Download\\arye\\test1\\wifi.csv","C:\\Download\\arye\\test1");
	}
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
	public static void toCsv(String path){
		FileWriter fw;             // statement
		try {                 //   try write the file 
			fw = new FileWriter(path+"\\wifi.csv");
			PrintWriter outs = new PrintWriter(fw);
			String[] pathes=whichFiles(path);
			String[][] wifiInfo=collectInfo(pathes);
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
	private static void printFile(String[][] info,PrintWriter outs){
		outs.print("Time, ID, Lat, Lon, Alt, #WiFi networks (up to 10), SSID1, MAC1, Frequncy1, Signal1,SSID2, MAC2, Frequncy2, Signal2, SSID3, MAC3, Frequncy3, Signal3,SSID4, MAC4, Frequncy4, Signal4, SSID5, MAC5, Frequncy5, Signal5,SSID6, MAC6, Frequncy6, Signal6, SSID7, MAC7, Frequncy7, Signal7,SSID8, MAC8, Frequncy8, Signal8, SSID9, MAC9, Frequncy9, Signal9,SSID10, MAC10, Frequncy10, Signal10");
		int[] num_of_lines=get_num_of_lines(info);
		leaveJustTen(num_of_lines,info);      // this method gets the matrix and give just the 10 power
		int lineNum=0;
		int numOfWifi;
		for(int i=0;i<info[0].length;i++){
			if(num_of_lines[i]>lineNum){
				lineNum=num_of_lines[i];
				outs.println();
				numOfWifi=0;
				for(int temp=i;temp<num_of_lines.length&&num_of_lines[temp]<=lineNum;temp++){
					if(num_of_lines[temp]!=0){
						numOfWifi++;
					}
				}
				outs.print(info[2][i]+","+info[9][i]+","+info[5][i]+","+info[6][i]+","+info[7][i]+","+numOfWifi);
			}
			if(num_of_lines[i]!=0){
				outs.print(","+info[1][i]+","+info[0][i]+","+info[3][i]+","+info[4][i]);
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
	private static void leaveJustTen(int[] num_of_lines, String[][] info) {
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
	 * @param info
	 * @param num_of_lines
	 */
	private static void zeroMinimum(int start, int end, String[][] info,int[] num_of_lines) {
		int lowest = Integer.parseInt(info[4][start]),place=start;
		for (int p = start; p < end; p++)
		{
			if (info[8][p]=="WIFI"&&Integer.parseInt(info[4][p]) < lowest&&num_of_lines[p]!=0)
			{
				lowest = Integer.parseInt(info[4][p]);
				place = p;
			}
		}
		num_of_lines[place]=0;
	}
	/**
	 * Gets array[][] of info. 
	 * Return array num_of_lines that symbolize each line in the info in which line in the new file to print it (zero sybolize not to print)
	 * 
	 * @param info
	 * @return number of lines in matrix
	 * 
	 */
	private static int[] get_num_of_lines(String[][] info) {
		int[] num_of_lines=new int[info[0].length];
		int numOfCurrentLine=1;
		num_of_lines[0]=1;
		if(!info[8][0].equals("WIFI")){
			num_of_lines[0]=0;
		}
		for(int i=1;i<info[0].length;i++){
			if(!info[8][i].equals("WIFI")){
				num_of_lines[i]=0;
				continue;
			}
			if(info[2][i].equals(info[2][i-1])&&info[5][i].equals(info[5][i-1])&&
					info[6][i].equals(info[6][i-1])&&info[7][i].equals(info[7][i-1])){
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
	 * from https://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java 
	 * Gets path of a file.  
	 * Return the number of lines in it.
	 * 
	 * @param filename
	 * @return 1 or number of lines
	 * @throws IOException
	 */
	private static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {                 // try
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
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
	 * Get an array of the relevant path names. 
	 * Return the relevant info from the files in String array[][], The order is:
	 * 0 MAC,1 SSID,2 Time,3 Channel,4 RSSI,5 Latitude,6 Longitude,7 Altitude,8 Type,9 ID
	 * @param pathes-
	 * @return info-matrix of parameters 
	 */
	private static String[][] collectInfo(String[] pathes){
		int sum_of_num_of_lines=0;
		int currentLine=0;
		int [] num_of_lines=new int[pathes.length];

		for(int i=0;i<pathes.length;i++){
			try {
				num_of_lines[i]=countLines(pathes[i])-2;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sum_of_num_of_lines += num_of_lines[i];
		}
		String info[][]=new String[10][sum_of_num_of_lines];
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
					info[0][j]=sc.next();
					info[1][j]=sc.next();
					sc.next();
					info[2][j]=sc.next();
					info[3][j]=sc.next();
					info[4][j]=sc.next();
					info[5][j]=sc.next();
					info[6][j]=sc.next();
					info[7][j]=sc.next();
					sc.next();
					info[8][j]=sc.next();
					info[9][j]=display;
					checkInfo(info,j);
				}
				scanner.close();
				br.close();
			} catch (Exception e) {               // if their is a problem with the csv file, it won't convert to the new csv file
				// TODO Auto-generated catch block
				for(int x=currentLine ; x<num_of_lines[i]+currentLine ; x++){
					info[8][x]="0";//don't write this line
				}
			}
			finally{
				currentLine+=num_of_lines[i];	
			}
		}
		return info;

	}
	/**
	 * if line in info is not good, throw exeption
	 * @param info
	 * @param lineNum
	 * @throws java.lang.Exception 
	 */
	private static void checkInfo(String[][] info, int lineNum) throws java.lang.Exception {
		// TODO Auto-generated method stub
		checkTime(info[2][lineNum]);
		Integer.parseInt(info[3][lineNum]);
		Integer.parseInt(info[4][lineNum]);
		Double.parseDouble(info[5][lineNum]);
		Double.parseDouble(info[6][lineNum]);
		Double.parseDouble(info[7][lineNum]);
	}
	/**
	 * get path of the csv file that the function 'toCsv' made
	 * Write it to KML file 
	 * if their are problems with the csv file this function will stop in the middle
	 * @param path,
	 */
	public static int writeKml(String csvPath, String newKmlPath){
		String userAns;
		filter myFilter=null;
		Scanner console = new Scanner(System.in);
		do{
			System.out.println("do you want to filter the low rxl? y/n");
			userAns=console.next();
		}while(!userAns.equals("n")&&!userAns.equals("y"));
		if(userAns.equals("y")){
			while(true){
				try{
					System.out.println("what is the min rxl?");
					userAns=console.next();
					Integer.parseInt(userAns);
					break;
				}catch(Exception e){

				}
			}
			myFilter=new filterByRXL();

		}
		else{
			do{
				System.out.println("do you want to filter just one ID? y/n");
				userAns=console.next();
			}while(!userAns.equals("n")&&!userAns.equals("y"));
			if(userAns.equals("y")){
				System.out.println("what is the ID?");
				console.nextLine();
				userAns=console.nextLine();
				myFilter=new filterByID();


			}else{
				do{
					System.out.println("do you want to filter just the recent time? y/n");
					userAns=console.next();
				}while(!userAns.equals("n")&&!userAns.equals("y"));
				if(userAns.equals("y")){
					System.out.println("data from which time you want? tipe it: year-month-day hour:minut:second");
					console.nextLine();
					while(true){
						try{
							userAns=console.nextLine();
							checkTime(userAns);
							myFilter=new filterByTime();
							break;
						}catch(Exception e){
							System.out.println("tipe it: year-month-day hour:minut:second");
						}
					}
				}else{
					myFilter=new dontFilter();
				}
			}
		}

		if(editPlacesToKml(newKmlPath,csvPath, myFilter,userAns)==0){
			System.out.println("the file in the path is not good csv file that our function made");
			return 0;
		}
		return 1;
	}
	/**
	 * if the string is not good, throws exeption
	 * @param userAns
	 * @throws java.lang.Exception
	 */
	private static void checkTime(String userAns) throws java.lang.Exception {
		// TODO Auto-generated method stub
		String[] Checker=userAns.split("-");
		Integer.parseInt(Checker[0]);
		if(Integer.parseInt(Checker[1])>12||Integer.parseInt(Checker[1])<1){
			throw Exception;
		}
		Checker=Checker[2].split(" ");
		if(Integer.parseInt(Checker[0])>31||Integer.parseInt(Checker[0])<1){
			throw Exception;
		}
		Checker=Checker[1].split(":");
		if(Integer.parseInt(Checker[0])>23||Integer.parseInt(Checker[0])<0){
			throw Exception;
		}
		if(Integer.parseInt(Checker[1])>59||Integer.parseInt(Checker[1])<0){
			throw Exception;
		}
		if(Integer.parseInt(Checker[1])>59||Integer.parseInt(Checker[1])<0){
			throw Exception;
		}
	}
	/**
	 * Write the KML file with the filter that the user wanted.
	 * @param outs-
	 * @param filePlace-
	 * @return 
	 */
	private static int editPlacesToKml(String newKmlPath,String path,filter myFilter, String filter){
		try {
			final Kml kml = new Kml();
			Document document = kml.createAndSetDocument().withName("MyWifi");
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String str;
			str = br.readLine();
			if (!str.equals("Time, ID, Lat, Lon, Alt, #WiFi networks (up to 10), SSID1, MAC1, Frequncy1, Signal1,SSID2, MAC2, Frequncy2, Signal2, SSID3, MAC3, Frequncy3, Signal3,SSID4, MAC4, Frequncy4, Signal4, SSID5, MAC5, Frequncy5, Signal5,SSID6, MAC6, Frequncy6, Signal6, SSID7, MAC7, Frequncy7, Signal7,SSID8, MAC8, Frequncy8, Signal8, SSID9, MAC9, Frequncy9, Signal9,SSID10, MAC10, Frequncy10, Signal10" )){
				br.close();
				return 0;//fail
			}
			for (int i=0;i<countLines(path);i++){
				try {
					String[] parts = br.readLine().split(",");
					checkLine(parts);
					if(! myFilter.filters(parts,filter)){
						continue;
					}
					String[] time = parts[0].split(" ");
					Folder y =document.createAndAddFolder();
					y.withName(parts[0]).createAndSetTimeStamp().setWhen(time[0]+"T"+time[1]+"Z");
					y.createAndAddPlacemark().withName(parts[0]).withDescription(getDiscription(parts)).withOpen(Boolean.TRUE)  
					.createAndSetPoint().addToCoordinates(Double.parseDouble(parts[3]), Double.parseDouble(parts[2]));
				}
				catch(Exception ex) {
					System.out.println(ex);
					br.close();// exception
					return (0);//fail
				}


			}
			//marshals into file
			kml.marshal(new File(newKmlPath+"\\wifi.kml"));
			br.close();
			fr.close();
			return 1;
		}
		catch(IOException ex) {                            // exception
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
			return 0;
		}
	}
	/**
	 * 
	 * @param array of one line from the csv file
	 * @return String of all the discription of wifi's in the point
	 */
	private static String getDiscription(String[] info){
		String discription="";
		for(int j=6,counter=1;j<Integer.parseInt(info[5])*4+6;j+=4,counter++){
			if(info[j+3]!="0"){
				discription +=" <br/>"+counter+": <br/>SSID: <b>"+info[j]+"  <br/>MAC: <b>"+info[j+1]+"  <br/>Channel: <b>"+info[j+2]+"  <br/>Freqency: <b>"+info[j+3];
			}
		}
		return discription;
	}
	/**
	 * if line from csv file is not good, throw exeption
	 * @param info
	 * @throws java.lang.Exception 
	 */
	private static void checkLine(String[] parts) throws java.lang.Exception {
		// TODO Auto-generated method stub
		checkTime(parts[0]);
		Double.parseDouble(parts[2]);
		Double.parseDouble(parts[3]);
		Double.parseDouble(parts[4]);
		for(int j=6;j<Integer.parseInt(parts[5])*4+6;j+=4){
			Integer.parseInt(parts[j+2]);
			Integer.parseInt(parts[j+3]);
		}
	}
}
