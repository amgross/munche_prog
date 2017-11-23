/**
 * 
 * implement of filter to line for the kml file by ID
 *every wifi that his rxl to low, changes his rxl to 0
 *if all the items 'removed' return false
 *else returning true
 */
public class filterByRXL implements filter{
	public boolean filters(String[] line,String min) {
		// TODO Auto-generated method stub
		int numOfWIFIs=Integer.parseInt(line[5]);
		boolean ans=false;
		for(int i=0;i<numOfWIFIs;i++){
			if(Integer.parseInt(line[i*4+9])<Integer.parseInt(min)){
				line[i*4+9]="0";
			}
			else{
				ans=true;
			}
		}
		return ans;
	}
}
