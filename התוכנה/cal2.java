import java.util.HashMap;
import java.util.Vector;

import org.omg.CORBA._PolicyStub;

public class cal2 {

	private final int _POWER = 2;
	private final double _NORM = 10000;
	private final double _SIGNAL_DIFF = 0.4;
	private final double _MIN_DIFF = 3;
	private final double _NO_SIGNAL = -120;
	private final double _DIFF_NO_SIGNAL = 100;

	private double[] arrsort(double[] arr, double num)
	{
		double temp;
		for (int i = 0; i < arr.length; i++) {
			if(num>arr[i]){
				temp=arr[i];
				arr[i]=num;
				arrsort(arr,temp);
			}
		}
		return arr;
	}
	
	public void cal2(double[][] arr,double[] arr1,int j)
	{
		for (int i = 0; i < arr1.length; i++) {
		double a=((_NORM/((Math.pow(Math.max(Math.abs(arr[i][j]-arr1[i]),_MIN_DIFF), _SIGNAL_DIFF)*(Math.pow(arr1[i], _POWER))))));
		
		}
	}
	public void avgcomppers(String path1, String path2,String pathOut,int numOfPoint)
	{
		int i=0, j=0;
		
		double[] arr=new double[numOfPoint];
		WiggleWifi a=new WiggleWifi();
		WiggleWifi b=new WiggleWifi();
		int input=0;
		Vector<sameScanWifi> database=new Vector<sameScanWifi>();
		 Vector<Vector<wifiWithCoordinate>> e=new Vector<Vector<wifiWithCoordinate>>();
		Vector<sameScanWifi> c=new Vector<sameScanWifi>();
		double[][] data=new double[e.size()][10];
		double[][] time=new double[e.size()][10];
		double[] avgRssi=new double[e.size()];
		Vector<sameScanWifi> d=new Vector<sameScanWifi>();
		e=dataBaseFunctions.collectIdenticalMAC(a.collectInfoFromWiggleWifi(path1));//vector<sameScanWifi>
		d=b.collectInfoFromWiggleWifi(path2);
		for(Vector<wifiWithCoordinate> wifi: e)
		{
		for(wifi rssi: wifi)
		{
			data[i][j]=rssi.getRSSI();//מטריצה הפוכה
			i++;
			avgRssi[j]=rssi.getRSSI();
		}
		avgRssi[j]/=i;
		i=0;
		j++;
	//	input/=avg1;
	//	avg1=(Math.abs(input)-Math.abs(a)
		}
		

	}




	/*
	 * for example: avg0+=arr[i][0]/math.pow(arr[i][4],2); avg1+=arr[i][1]/math.pow(arr[i][4],2);
	 * avg2+=arr[i][2]/math.pow(arr[i][4],2);avg3[i][3]/math.pow(arr[i][4],2);avg4+=(1/math.pow(arr[i][4],2));
	 */
	public static wifiWithCoordinate avgcomp(Vector<wifiWithCoordinate> sameWifi)
	{
		double avgAltitude=0,avgLatitude=0,avLongitude=0,avg=0;
		for (wifiWithCoordinate wifi: sameWifi) {
			avgAltitude += wifi.getAltitude()/Math.pow(wifi.getRSSI(),2); 
			avgLatitude += wifi.getLatitude()/Math.pow(wifi.getRSSI(),2); 
			avLongitude += wifi.getLongitude()/Math.pow(wifi.getRSSI(),2);
			avg += (1/Math.pow(wifi.getRSSI(),2));
		}
		wifiWithCoordinate realPlace=new wifiWithCoordinate();
		realPlace.setAltitude(avgAltitude/avg);
		realPlace.setLatitude(avgLatitude/avg);
		realPlace.setLongitude(avLongitude/avg);
		realPlace.setMAC(sameWifi.firstElement().getMAC());
		realPlace.setSSID(sameWifi.firstElement().getSSID());

		return realPlace;
	}
}
