package data;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;


public class findPlaces {

	private static final int _POWER = 2;
	private static final int _NORM = 10000;
	private static final double _SIGNAL_DIFF = 0.4;
	private static final int _MIN_DIFF = 3;
	private static final int _DIFF_NO_SIGNAL = 100;

	/**
	 * the program getting two databases of Vector<sameScanWifi>
	 * every scan from the second the program will take the most close scans from the first and will revalue the coordinates of the scan with them.
	 * @param database 
	 * @param manScans
	 * @param num_of_points-how many scans from the first to use for revalue
	 */
	public static void findManPlace(Vector<sameScanWifi> database,Vector<sameScanWifi> manScans,int num_of_points){
		for(sameScanWifi fixingNow : manScans){
			HashMap<sameScanWifi,Double> scansWeight= new HashMap<sameScanWifi,Double>();

			for(sameScanWifi gettingWeight : database){				
				scansWeight.put(gettingWeight, getScanWeight(gettingWeight,fixingNow));
			}
			database.sort(Comparator.comparing(scan -> -scansWeight.get(scan)));
			double lat=0,lon=0,alt=0,weight=0;
			for(sameScanWifi currentScan : database){	
				num_of_points--;
				lat+= scansWeight.get(currentScan)*currentScan.getLatitude();
				lon+= scansWeight.get(currentScan)*currentScan.getLongitude();
				alt+= scansWeight.get(currentScan)*currentScan.getAltitude();
				weight+= scansWeight.get(currentScan);
				if(num_of_points==0) break;
			}
			fixingNow.setAltitude(String.valueOf(alt/weight));
			fixingNow.setLatitude(String.valueOf(lat/weight));
			fixingNow.setLongitude(String.valueOf(lon/weight));
		}
	}
	
	/**
	 * 
	 * @param gettingWeight=scan that we 'weighting' now how much it close
	 * @param fixingNow=scan that we want now to find his coordinates
	 * @return the 'weight' as double
	 */
	private static double getScanWeight(sameScanWifi gettingWeight, sameScanWifi fixingNow) {
		// TODO Auto-generated method stub

		double scanWeight=1;
		for(wifi currentWifi : fixingNow){
			wifi same=gettingWeight.getWifi(currentWifi.getMAC());
			if(same!=null){
				scanWeight*=findWheight(same.getRSSI(),currentWifi.getRSSI());
			}
			else{
				scanWeight *= findWheight(_DIFF_NO_SIGNAL + currentWifi.getRSSI(),currentWifi.getRSSI());
			}
		}
		return scanWeight;
	}

	/**
	 * 
	 * @param check=rssi of a mac in the scan that we 'weighting' now how much it close
	 * @param input=rssi of a mac in scan that we want now to find his coordinates
	 * (bose are the same mac)
	 * @return double of the 'weight' of this current mac in the scan that we 'weighting' now how much it close
	 */
	private static double findWheight(int check,int input)
	{ 

		return (_NORM/(Math.pow(Math.max(Math.abs(check-input),_MIN_DIFF), _SIGNAL_DIFF)*(Math.pow(input, _POWER))));


	}
	
	/**
	 * 
	 * @param IdenticalMAC vector that every object in it is vector of the same ruter from diffrent scan from the same placr
	 * @param num of points to check with
	 * @return the real places of the routers in vector of wifiWithCoordinate
	 */
	public static Vector<wifiWithCoordinate> realPlaces(Vector<Vector<wifiWithCoordinate>> IdenticalMAC,int num){
		Vector<wifiWithCoordinate> realePlace=new Vector<wifiWithCoordinate>();
		for(Vector<wifiWithCoordinate> sameWifi: IdenticalMAC ){
			sameWifi.sort(Comparator.comparing(sample -> -sample.getRSSI()));
			realePlace.add(avgcomp(sameWifi,num));
		}



		return realePlace;
	}
	
	/**
	 * 
	 * @param sameWifi, sort vector of wifiWithCoordinate
	 * @param num of points to check with
	 * @return the estimated place of the router in wifiWithCoordinate object
	 */
	private static wifiWithCoordinate avgcomp(Vector<wifiWithCoordinate> sameWifi,int num)
	{
		double avgAltitude=0,avgLatitude=0,avLongitude=0,avg=0;
		
		for (wifiWithCoordinate wifi: sameWifi) {
			num--;
			avgAltitude += wifi.getAltitude()/Math.pow(wifi.getRSSI(),2); 
			avgLatitude += wifi.getLatitude()/Math.pow(wifi.getRSSI(),2); 
			avLongitude += wifi.getLongitude()/Math.pow(wifi.getRSSI(),2);
			avg += (1/Math.pow(wifi.getRSSI(),2));
			if(num==0) break;
		}
		wifiWithCoordinate realPlace=new wifiWithCoordinate();
		realPlace.setAltitude(avgAltitude/avg);
		realPlace.setLatitude(avgLatitude/avg);
		realPlace.setLongitude(avLongitude/avg);
		realPlace.setMAC(sameWifi.firstElement().getMAC());
		realPlace.setSSID(sameWifi.firstElement().getSSID());

		return realPlace;
	}

}
