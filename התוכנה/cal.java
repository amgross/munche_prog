import java.util.Vector;

public class cal {

	/*
	 * for example: avg0+=arr[i][0]/math.pow(arr[i][4],2); avg1+=arr[i][1]/math.pow(arr[i][4],2);
	 * avg2+=arr[i][2]/math.pow(arr[i][4],2);avg3[i][3]/math.pow(arr[i][4],2);avg4+=(1/math.pow(arr[i][4],2));
	 */
	public static wifiWithCoordinate avgcomp(Vector<wifiWithCoordinate> sameWifi)
	{
		double avgAltitude=0,avgLatitude=0,avLongitude=0,avg=0;
		for (wifiWithCoordinate wifi: sameWifi) {
			avgAltitude += wifi.getAltitude()/Math.pow(wifi.getRSSI(),2); 
			avgLatitude += wifi.getLatitude()/Math.pow(wifi.getRSSI(),2); 
			avLongitude += wifi.getLongitude()/Math.pow(wifi.getRSSI(),2);
			avg += (1/Math.pow(wifi.getRSSI(),2));
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
