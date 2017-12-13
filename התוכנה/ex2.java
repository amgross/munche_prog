import java.util.Vector;

public class ex2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		algorithm_1("ex2\\bm2","ex2\\bm2",3);
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
		Vector<Vector<wifiWithCoordinate>> IdenticalMAC=dataBaseFunctions.collectIdenticalMAC(dataBase);
		Vector<wifiWithCoordinate> realPlaces=dataBaseFunctions.realPlaces(IdenticalMAC,num_of_points);
		KML.printFileFromWifiWithCoordinateToKML(writing_path, realPlaces);
	}
	
	public static void algorithm_2(String reading_database_pathCSV,String reading_man_pathCSV,String writing_path,int num_of_points) throws Exception{
		Vector<sameScanWifi> dataBase=CSV.collectInfoFromCSV(reading_database_pathCSV);
		Vector<Vector<wifiWithCoordinate>> IdenticalMAC=dataBaseFunctions.collectIdenticalMAC(dataBase);
		Vector<wifiWithCoordinate> routerPlaces=dataBaseFunctions.realPlaces(IdenticalMAC,num_of_points);
		Vector<sameScanWifi> manScans=CSV.collectInfoFromCSV( reading_man_pathCSV);
		wifiWithCoordinate manPlace=cal3.findManPlace(routerPlaces,manScans,num_of_points);
	}
}
