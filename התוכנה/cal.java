
public class cal {

	/*
	 * for example: avg0+=arr[i][0]/math.pow(arr[i][4],2); avg1+=arr[i][1]/math.pow(arr[i][4],2);
	 * avg2+=arr[i][2]/math.pow(arr[i][4],2);avg3[i][3]/math.pow(arr[i][4],2);avg4+=(1/math.pow(arr[i][4],2));
	 */
	private double[] avgcomp(double[][] arr)
	{
		double avg0=0,avg1=0,avg2=0,avg3=0,avg4=0;
		for (int i = 0; i < arr[0].length; i++) {
			avg0+=arr[i][0]/Math.pow(arr[i][4],2); 
			avg1+=arr[i][1]/Math.pow(arr[i][4],2); 
			avg2+=arr[i][2]/Math.pow(arr[i][4],2);
			avg3+=arr[i][3]/Math.pow(arr[i][4],2);
			avg4+=(1/Math.pow(arr[i][4],2));
		}
		double[] avg={avg0/avg4,avg1/avg4,avg2/avg4,avg3/avg4};

		return avg;
	}
}
