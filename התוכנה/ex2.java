import java.util.Vector;

public class ex2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fromAppToCSV("ex2");
	}

	public static void fromAppToCSV(String path){
		Vector<sameScanWifi> dataBase=WiggleWifi.collectInfoFromWiggleWifi(path);	
		filter myFilter=new filterBySSID();
		filter.parm.setParm("Ariel_University");
		dataBaseFunctions.filterDataBase(myFilter, dataBase);
		Vector<Vector<wifiWithCoordinate>> IdenticalMAC=dataBaseFunctions.collectIdenticalMAC(dataBase);
		Vector<wifiWithCoordinate> realPlaces=dataBaseFunctions.realPlaces(IdenticalMAC);
		KML.printFileFromWifiWithCoordinateToKML(path, realPlaces);
		/////////////////////////////////////////////////////////////////
		//KML.printFileFromDataBaseToKML(path, dataBase);
		//CSV.printFileFromDataBaseToCSV(dataBase, path);
	}
	
}
