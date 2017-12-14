import java.util.Iterator;

import de.micromata.opengis.kml.v_2_2_0.Folder;

/**
 * this class hands array of not more than ten wifis from the same scan
 * @author אריה גרוס
 *
 */
public class sameScanWifi implements Iterable<wifi>{
	private int num;
	private double altitude;
	private double longitude;
	private double Latitude;
	private String time;
	private String ID;
	private wifi[] wifis=new wifi[10];

	public sameScanWifi(){
		num=0;
	}

	/**
	 * this methode get another sameScanWifi and check if they are from the same scan
	 */
	public boolean compare(sameScanWifi a){
		if(a.getAltitude()==this.altitude&&a.getID().equals(this.ID)&&
				a.getLatitude()==this.Latitude&&a.getLongitude()==this.longitude&&
				a.getTime().equals(this.time)){
			return true;
		}
		return false;
	}

	public void insert(sameScanWifi a){
		for(wifi current:a){
			this.insert(current);
		}
	}

	/**
	 * 
	 * @param wifi
	 * @return true if this wifi exist as is and false else
	 */
	public boolean exist(wifi a){
		for(int i=0;i<num;i++){
			if(a.getMAC()==wifis[i].getMAC()&&a.getChannel()==wifis[i].getChannel()&&a.getRSSI()==wifis[i].getRSSI()&&a.getSSID()==wifis[i].getSSID()){
				return true;
			}
		}
		return false;
	}
	public boolean exist(String mac){
		for(int i=0;i<num;i++){
			if(mac.equals(wifis[i].getMAC())){
				return true;
			}
		}
		return false;
	}
	
	public wifi getWifi(String MAC){
		for(int i=0;i<num;i++){
			if(MAC.equals(wifis[i].getMAC())){
				return wifis[i];
			}
		}
		return null;

	}
	/**
	 * insert new wifi, if their are already ten delete the one with the min rssi
	 * @param wifi
	 */
	public void insert(wifi a){
		if (exist(a)){
			return;
		}
		if(num<10){
			wifis[num]=a;
			num++;
		}
		else{
			for(int i=0;i<10;i++){
				if(wifis[i].getRSSI()<a.getRSSI()){
					wifi temp=a;
					a=wifis[i];
					wifis[i]=temp;
				}
			}
		}
	}



	/**
	 * 
	 * @return the wifis in the format for csv
	 */
	public String toStringForCsv(){

		String toCsv=this.time+","+this.ID+","+this.Latitude+","+this.longitude+","+this.altitude+","+this.num;
		for(int i=0;i<num;i++){
			toCsv+=","+wifis[i].getSSID()+","+wifis[i].getMAC()+","+wifis[i].getChannel()+","+wifis[i].getRSSI();
		}
		return toCsv;
	}

	/**
	 * add placeMark to folder
	 */
	public void placeMark(Folder y){
		String[] time = this.time.split(" ");
		y.withName(this.time).createAndSetTimeStamp().setWhen(time[0]+"T"+time[1]+"Z");
		y.createAndAddPlacemark().withName(this.time).withDescription(getDiscription()).withOpen(Boolean.TRUE)  
		.createAndSetPoint().addToCoordinates(this.longitude, this.Latitude);
	}

	/**
	 * 
	 * @return String of all the discription of wifi's in the point for kml
	 */
	private String getDiscription(){
		String discription="";
		for(int i=0;i<num;i++){
			discription +=" <br/>"+(i+1)+": <br/>SSID: <b>"+wifis[i].getSSID()+"  <br/>MAC: <b>"+wifis[i].getMAC()+"  <br/>Channel: <b>"+wifis[i].getChannel()+"  <br/>Freqency: <b>"+wifis[i].getRSSI();
		}
		return discription;
	}

	/**
	 *  remove one wifi
	 * @param wifi
	 * @return true if the object didn't emptied, else false
	 */
	public boolean remove(wifi delete){
		for(int i=0;i<this.num-1;i++){
			if(delete==this.wifis[i]){
				this.wifis[i]=this.wifis[this.num-1];
				this.num--;
				return true;
			}
		}
		if(delete==this.wifis[this.num-1]){
			this.num--;
		}
		if(this.num==0)return false;
		return true;
	}

	/**
	 * 
	 * setters return exception if the values are non good
	 */
	public void setAltitude(String altitude) {
		this.altitude = Double.parseDouble(altitude);
	}



	public void setLongitude(String longtitude) {
		this.longitude = Double.parseDouble(longtitude);
	}



	public void setLatitude(String latitude) {
		Latitude = Double.parseDouble(latitude);
	}



	public void setTime(String time) throws Exception {
		this.time = time;
		check.checkTime(time);
	}



	public void setID(String iD) {
		ID = iD;
	}

	public int size() {
		return num;
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

	public String getID() {
		return ID;
	}

	public String getTime() {
		return time;
	}

	/**
	 * iterator for scanning all the wifis that contained
	 */
	@Override
	public Iterator<wifi> iterator() {
		// TODO Auto-generated method stub
		Iterator<wifi> iter=new Iterator<wifi>(){
		private int next=0;
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			wifis[next-1]=wifis[num-1];
			num--;
			next--;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(next<num){
				return true;
			}
			return false;
		}

		@Override
		public wifi next() {
			// TODO Auto-generated method stub
			next++;
			return wifis[next-1];
		}

		};
		return iter;
	}


}
