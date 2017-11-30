import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
/**
 * 
 *       
 * @author arye gross and avi wasserberger
 */
/**
 * this program get path of the csv file that the function 'toCsv' made
 * Write it to KML file 
 * if their are problems with the csv file this function will stop in the middle
 * @param path,
 */
public class toKml {
	
	private static final Exception Exception = null;
	private static Scanner console;
	public static int writeKml(String csvPath, String newKmlPath){
		String userAns;
		filter myFilter=null;
		////////get the filter from the client
		console = new Scanner(System.in);
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
							check.checkTime(userAns);
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
	 * Write the KML file with the filter that the user wanted.
	 * @param outs-
	 * @param filePlace-
	 * @return 0 if the csv file was'nt good, else return 1
	 */
	private static int editPlacesToKml(String newKmlPath,String path,filter myFilter, String filter){
		try {
			final Kml kml = new Kml();
			Document document = kml.createAndSetDocument().withName("MyWifi");
			Vector<sameScanWifi> Info=collectInfo(path,myFilter, filter);
			dataBase.deleteExtraMac(Info);
			for(sameScanWifi current:Info){
				Folder y =document.createAndAddFolder();
				current.placeMark(y);
			}
			//marshals into file
			kml.marshal(new File(newKmlPath+"\\wifi.kml"));
			return 1;
		}
		catch(Exception ex) {                            // exception
			//System.exit(2);
			return 0;
		}
	}
	
	/**
	 * collect the info into Vector<sameScanWifi> with the filter
	 * @param path
	 * @param myFilter
	 * @param filter
	 * @return
	 * @throws Exception if the csv file is'nt good
	 */
	private static Vector<sameScanWifi> collectInfo(String path,filter myFilter,String filter) throws Exception{
		Vector<sameScanWifi> wifis=new Vector<sameScanWifi>();
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
			if(myFilter.filters(tempSameScanWifi,filter)){
				wifis.add(tempSameScanWifi);
			}
		}
		
		br.close();
		return wifis;
	}
	
	
	
}
