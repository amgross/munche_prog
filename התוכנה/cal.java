import java.util.Vector;

public class cal {

	/**
	 * 
	 * @param sameWifi, sort vector of wifiWithCoordinate
	 * @param num of points to check with
	 * @return the estimated place of the router in wifiWithCoordinate object
	 */
	public static wifiWithCoordinate avgcomp(Vector<wifiWithCoordinate> sameWifi,int num)
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
