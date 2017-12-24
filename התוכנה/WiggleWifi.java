import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class WiggleWifi {
	/**
	 * Gets path of folder.
	 * Returns array of path names of relevant files in the folder.
	 * @param path- A path name
	 * @return array of path names to the relevant files.   
	 */
	private static String[] whichFiles(String path){
		try{
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			String[] pathes=new String[listOfFiles.length];
			int counter=0;
			for (int i=0;i<listOfFiles.length;i++) {
				File file=listOfFiles[i];
				if (file.getName().endsWith(".csv")&&file.isFile()) {
					pathes[counter] = file.getPath();
					counter++;   // another relevant file 
				}
			}
			String[] finalPathes=new String[counter];
			for(int i=0;i<counter;i++){
				finalPathes[i]=pathes[i];
			}
			return finalPathes;
		}catch(NullPointerException ex){
			///////////////////////////////
			System.out.println("there is no folder in that path");
			///////////////////////////////
		}
		return null;
	}
	/**
	 * Get an path of folder that contains wiggle wifi files. 
	 * Return the relevant info from the files in dataBase
	 * @param pathes-
	 * @return info-matrix of parameters 
	 */
	public static Vector<sameScanWifi> collectInfoFromWiggleWifi(String path){
		String[] pathes=whichFiles(path);
		int [] num_of_lines=new int[pathes.length];

		for(int i=0;i<pathes.length;i++){
			try {
				num_of_lines[i]=genericFunctions.countLines(pathes[i])-2;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				num_of_lines[i]=0;
				System.out.println("problem with counting lines of: " + pathes[i]);
			}
		}
		Vector<sameScanWifi> dataBase=new Vector<sameScanWifi>();
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
				dataBaseFunctions.unit(dataBase,tempSameScanWifiVector);
				scanner.close();
				br.close();
				///////////////////////////////////
				System.out.println("add: " +pathes[i]);
				///////////////////////////////////
			} catch (Exception e) {               // if their is a problem with the csv file, it won't convert to the new csv file
				// TODO Auto-generated catch block
				////////////////////////////////
				System.out.println(pathes[i] + "   is csv file but not good app file");
				///////////////////////////////
			}

		}

		return dataBase;

	}
}
