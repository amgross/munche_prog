import java.util.Vector;

public class filterByCoordinates  extends filter{

	@Override
	public String toString() {
		return par1 + " < longitude < " + par2 + " AND " + par3 + " < latitude < " + par4;
	}

	public filterByCoordinates(String minLon, String maxLon, String minLat, String maxLat) {
		super(minLon, maxLon, minLat, maxLat);
		Double.parseDouble(minLon);
		Double.parseDouble(maxLon);
		Double.parseDouble(minLat);
		Double.parseDouble(maxLat);
		// TODO Auto-generated constructor stub
	}

	@Override
	Vector<sameScanWifi> filt(Vector<sameScanWifi> database1, Vector<sameScanWifi> Database2) {
		// TODO Auto-generated method stub
		Vector<sameScanWifi> ans = dataBaseFunctions.clone(database1);
		boolean change;
		do{
			change =false;
			for (int i = 0; i < ans.size(); i++) {
				if(		ans.elementAt(i).getLatitude()<Double.parseDouble(par1)||
						ans.elementAt(i).getLatitude()>Double.parseDouble(par2)||
						ans.elementAt(i).getLongitude()<Double.parseDouble(par3)||
						ans.elementAt(i).getLongitude()>Double.parseDouble(par4)){
					ans.remove(i);
					change=true;
					break;
				}
			}
		}while(change);
		
		return ans;
	}

}
