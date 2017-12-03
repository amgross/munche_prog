import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class dataBase {
	/**
	 * delete extra mac from database
	 * @param database
	 */
	public static void deleteExtraMac(Vector<sameScanWifi> wifis){
		 HashMap< String,sameScanWifi> hmap = new HashMap< String,sameScanWifi>();
		 for (Iterator<sameScanWifi> sameScanWifiIterator = wifis.iterator(); sameScanWifiIterator.hasNext(); ) {
			 sameScanWifi currentSameScanWifi=sameScanWifiIterator.next();
	    	  for(wifi currentWifi:currentSameScanWifi){
	    		  if(hmap.get(currentWifi.getMAC())==null){
	    			  hmap.put(currentWifi.getMAC(), currentSameScanWifi);
	    		  }
	    		  else{
	    			  for(wifi sameMac:hmap.get(currentWifi.getMAC())){
	    				  if(sameMac.getMAC().equals(currentWifi.getMAC())){
	    					  if(sameMac.getRSSI()>currentWifi.getRSSI()){
	    						  currentSameScanWifi.remove(currentWifi);
	    					  }
	    					  else{
	    						  hmap.get(currentWifi.getMAC()).remove(sameMac);
	    						  hmap.replace(currentWifi.getMAC(),  currentSameScanWifi);
	    					  }
	    				  }
	    			  }
	    		  }
	    	  }
	    	  if(currentSameScanWifi.size()==0){
	    		  sameScanWifiIterator.remove();
	    	  }
	      }

	}
	
	/**
	 * every wifi with the same mac collected into one vector of wifi with coordinate and all the vectors in one vector
	 * @param database
	 * @return vector of vectors of wifis with the same mac
	 */
	public static Vector<Vector<wifiWithCoordinate>> collectIdenticalMAC(Vector<sameScanWifi> database){
		Vector<Vector<wifiWithCoordinate>> IdenticalMAC=new Vector<Vector<wifiWithCoordinate>>();
		 HashMap< String,Vector<wifiWithCoordinate>> hmap = new HashMap< String,Vector<wifiWithCoordinate>>();
		 for (sameScanWifi currentSameScanWifi: database) {
	    	  for(wifi currentWifi:currentSameScanWifi){
	    		  if(hmap.get(currentWifi.getMAC())==null){
	    			  Vector<wifiWithCoordinate> temp=new Vector<wifiWithCoordinate>();
	    			  IdenticalMAC.add(temp);
	    			  temp.add((new wifiWithCoordinate(currentSameScanWifi,currentWifi)));
	    			  hmap.put(currentWifi.getMAC(), temp);
	    		  }
	    		  else{
	    			  hmap.get(currentWifi.getMAC()).add(new wifiWithCoordinate(currentSameScanWifi,currentWifi));
	    		  }
	    	  }
	      }
		 return IdenticalMAC;
	}

}
