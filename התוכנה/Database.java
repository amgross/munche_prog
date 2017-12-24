import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

public class Database {
	private Vector<Vector<sameScanWifi>> sourceData;
	private HashMap<Vector<sameScanWifi>,String> sourceDataPath;
	private Vector<sameScanWifi> local_dataBase;
	private Vector<sameScanWifi> current_dataBase;
	private int num_of_routers;
	private filterTree currentFilter;
	private BufferedReader br;

	public Database() {
		newDatabase();
	}

	public filterTree getCurrentFilter() {
		return currentFilter;
	}

	public void deleteDatabase(){
		newDatabase();
	}

	public void newDatabase(){
		sourceData = new Vector<Vector<sameScanWifi>>();
		sourceDataPath = new HashMap<Vector<sameScanWifi>,String>();
		local_dataBase = new Vector<sameScanWifi>();
		current_dataBase = new Vector<sameScanWifi>();
		num_of_routers = 0;
		currentFilter = null;
	}



	public void editCsv(String path){
		try {
			Vector<sameScanWifi> csvData=CSV.collectInfoFromCSV(path);
			sourceData.add(csvData);
			sourceDataPath.put(csvData, path);
			dataBaseFunctions.unit(local_dataBase, csvData);
			createCurrent_dataBase();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			/////////////////////////////////////////
			System.out.println("not good file because one of the numeric fields waswnt numeric\n" 
					+ e.getMessage());
			/////////////////////////////////////////
		} catch (IOException e) {
			// TODO Auto-generated catch block
			////////////////////////////////////////
			System.out.println("the file in the path is open/not found\n" + e.getMessage());
			////////////////////////////////////////
		} catch (Exception e) {
			// TODO Auto-generated catch block
			////////////////////////////////////////
			System.out.println("not good file\n" + e.getMessage());
			///////////////////////////////////////
		}
	}

	public void editWiggle(String path){
		Vector<sameScanWifi> csvData=WiggleWifi.collectInfoFromWiggleWifi(path);
		sourceData.add(csvData);
		sourceDataPath.put(csvData, path);
		dataBaseFunctions.unit(local_dataBase, csvData);
		createCurrent_dataBase();
	}

	private void createCurrent_dataBase() {

		current_dataBase = dataBaseFunctions.clone(local_dataBase);
		if(currentFilter!=null){
			current_dataBase = currentFilter.filt(current_dataBase, local_dataBase);
		}
		num_of_routers = dataBaseFunctions.collectIdenticalMAC(current_dataBase).size();
	}



	public int getNum_of_routers() {
		return num_of_routers;
	}

	public int getNumOfScans(){
		return current_dataBase.size();
	}
	
	public String getFilter(){
		return currentFilter.toString();
	}

	public void saveAsCSV(String path){
		CSV.printFileFromDataBaseToCSV(current_dataBase, path);
	}

	public void saveAsKML(String path){
		KML.printFileFromDataBaseToKML(current_dataBase, path);
	}


	public void timeFilter(String begin, String end){
		currentFilter = new filterTree(new filterByTime(begin, end));
		createCurrent_dataBase();
	}
	public void and_timeFilter(String begin, String end){
		currentFilter = new filterTree(new andGate(), currentFilter, new filterTree(new filterByTime(begin, end)));
		createCurrent_dataBase();
	}
	public void or_timeFilter(String begin, String end){
		currentFilter = new filterTree(new orGate(), currentFilter, new filterTree(new filterByTime(begin, end)));
		createCurrent_dataBase();
	}
	public void and_not_timeFilter(String begin, String end){
		filterTree temp = new filterTree(new notGate(), null, new filterTree(new filterByTime(begin, end)));
		currentFilter = new filterTree(new andGate(), currentFilter, temp);
		createCurrent_dataBase();
	}
	public void or_not_timeFilter(String begin, String end){
		filterTree temp = new filterTree(new notGate(), null, new filterTree(new filterByTime(begin, end)));
		currentFilter = new filterTree(new orGate(), currentFilter, temp);
		createCurrent_dataBase();
	}

	public void coordinateFilter(String minLon, String maxLon, String minLat, String maxLat){
		currentFilter = new filterTree(new filterByCoordinates(minLon, maxLon, minLat, maxLat));
		createCurrent_dataBase();
	}
	public void and_coordinateFilter(String minLon, String maxLon, String minLat, String maxLat){
		currentFilter = new filterTree(new andGate(), currentFilter, new filterTree(new filterByCoordinates(minLon, maxLon, minLat, maxLat)));
		createCurrent_dataBase();
	}
	public void or_coordinateFilter(String minLon, String maxLon, String minLat, String maxLat){
		currentFilter = new filterTree(new orGate(), currentFilter, new filterTree(new filterByCoordinates(minLon, maxLon, minLat, maxLat)));
		createCurrent_dataBase();
	}

	public void deviceFilter(String deviceName){
		currentFilter = new filterTree(new filterByID(deviceName));
		createCurrent_dataBase();
	}
	public void and_deviceFilter(String deviceName){
		currentFilter = new filterTree(new andGate(), currentFilter, new filterTree(new filterByID(deviceName)));
		createCurrent_dataBase();
	}
	public void or_deviceFilter(String deviceName){
		currentFilter = new filterTree(new orGate(), currentFilter, new filterTree(new filterByID(deviceName)));
		createCurrent_dataBase();
	}
	public void and_not_deviceFilter(String deviceName){
		filterTree temp = new filterTree(new notGate(), null, new filterTree(new filterByID(deviceName)));
		currentFilter = new filterTree(new andGate(), currentFilter, temp);
		createCurrent_dataBase();
	}
	public void or_not_DeviceFilter(String deviceName){
		filterTree temp = new filterTree(new notGate(), null, new filterTree(new filterByID(deviceName)));
		currentFilter = new filterTree(new orGate(), currentFilter, temp);
		createCurrent_dataBase();
	}

	public void saveFilter(String path) {
		FileWriter fw;             // statement
		try {
			fw = new FileWriter(path);
			PrintWriter outs = new PrintWriter(fw);
			outs.println(currentFilter);
			fw.close();      // close fw writer
			outs.close();     //close outs writer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			///////////////////////////
			System.out.println("cant write into: " + path + "\n mabe it open?\n" + e.getMessage());
			///////////////////////////
		}
	}
	public void uploadFilter(String path){
		FileReader fr;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			filterTree temp = new filterTree(br.readLine());
			currentFilter = temp;
			createCurrent_dataBase();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//////////////////////////////
			System.out.println("cant find: " + path +"\n" + e.getMessage());
			/////////////////////////////
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/////////////////////////////
			System.out.println("cant read from: " + path +"\n mabe it open?\n" + e.getMessage());
			/////////////////////////////
		}
	}
}


