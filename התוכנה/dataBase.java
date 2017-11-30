import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class dataBase {
	public static void deleteExtraMac(Vector<sameScanWifi> wifis){
		 HashMap< String,sameScanWifi> hmap = new HashMap< String,sameScanWifi>();
		 for (Iterator<sameScanWifi> sameScanWifiIterator = wifis.iterator(); sameScanWifiIterator.hasNext(); ) {
			 sameScanWifi a=sameScanWifiIterator.next();
	    	  for(wifi b:a){
	    		  if(hmap.get(b.getMAC())==null){
	    			  hmap.put(b.getMAC(), a);
	    		  }
	    		  else{
	    			  for(wifi c:hmap.get(b.getMAC())){
	    				  if(c.getMAC().equals(b.getMAC())){
	    					  if(c.getRSSI()>b.getRSSI()){
	    						  a.remove(b);
	    					  }
	    					  else{
	    						  hmap.get(b.getMAC()).remove(c);
	    					  }
	    				  }
	    			  }
	    		  }
	    	  }
	    	  if(a.size()==0){
	    		  sameScanWifiIterator.remove();
	    	  }
	      }

	}

}
