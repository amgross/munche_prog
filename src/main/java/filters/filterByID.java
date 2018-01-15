package filters;
import java.util.Vector;

import data.dataBaseFunctions;
import data.sameScanWifi;

/**
 * 
 * implement of filter to 'sameScanWifi' for the kml file by ID
 *if it is non good ID return false
 *else returning true
 */
public class filterByID extends filter{



	@Override
	public String toString() {
		return "device ID = " + par1;
	}

	public filterByID(String ID) {
		super(ID, null, null, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<sameScanWifi> filt(Vector<sameScanWifi> database1, Vector<sameScanWifi> nothing) {
		// TODO Auto-generated method stub
		Vector<sameScanWifi> ans = dataBaseFunctions.clone(database1);
		boolean change;
		do{
			change =false;
			for (int i = 0; i < ans.size(); i++) {
				if(!ans.elementAt(i).getID().equals(par1)){
					ans.remove(i);
					change=true;
					break;
				}
			}
		}while(change);
		
		return ans;
	}

	
	
}
