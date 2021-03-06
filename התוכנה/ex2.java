import java.util.Vector;

public class ex2 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//algorithm_1("C:\\munche\\ex2\\bm3\\wifi.csv","C:\\munche\\ex2\\bm3\\router.csv",20);
		algorithm_2("C:\\munche\\ex2\\bm3TS1\\wifi.csv","C:\\munche\\ex2\\bm3TS1\\_comb_no_gps_ts1.csv","C:\\munche\\ex2\\bm3TS1\\router.csv",3);
	}
	/**
	 * 
	 * @param reading_path path of our csv file
	 * @param writing_path path of folder to put the csv with the places of the routers
	 * @param num_of_points num of points to check with them where is the routers
	 * prints csv file that called router.csv that presents the routers
	 */
	public static void algorithm_1(String reading_path,String writing_path,int num_of_points){

		Vector<sameScanWifi> dataBase;
		try {
			dataBase = CSV.collectInfoFromCSV(reading_path);
			Vector<Vector<wifiWithCoordinate>> IdenticalMAC=dataBaseFunctions.collectIdenticalMAC(dataBase);
			Vector<wifiWithCoordinate> realPlaces=findPlaces.realPlaces(IdenticalMAC,num_of_points);
			CSV.printFileFromWifiWithCoordinateToCSV( realPlaces, writing_path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("problem with readyng/writing path/file");
			e.printStackTrace();
		}	
	}
	
	/**
	 * 
	 * @param reading_database_pathCSV = regiler csv file
	 * @param reading_without_coordinate_path = csv file that we want to revalue his coordinates
	 * @param writing_path = file to write in it the csv after revaluing its coordinates
	 * @param num_of_points = with how many points in the data base to revalue the coordinates
	 * @throws Exception = if the pathes are wrong/the files in them have problems
	 */
	public static void algorithm_2(String reading_database_pathCSV,String reading_without_coordinate_path,String writing_path,int num_of_points) throws Exception{
		Vector<sameScanWifi> dataBase=CSV.collectInfoFromCSV(reading_database_pathCSV);
		Vector<sameScanWifi> manScans=CSV.collectInfoFromCSV(  reading_without_coordinate_path);
		findPlaces.findManPlace(dataBase,manScans,num_of_points);
		CSV.printFileFromDataBaseToCSV( manScans, writing_path);
	}
}
