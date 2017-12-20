import java.util.Iterator;

public class filterBySSID implements filter{
	@Override
	/**
	 * filtering scan by SSID
	 * return true if not all the items removed
	 * else return false
	 */
	public boolean filters(sameScanWifi info) {
		// TODO Auto-generated method stub
		for(Iterator<wifi> iter = info.iterator(); iter.hasNext(); ) {
			wifi current=iter.next();
			if(!current.getSSID().equals(filter.parm.getParm())){
				iter.remove();
			}
		}
		if(info.size()==0){
			return false;
		}
		return true;
	}
}
