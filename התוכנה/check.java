
public class check {
	public static final Exception Exception = null;
	/**
	 * if line in info is not good, throw exeption
	 * @param info
	 * @param lineNum
	 * @throws java.lang.Exception 
	 */
	public static void checkInfo(String[][] info, int lineNum) throws java.lang.Exception {
		// TODO Auto-generated method stub
		checkTime(info[2][lineNum]);
		Integer.parseInt(info[3][lineNum]);
		Integer.parseInt(info[4][lineNum]);
		Double.parseDouble(info[5][lineNum]);
		Double.parseDouble(info[6][lineNum]);
		Double.parseDouble(info[7][lineNum]);
	}
	
	/**
	 * if the string is not good, throws exeption
	 * @param userAns
	 * @throws java.lang.Exception
	 */
	public static void checkTime(String userAns) throws java.lang.Exception {
		// TODO Auto-generated method stub
		String[] Checker=userAns.split("-");
		Integer.parseInt(Checker[0]);
		if(Integer.parseInt(Checker[1])>12||Integer.parseInt(Checker[1])<1){
			throw Exception;
		}
		Checker=Checker[2].split(" ");
		if(Integer.parseInt(Checker[0])>31||Integer.parseInt(Checker[0])<1){
			throw Exception;
		}
		Checker=Checker[1].split(":");
		if(Integer.parseInt(Checker[0])>23||Integer.parseInt(Checker[0])<0){
			throw Exception;
		}
		if(Integer.parseInt(Checker[1])>59||Integer.parseInt(Checker[1])<0){
			throw Exception;
		}
		if(Integer.parseInt(Checker[2])>59||Integer.parseInt(Checker[2])<0){
			throw Exception;
		}
	}
	/**
	 * if line from csv file is not good, throw exeption
	 * @param info
	 * @throws java.lang.Exception 
	 */
	public static void checkLine(String[] parts) throws java.lang.Exception {
		// TODO Auto-generated method stub
		check.checkTime(parts[0]);
		Double.parseDouble(parts[2]);
		Double.parseDouble(parts[3]);
		Double.parseDouble(parts[4]);
		for(int j=6;j<Integer.parseInt(parts[5])*4+6;j+=4){
			Integer.parseInt(parts[j+2]);
			Integer.parseInt(parts[j+3]);
		}
	}
}
