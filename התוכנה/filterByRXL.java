import java.util.Iterator;

/**
 * 
 * implement of filter to 'sameScanWifi' for the kml file by ID
 *every wifi that his rxl to low, remove it
 *if all the items 'removed' return false
 *else returning true
 */
public class filterByRXL implements filter{
	public boolean filters(sameScanWifi info) {
		// TODO Auto-generated method stub
		for(Iterator<wifi> iter = info.iterator(); iter.hasNext(); ) {
			wifi current=iter.next();
			if(current.getRSSI()<Integer.parseInt(filter.parm.getParm())){
				iter.remove();
			}
		}
		if(info.size()==0){
			return false;
		}
		return true;
	}
}
