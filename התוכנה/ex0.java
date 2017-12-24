import java.util.Vector;

public class ex0 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		fromAppToCSV("C:\\munche\\ex2\\a","C:\\munche\\ex2\\a\\wifi.csv");
		fromoCSVToKML("C:\\munche\\ex2\\a\\wifi.csv","C:\\munche\\ex2\\a\\wifi.kml");
	}

	public static void fromAppToCSV(String wiggleFolder, String printCsvFile){
		Vector<sameScanWifi> dataBase=WiggleWifi.collectInfoFromWiggleWifi(wiggleFolder);
		CSV.printFileFromDataBaseToCSV(dataBase, printCsvFile);
	}
	
	public static void fromoCSVToKML(String CSVPath, String KMLPath) throws Exception{
		Vector<sameScanWifi> dataBase=CSV.collectInfoFromCSV(CSVPath);
		filter myFilter=dataBaseFunctions.chooseFilter();
		dataBaseFunctions.filterDataBase(myFilter, dataBase);
		dataBaseFunctions.deleteDoubleMacFromDataBase(dataBase);
		KML.printFileFromDataBaseToKML(dataBase, KMLPath);
	}

}
