
import java.util.List;
/**
 * 
 * interface of filter to line for the kml file
 *every non relevant wifi, changes his rxl to 0
 *if all the items 'removed' return false
 *else returning true
 */
public interface filter {
	boolean filters(sameScanWifi info, String par);

}
