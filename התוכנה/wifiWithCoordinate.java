
public class wifiWithCoordinate extends wifi {
	private double altitude;
	private double longitude;
	private double Latitude;
	public wifiWithCoordinate(sameScanWifi coordinates, wifi details){
		super();
		altitude=coordinates.getAltitude();
		longitude=coordinates.getLongitude();
		Latitude=coordinates.getLatitude();
		super.setChannel(Integer.toString(details.getChannel()));
		super.setMAC(details.getMAC());
		super.setRSSI(Integer.toString(details.getRSSI()));
		super.setSSID(details.getSSID());
	}
	public double getAltitude() {
		return altitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getLatitude() {
		return Latitude;
	}
	
}
