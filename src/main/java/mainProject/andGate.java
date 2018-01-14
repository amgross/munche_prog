package mainProject;
import java.util.Iterator;
import java.util.Vector;

public class andGate extends filter {

	@Override
	public String toString() {
		return "AND";
	}

	public andGate() {
		super(null, null, null, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<sameScanWifi> filt(Vector<sameScanWifi> database1, Vector<sameScanWifi> Database2) {
		// TODO Auto-generated method stub
		Vector<sameScanWifi> ans = dataBaseFunctions.clone(database1);
		boolean change;
		do{
			change =false;
			for (int i = 0; i < ans.size(); i++) {
				if(!dataBaseFunctions.contain(Database2, ans.elementAt(i))){
					ans.remove(i);
					change=true;
					break;
				}
			}
		}while(change);

		return ans;
	}

}
