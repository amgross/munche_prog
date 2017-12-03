/**
 * 
 * implement of filter to 'sameScanWifi' for the kml file by ID
 *if it is non good ID return false
 *else returning true
 */
public class filterByID implements filter{



	@Override
	public boolean filters(sameScanWifi info) {
		if(info.getID().equals(filter.parm.getParm())){
			
			return true;
		}
		return false;
		
	}

	
	
}
