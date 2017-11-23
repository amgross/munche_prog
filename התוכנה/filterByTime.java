/**
 * 
 * implement of filter to line for the kml file by ID
 *if it is before the relevant time return false
 *else returning true
 */
public class filterByTime implements filter{
	public boolean filters(String[] line,String beginTime) {
		
		String[] partsCheck=line[0].split("-");
		String[] partsChecker=beginTime.split("-");
		if(Integer.parseInt(partsCheck[0])<Integer.parseInt(partsChecker[0])){
			return false;
		}
		if(Integer.parseInt(partsCheck[0])>Integer.parseInt(partsChecker[0])){
			return true;
		}
		if(Integer.parseInt(partsCheck[1])<Integer.parseInt(partsChecker[1])){
			return false;
		}
		if(Integer.parseInt(partsCheck[1])>Integer.parseInt(partsChecker[1])){
			return true;
		}
		partsCheck=partsCheck[2].split(" ");
		partsChecker=partsChecker[2].split(" ");
		if(Integer.parseInt(partsCheck[0])<Integer.parseInt(partsChecker[0])){
			return false;
		}
		if(Integer.parseInt(partsCheck[0])>Integer.parseInt(partsChecker[0])){
			return true;
		}
		partsCheck=partsCheck[1].split(":");
		partsChecker=partsChecker[1].split(":");
		if(Integer.parseInt(partsCheck[0])<Integer.parseInt(partsChecker[0])){
			return false;
		}
		if(Integer.parseInt(partsCheck[0])>Integer.parseInt(partsChecker[0])){
			return true;
		}
		if(Integer.parseInt(partsCheck[1])<Integer.parseInt(partsChecker[1])){
			return false;
		}
		if(Integer.parseInt(partsCheck[1])>Integer.parseInt(partsChecker[1])){
			return true;
		}if(Integer.parseInt(partsCheck[2])<Integer.parseInt(partsChecker[2])){
			return false;
		}
		if(Integer.parseInt(partsCheck[2])>Integer.parseInt(partsChecker[2])){
			return true;
		}
		return true;
		
	}


}
