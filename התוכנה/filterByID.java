/**
 * 
 * implement of filter to line for the kml file by ID
 *if it is non good ID return false
 *else returning true
 */
public class filterByID implements filter{



	@Override
	public boolean filters(sameScanWifi info, String ID) {
		if(info.getID().equals(ID)){
			
			return true;
		}
		return false;
		
	}

	
	
}
