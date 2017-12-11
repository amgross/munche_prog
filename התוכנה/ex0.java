import java.util.Vector;

public class ex0 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		fromAppToCSV("C:\\Download\\mytest");
		fromoCSVToKML("test\\writeKmlTest\\good1.csv","test\\writeKmlTest");
	}

	public static void fromAppToCSV(String path){
		Vector<sameScanWifi> dataBase=WiggleWifi.collectInfoFromWiggleWifi(path);
		CSV.printFileFromDataBaseToCSV(dataBase, path);
	}
	
	public static void fromoCSVToKML(String CSVPath, String KMLPath) throws Exception{
		Vector<sameScanWifi> dataBase=CSV.collectInfoFromCSV(CSVPath);
		filter myFilter=dataBaseFunctions.chooseFilter();
		dataBaseFunctions.filterDataBase(myFilter, dataBase);
		dataBaseFunctions.deleteDoubleMacFromDataBase(dataBase);
		KML.printFileFromDataBaseToKML(KMLPath, dataBase);
	}

}
