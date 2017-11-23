import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
			for (int i=0;i<genericFunctions.countLines(path);i++){
				try {
					String[] parts = br.readLine().split(",");
					check.checkLine(parts);
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
	
}
