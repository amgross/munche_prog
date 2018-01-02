import java.util.Vector;

import de.micromata.opengis.kml.v_2_2_0.Folder;

public class wifiWithCoordinate extends wifi {
	private double altitude;
	private double longitude;
	private double Latitude;
	public wifiWithCoordinate(){
		super();
	}
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
	/**
	 * add placeMark to folder
	 */
	public void placeMark(Folder y){
		y.withName(super.getMAC());
		y.createAndAddPlacemark().withName(super.getMAC()).withDescription(getDiscription()).withOpen(Boolean.TRUE)  
		.createAndSetPoint().addToCoordinates(this.longitude, this.Latitude);
	}

	public static int RSSIavg(Vector<wifiWithCoordinate> wifis){
		int sum=0;
		for(wifiWithCoordinate wifi: wifis)
		{
				sum+=wifi.getRSSI();
		}
		return sum/wifis.size();
	}
	/**
	 * 
	 * @return String of all the discription of wifi's in the point for kml
	 */
	private String getDiscription(){
		String discription=" <br/>SSID: <b>"+super.getSSID()+"  <br/>MAC: <b>"+super.getMAC();
		return discription;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(double latitude) {
		this.Latitude = latitude;
	}

	
	/**
	 * 
	 * @return the router in the format for csv
	 */
	public String toStringForCsv(){

		String toCsv=super.getMAC()+","+super.getSSID()+","+this.Latitude+","+this.longitude+","+this.altitude;
		return toCsv;
	}
	@Override
	public String toString() {
		return  "MAC=" + getMAC() + ", SSID=" + getSSID() + ", altitude=" + altitude + ", longitude=" + longitude + ", Latitude=" + Latitude;
	}
}
