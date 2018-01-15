package data;

public class check {
	public static final Exception Exception = null;
	
	
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
	
}
