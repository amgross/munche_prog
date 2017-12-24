
import java.util.Vector;
/**
 * 
 * interface of filter to 'sameScanWifi' for the kml file
 *every non relevant wifi, remove it
 *if all the items 'removed' return false
 *else returning true
 */
public class filterTree {
	filterTree right;
	filterTree left;
	filter current;
	public void setRight(filterTree right) {
		this.right = right;
	}
	public void setLeft(filterTree left) {
		this.left = left;
	}
	public void setCurrent(filter current) {
		this.current = current;
	}
	@Override
	public String toString() {
		String print="";
		if(left!=null){
			print += "(" + left + ")";
		}
		print += current;
		if(right!=null){
			print += "(" + right + ")";
		}
		return print;
	}

	public filterTree(String filter){
		current = null;
		left = null;
		right = null;
		boolean filte=false;
		String send = "";
		int count = 0;
		for (int i = 0; i < filter.length(); i++) {
			if(filter.charAt(i)=='('){
				if(filte||(count==0&&!send.equals(""))){
					current = filterFromString(send);
					send = "";
					filte = false;
				}
				else {
					filte = false;
					if(count!=0){
						send += filter.charAt(i);
					}else{
						send="";
					}
				}
				count++;
			}else{
				if(filter.charAt(i) == ')'){
					count-- ;
					if(count == 0){
						if(current==null){
							left = new filterTree(send);
							send = "";
							filte = true;
						}
						else{
							right = new filterTree(send);
							return;
						}
					}
					else{
						send += filter.charAt(i);
					}
				}
				else{
					send += filter.charAt(i);
				}
			}
			if(send.equals(filter)){
				current = filterFromString(send);
			}
		}
	}
	private filter filterFromString(String filter) {
		// TODO Auto-generated method stub
		if(filter.equals("AND")){
			return new andGate();
		}
		if(filter.equals("OR")){
			return new orGate();
		}
		if(filter.equals("NOT")){
			return new notGate();
		}
		if(filter.startsWith("device ID = ")){
			return new filterByID(filter.substring(12, filter.length()));
		}
		if(filter.contains("AND")){
			return new filterByCoordinates(filter.substring(0, filter.indexOf('<')-1),
					filter.substring(filter.indexOf('<',filter.indexOf('<')+1)+2, filter.indexOf('A')-1),
					filter.substring(filter.indexOf('D')+2, filter.indexOf('<',filter.indexOf('<',filter.indexOf('<')+1)+1)-1),
					filter.substring(filter.indexOf('<',filter.indexOf('<',filter.indexOf('<',filter.indexOf('<')+1)+1)+1)+2,filter.length()));
		}
		return new filterByTime(filter.substring(0, filter.indexOf('<')-1)
				,filter.substring(filter.indexOf('<',filter.indexOf('<')+1)+2,filter.length()));
	}
	public filterTree(filter current, filterTree left, filterTree right) {
		super();
		this.right = right;
		this.left = left;
		this.current = current;
	}
	public filterTree(filter current) {
		super();
		this.current = current;
	}
	public Vector<sameScanWifi> filt(Vector<sameScanWifi> current_dataBase, Vector<sameScanWifi> local_dataBase) {
		// TODO Auto-generated method stub
		Vector<sameScanWifi> leftData, rightData;
		if(current == null){
			return current_dataBase;
		}
		if(right == null){
			return current.filt(current_dataBase, null);
		}
		rightData = this.right.filt(current_dataBase, local_dataBase);
		if(this.left != null){
			leftData = this.left.filt(current_dataBase, local_dataBase);
			return this.current.filt(leftData, rightData);
		}
		return current.filt(rightData, local_dataBase);
	}



}
