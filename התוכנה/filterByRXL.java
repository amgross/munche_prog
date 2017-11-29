/**
 * 
 * implement of filter to 'sameScanWifi' for the kml file by ID
 *every wifi that his rxl to low, remove it
 *if all the items 'removed' return false
 *else returning true
 */
public class filterByRXL implements filter{
	public boolean filters(sameScanWifi info,String min) {
		// TODO Auto-generated method stub
		
		for(wifi current:info){
			if(current.getRSSI()<Integer.parseInt(min)){
				if(!info.remove(current)){
					return false;
				}
			}
		}
		return true;
	}
}
