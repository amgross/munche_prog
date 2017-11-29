/**
 * 
 * implement of filter to line for the kml file by ID
 *every wifi that his rxl to low, changes his rxl to 0
 *if all the items 'removed' return false
 *else returning true
 */
public class filterByRXL implements filter{
	public boolean filters(sameScanWifi info,String min) {
		// TODO Auto-generated method stub
		
		for(wifi current:info){
			if(current.getRSSI()<Integer.parseInt(min)){
				info.remove(current);
			}
		}
		if(info.size()==0){
			return false;
		}
		return true;
	}
}
