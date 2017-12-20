import java.util.Vector;

public class main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<sameScanWifi> dataBase = WiggleWifi.collectInfoFromWiggleWifi("C:\\munche\\ex2\\a");
		filter shiluvim2=new filterBySSID();
		filter.parm.setParm("Nizri");
		dataBaseFunctions.filterDataBase(shiluvim2, dataBase);
		CSV.printFileFromDataBaseToCSV(dataBase,"C:\\munche\\ex2\\a"); 
		KML.printFileFromDataBaseToKML("C:\\munche\\ex2\\a", dataBase);
		
	}
}
