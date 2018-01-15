package filters;
import java.util.Vector;

import data.check;
import data.dataBaseFunctions;
import data.sameScanWifi;

/**
 * 
 * implement of filter to 'sameScanWifi' for the kml file by ID
 *if it is before the relevant time return false
 *else returning true
 */
public class filterByTime extends filter{
	@Override
	public String toString() {
		return par1 +" < time < " + par2;
	}

	public filterByTime(String minTime, String maxTime) throws Exception {
		super(minTime, maxTime, null, null);
		check.checkTime(minTime);
		check.checkTime(maxTime);
		// TODO Auto-generated constructor stub
	}

	private boolean timeFilter(sameScanWifi info) {

		String[] partsCheck=info.getTime().split("-");
		String[] partsChecker=par1.split("-");
		if(Integer.parseInt(partsCheck[0])<Integer.parseInt(partsChecker[0])){
			return false;
		}
		if(!(Integer.parseInt(partsCheck[0])>Integer.parseInt(partsChecker[0]))){

			if(Integer.parseInt(partsCheck[1])<Integer.parseInt(partsChecker[1])){
				return false;
			}
			if(!(Integer.parseInt(partsCheck[1])>Integer.parseInt(partsChecker[1]))){

				partsCheck=partsCheck[2].split(" ");
				partsChecker=partsChecker[2].split(" ");
				if(Integer.parseInt(partsCheck[0])<Integer.parseInt(partsChecker[0])){
					return false;
				}
				if(!(Integer.parseInt(partsCheck[0])>Integer.parseInt(partsChecker[0]))){

					partsCheck=partsCheck[1].split(":");
					partsChecker=partsChecker[1].split(":");
					if(Integer.parseInt(partsCheck[0])<Integer.parseInt(partsChecker[0])){
						return false;
					}
					if(!(Integer.parseInt(partsCheck[0])>Integer.parseInt(partsChecker[0]))){

						if(Integer.parseInt(partsCheck[1])<Integer.parseInt(partsChecker[1])){
							return false;
						}
						if(!(Integer.parseInt(partsCheck[1])>Integer.parseInt(partsChecker[1]))){
							if(Integer.parseInt(partsCheck[2])<Integer.parseInt(partsChecker[2])){
								return false;
							}

						}
					}
				}
			}
		}
		partsCheck=info.getTime().split("-");
		partsChecker=par2.split("-");
		if(Integer.parseInt(partsCheck[0])>Integer.parseInt(partsChecker[0])){
			return false;
		}
		if(!(Integer.parseInt(partsCheck[0])<Integer.parseInt(partsChecker[0]))){

			if(Integer.parseInt(partsCheck[1])>Integer.parseInt(partsChecker[1])){
				return false;
			}
			if(!(Integer.parseInt(partsCheck[1])<Integer.parseInt(partsChecker[1]))){

				partsCheck=partsCheck[2].split(" ");
				partsChecker=partsChecker[2].split(" ");
				if(Integer.parseInt(partsCheck[0])>Integer.parseInt(partsChecker[0])){
					return false;
				}
				if(!(Integer.parseInt(partsCheck[0])<Integer.parseInt(partsChecker[0]))){

					partsCheck=partsCheck[1].split(":");
					partsChecker=partsChecker[1].split(":");
					if(Integer.parseInt(partsCheck[0])>Integer.parseInt(partsChecker[0])){
						return false;
					}
					if(!(Integer.parseInt(partsCheck[0])<Integer.parseInt(partsChecker[0]))){

						if(Integer.parseInt(partsCheck[1])>Integer.parseInt(partsChecker[1])){
							return false;
						}
						if(!(Integer.parseInt(partsCheck[1])<Integer.parseInt(partsChecker[1]))){
							if(Integer.parseInt(partsCheck[2])>Integer.parseInt(partsChecker[2])){
								return false;
							}

						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public Vector<sameScanWifi> filt(Vector<sameScanWifi> database1, Vector<sameScanWifi> nothing) {
		// TODO Auto-generated method stub
		Vector<sameScanWifi> ans = dataBaseFunctions.clone(database1);
		boolean change;
		do{
			change =false;
			for (int i = 0; i < ans.size(); i++) {
				if(!timeFilter(ans.elementAt(i))){
					ans.remove(i);
					change=true;
					break;
				}
			}
		}while(change);

		return ans;
	}


}
