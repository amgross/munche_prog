
public class wifi {
	
	private String _MAC;
	private String _SSID;
	private int _Channel;
	private int _RSSI;
	
	public  wifi() {
		
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
	
	
	public void setChannel(String channel) {
		_Channel = Integer.parseInt(channel);
		
	}
	public int getChannel() {
		return _Channel;
	}
	public void setRSSI(String rSSI) {
		_RSSI = Integer.parseInt(rSSI);
	}
	public int getRSSI() {
		return _RSSI;
	}
	

}
