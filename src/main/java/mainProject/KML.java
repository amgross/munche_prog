package mainProject;
import java.io.File;
import java.io.FileNotFoundException;
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
public class KML {
	
	
	public static void printFileFromDataBaseToKML(Vector<sameScanWifi> dataBase, String path){
			final Kml kml = new Kml();
			Document document = kml.createAndSetDocument().withName("MyWifi");
			for(sameScanWifi current:dataBase){
				Folder y =document.createAndAddFolder();
				current.placeMark(y);
			}
			//marshals into file
			try {
				kml.marshal(new File(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				///////////////////////////
				System.out.println("cant write into: " + path + "\n mabe it open?\n" + e.getMessage());
				///////////////////////////
			}
		
	}
	
	/**
	 * Write the KML file from databace to new kml file.
	 * @param filePlace-
	 * @return 0 if the csv file was'nt good, else return 1
	 */
	public static int printFileFromWifiWithCoordinateToKML(String folderPath,Vector<wifiWithCoordinate> dataBase){
		try {
			final Kml kml = new Kml();
			Document document = kml.createAndSetDocument().withName("MyWifi");
			for(wifiWithCoordinate current:dataBase){
				Folder y =document.createAndAddFolder();
				current.placeMark(y);
			}
			//marshals into file
			kml.marshal(new File(folderPath));
			return 1;
		}
		catch(Exception ex) {                            // exception
			//System.exit(2);
			return 0;
		}
	}
	
	
}
