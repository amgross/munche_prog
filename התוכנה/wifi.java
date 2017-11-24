
public class wifi {

	 //0 MAC,1 SSID,2 Time,3 Channel,4 RSSI,5 Latitude,6 Longitude,7 Altitude,8 Type,9 ID
	private String _MAC;
	private String _SSID;
	private String _Time;
	private String _Channel;
	private String _RSSI;
	private String _Latitude;
	private String _Longitude;
	private String _Altitude;
	private String _Type;
	private String _ID;
	
	public  wifi() {
		
	}
	public wifi(String MAC,String SSID,String Time,String Channel,String RSSI,String Latitude,String Longitude,String Altitude,String Type,String ID) {
		this._MAC=MAC;
		this._SSID=SSID;
		this._Time=Time;
		this._Channel=Channel;
		this._RSSI=RSSI;
		this._Latitude=Latitude;
		this._Longitude=Longitude;
		this._Altitude=Altitude;
		this._Type=Type;
		this._ID=ID;
	}
	
	public void setMAC(String mAC) {
		_MAC = mAC;
	}
	public String getMAC() {
		return _MAC;
	}
	public void setSSID(String sSID) {
		_SSID = sSID;
	}
	public String getSSID() {
		return _SSID;
	}
	public void setTime(String time) {
		_Time = time;
	}
	public String getTime() {
		return _Time;
	}
	public void setChannel(String channel) {
		_Channel = channel;
	}
	public String getChannel() {
		return _Channel;
	}
	public void setRSSI(String rSSI) {
		_RSSI = rSSI;
	}
	public String getRSSI() {
		return _RSSI;
	}
	public void setLatitude(String latitude) {
		_Latitude = latitude;
	}
	public String getLatitude() {
		return _Latitude;
	}
	public void setLongitude(String longitude) {
		_Longitude = longitude;
	}
	public String getLongitude() {
		return _Longitude;
	}
	public void setAltitude(String altitude) {
		_Altitude = altitude;
	}
	public String getAltitude() {
		return _Altitude;
	}
	public void setType(String type) {
		_Type = type;
	}
	public String getType() {
		return _Type;
	}
	public void setID(String iD) {
		_ID = iD;
	}
	public String getID() {
		return _ID;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
