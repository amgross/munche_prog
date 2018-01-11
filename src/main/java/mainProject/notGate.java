package mainProject;
import java.util.Vector;

public class notGate extends filter {

	public notGate() {
		super(null, null, null, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	Vector<sameScanWifi> filt(Vector<sameScanWifi> currentDatabase, Vector<sameScanWifi> localDatabase) {
		// TODO Auto-generated method stub
		Vector<sameScanWifi> ans = dataBaseFunctions.clone(localDatabase);
		boolean change;
		do{
			change =false;
			for (int i = 0; i < ans.size(); i++) {
				if(dataBaseFunctions.contain(currentDatabase, ans.elementAt(i))){
					ans.remove(i);
					change=true;
					break;
				}
			}
		}while(change);

		return ans;
	}

	@Override
	public String toString() {
		return "NOT";
	}

}
