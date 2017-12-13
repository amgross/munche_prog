import java.util.Vector;

public class ex2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		algorithm_1("C:\\munche\\ex2\\a","C:\\munche\\ex2\\a",3);
	}
	/**
	 * 
	 * @param reading_path path of folder with all the wiggle data
	 * @param writing_path path of folder to put the kml with the places of the routers
	 * @param num_of_points num of points to check with them where is the routers
	 * prints kml file that called wifi.kml that presents the routers
	 */
	public static void algorithm_1(String reading_path,String writing_path,int num_of_points){
		Vector<sameScanWifi> dataBase=WiggleWifi.collectInfoFromWiggleWifi(reading_path);
		filter shiluvim2=new filterBySSID();
		filter.parm.setParm("Nizri");
		dataBaseFunctions.filterDataBase(shiluvim2, dataBase);
		Vector<Vector<wifiWithCoordinate>> IdenticalMAC=dataBaseFunctions.collectIdenticalMAC(dataBase);
		Vector<wifiWithCoordinate> realPlaces=dataBaseFunctions.realPlaces(IdenticalMAC,num_of_points);
		CSV.printFileFromWifiWithCoordinateToCSV(writing_path, realPlaces);
		KML.printFileFromWifiWithCoordinateToKML(writing_path, realPlaces);
	}
	
}
