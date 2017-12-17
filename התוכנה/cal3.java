import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import org.omg.CORBA._PolicyStub;

public class cal3 {
//  still not working

	private static final int _POWER = 2;
	private static final int _NORM = 10000;
	private static final double _SIGNAL_DIFF = 0.4;
	private static final int _MIN_DIFF = 3;
	private static final int _NO_SIGNAL = -120;
	private static final int _DIFF_NO_SIGNAL = 100;
	
	public static void findManPlace(Vector<sameScanWifi> database,Vector<sameScanWifi> manScans,int num_of_points){
		for(sameScanWifi fixingNow : manScans){
			HashMap<sameScanWifi,Double> scansWeight= new HashMap<sameScanWifi,Double>();
			
			for(sameScanWifi gettingWeight : database){				
				scansWeight.put(gettingWeight, getScanWeight(gettingWeight,fixingNow));
			}
			 database.sort(Comparator.comparing(scan -> -scansWeight.get(scan)));
			 ///////a(database,hashmap,manScans,num_of_points);
			 for
			 scansWeight.get(key)
		}
		for(Vector<sameScanWifi> w:database)
		
//		getWhights( manScans,hmap1);
//		manScans.sort(Comparator.comparing(sample -> -hmap1.get(sample)));
		//double[] array=new double[num_of_points];
		

			
		
	}
	private static double getScanWeight(sameScanWifi gettingWeight, sameScanWifi fixingNow) {
		// TODO Auto-generated method stub
		double scanWeight=1;
		for(wifi currentWifi : fixingNow){
			wifi same=gettingWeight.getWifi(currentWifi.getMAC());
			if(same!=null){
				scanWeight*=findWheight(same.getRSSI(),currentWifi.getRSSI());
			}
			else{
				scanWeight *= findWheight(_DIFF_NO_SIGNAL + currentWifi.getRSSI(),currentWifi.getRSSI());
			}
		}
		return scanWeight;
	}
	private static void getWhights(Vector<sameScanWifi> manScans,HashMap<sameScanWifi,Double> hmap1) {
		// TODO Auto-generated method stub
		 HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		Vector<Vector<wifiWithCoordinate>> sortByMAC=dataBaseFunctions.collectIdenticalMAC(manScans);
		for(Vector<wifiWithCoordinate> currentRouter:sortByMAC){
			hmap.put(currentRouter.firstElement().getMAC(), wifiWithCoordinate.RSSIavg(currentRouter));
		}
		for(sameScanWifi currentScan:manScans){
			double Weight=1;
			for(wifi currentWifi:currentScan){
				Weight*=findWheight(currentWifi.getRSSI(),hmap.get(currentWifi.getMAC()));
			}
			for(Vector<wifiWithCoordinate> router: sortByMAC){
				if(!currentScan.exist(router.firstElement().getMAC())){
					Weight*=findWheight((int)_DIFF_NO_SIGNAL,hmap.get(router.firstElement().getMAC()));
				}
			}
			hmap1.put(currentScan, Weight);
		}
	}
	private double[] arrsort(double[] arr, double num)
	{
		double temp;
		for (int i = 0; i < arr.length; i++) {
			if(num>arr[i]){
				temp=arr[i];
				arr[i]=num;
				arrsort(arr,temp);//רקורסיה
			}
		}
		return arr;
	}
	
	public static double findWheight(int check,int input)
	{ 

			return ((_NORM/((Math.pow(Math.max(Math.abs(check-input),_MIN_DIFF), _SIGNAL_DIFF)*(Math.pow(input, _POWER))))));
		
		
	}
/*	public void avgcomppers(String path1, String path2,String pathOut,int numOfPoint)
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
			data[i][j]=rssi.getRSSI();
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

*/


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
