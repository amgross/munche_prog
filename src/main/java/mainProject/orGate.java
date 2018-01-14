package mainProject;
import java.util.Vector;

public class orGate extends filter {

	public orGate() {
		super(null, null, null, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<sameScanWifi> filt(Vector<sameScanWifi> database1, Vector<sameScanWifi> Database2) {
		// TODO Auto-generated method stub
		Vector<sameScanWifi> ans = dataBaseFunctions.clone(database1);
		dataBaseFunctions.unit(ans, Database2);
		return ans;
	}

	@Override
	public String toString() {
		return "OR";
	}

}
