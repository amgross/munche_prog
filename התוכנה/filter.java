
import java.util.List;
/**
 * 
 * interface of filter to 'sameScanWifi' for the kml file
 *every non relevant wifi, remove it
 *if all the items 'removed' return false
 *else returning true
 */
public interface filter {
	class parm{
		static String myParm;

		public static void setParm(String parm) {
			filter.parm.myParm = parm;
		}

		public static String getParm() {
			return myParm;
		}

		
	}
	boolean filters(sameScanWifi dataBase);

}
