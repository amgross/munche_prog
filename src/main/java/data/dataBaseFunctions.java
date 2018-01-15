package data;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class dataBaseFunctions{
	/**
	 * delete double mac from database
	 * @param dataBase
	 */
	public static void deleteDoubleMacFromDataBase(Vector<sameScanWifi> dataBase){
		HashMap< String,sameScanWifi> hmap = new HashMap< String,sameScanWifi>();
		for (Iterator<sameScanWifi> sameScanWifiIterator = dataBase.iterator(); sameScanWifiIterator.hasNext(); ) {
			sameScanWifi currentSameScanWifi=sameScanWifiIterator.next();
			for(Iterator<wifi> iter = currentSameScanWifi.iterator(); iter.hasNext(); ) {
				wifi currentWifi=iter.next();
				if(hmap.get(currentWifi.getMAC())==null){
					hmap.put(currentWifi.getMAC(), currentSameScanWifi);
				}
				else{
					for(wifi sameMac:hmap.get(currentWifi.getMAC())){
						if(sameMac.getMAC().equals(currentWifi.getMAC())){
							if(sameMac.getRSSI()>currentWifi.getRSSI()){
								iter.remove();
								break;
							}
							else{
								hmap.get(currentWifi.getMAC()).remove(sameMac);
								hmap.replace(currentWifi.getMAC(),  currentSameScanWifi);
							}
						}
					}
				}
			}
		}
		for (Iterator<sameScanWifi> sameScanWifiIterator = dataBase.iterator(); sameScanWifiIterator.hasNext(); ) {
			if(sameScanWifiIterator.next().size()==0){
				sameScanWifiIterator.remove();
			}
		}

	}



	/**
	 * every wifi with the same mac collected into one vector of wifi with coordinate and all the vectors in one vector
	 * @param database
	 * @return vector of vectors of wifis with the same mac
	 */
	public static Vector<Vector<wifiWithCoordinate>> collectIdenticalMAC(Vector<sameScanWifi> database){
		Vector<Vector<wifiWithCoordinate>> IdenticalMAC=new Vector<Vector<wifiWithCoordinate>>();
		HashMap< String,Vector<wifiWithCoordinate>> hmap = new HashMap< String,Vector<wifiWithCoordinate>>();
		for (sameScanWifi currentSameScanWifi: database) {
			for(wifi currentWifi:currentSameScanWifi){
				if(hmap.get(currentWifi.getMAC())==null){
					Vector<wifiWithCoordinate> temp=new Vector<wifiWithCoordinate>();
					IdenticalMAC.add(temp);
					temp.add((new wifiWithCoordinate(currentSameScanWifi,currentWifi)));
					hmap.put(currentWifi.getMAC(), temp);
				}
				else{
					hmap.get(currentWifi.getMAC()).add(new wifiWithCoordinate(currentSameScanWifi,currentWifi));
				}
			}
		}
		return IdenticalMAC;
	}


	/**
	 * add all the sameScanWifi that in tempSameScanWifiVector into  database
	 * if their was an ellement from the same scan it units them,
	 *  else it add it to the end of the vector
	 * @param dataBase
	 * @param tempDataBase to insert into database
	 */
	public static void unit(Vector<sameScanWifi> dataBase, Vector<sameScanWifi> tempDataBase) {
		// TODO Auto-generated method stub
		for(int i=0;i<tempDataBase.size();i++){
			boolean add=false;
			for(int j=0;j<dataBase.size();j++){
				if(tempDataBase.elementAt(i).compare(dataBase.elementAt(j))){
					dataBase.elementAt(j).insert(tempDataBase.elementAt(i));
					add=true;
					break;
				}
			}
			if(!add){
				dataBase.add(tempDataBase.elementAt(i));
			}
		}
	}
	
	
	public static Vector<sameScanWifi> clone(Vector<sameScanWifi> database){
		 Vector<sameScanWifi> ans = new Vector<sameScanWifi>();
		for (int i=0;i<database.size();i++){
			ans.addElement(database.elementAt(i).clone());
		}
		return ans;
	}
	
	public static boolean contain(Vector<sameScanWifi> database, sameScanWifi check) {
		// TODO Auto-generated method stub
		for(int i=0;i<database.size();i++){
				if(check.compare(database.elementAt(i))){
					return true;
				}
		}
		return false;
	}
	
}
