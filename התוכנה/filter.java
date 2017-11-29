
import java.util.List;
/**
 * 
 * interface of filter to 'sameScanWifi' for the kml file
 *every non relevant wifi, remove it
 *if all the items 'removed' return false
 *else returning true
 */
public interface filter {
	boolean filters(sameScanWifi info, String par);

}
