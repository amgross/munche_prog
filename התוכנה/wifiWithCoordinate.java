import de.micromata.opengis.kml.v_2_2_0.Folder;

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
	/**
	 * add placeMark to folder
	 */
	public void placeMark(Folder y){
		y.withName(super.getMAC());
		y.createAndAddPlacemark().withName(super.getMAC()).withDescription(getDiscription()).withOpen(Boolean.TRUE)  
		.createAndSetPoint().addToCoordinates(this.longitude, this.Latitude);
	}
	/**
	 * 
	 * @return String of all the discription of wifi's in the point for kml
	 */
	private String getDiscription(){
		String discription=" <br/>SSID: <b>"+super.getSSID()+"  <br/>MAC: <b>"+super.getMAC();
		return discription;
	}
	
}
