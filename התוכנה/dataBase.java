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

}
