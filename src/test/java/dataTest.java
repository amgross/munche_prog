import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Vector;

import org.junit.Test;

import data.*;

public class dataTest {
	/**
	 * check the check class
	 */
	@Test
	public void checkGoodTimeTest(){
		try {
			check.checkTime("95-04-26 15:25:56");
			check.checkTime("2007-12-01 23:00:59");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	@Test(expected = java.lang.Exception.class)
	public void checkBadTimeTest1() throws Exception{
		
			check.checkTime("95/04/26 15:25:56");
	}
	@Test(expected = java.lang.Exception.class)
	public void checkBadTimeTest2() throws Exception{
		
			check.checkTime("2007-12-01 23:00:60");
	}
	
	/**
	 * test of little database
	 */
	@Test
	public void simpleDatabase(){
		Database a=new Database();
		a.editCsv("test\\writeKmlTest\\good2.csv");
		assertEquals(1, a.getNumOfScans());
		assertEquals(4, a.getNum_of_routers());
	}
	
	/**
	 * test algorithm 2 with the database that we got in the excel
	 */
	@Test
	public void algo2FromExelExample(){
		sameScanWifi input=new sameScanWifi();
		wifi temp;
		temp=new wifi(); temp.setMAC("aa:aa:aa:aa"); temp.setSSID("a"); temp.setRSSI("-50"); input.insert(temp);
		temp=new wifi(); temp.setMAC("bb:bb:bb:bb"); temp.setSSID("b"); temp.setRSSI("-70"); input.insert(temp);
		temp=new wifi(); temp.setMAC("cc:cc:cc:cc"); temp.setSSID("c"); temp.setRSSI("-90"); input.insert(temp);
		Vector<sameScanWifi> man=new Vector<sameScanWifi>();
		man.add(input);
		Vector<sameScanWifi> dataBase=new Vector<sameScanWifi>();
		sameScanWifi tempScan;
		tempScan=new sameScanWifi();
		tempScan.setAltitude("650");tempScan.setLatitude("32.103");tempScan.setLongitude("35.208");
		temp=new wifi(); temp.setMAC("aa:aa:aa:aa"); temp.setSSID("a"); temp.setRSSI("-62"); tempScan.insert(temp);
		temp=new wifi(); temp.setMAC("bb:bb:bb:bb"); temp.setSSID("b"); temp.setRSSI("-79"); tempScan.insert(temp);
		temp=new wifi(); temp.setMAC("cc:cc:cc:cc"); temp.setSSID("c"); temp.setRSSI("-71"); tempScan.insert(temp);
		dataBase.add(tempScan);
		tempScan=new sameScanWifi();
		tempScan.setAltitude("1000000");tempScan.setLatitude("1000000");tempScan.setLongitude("1000000");
		dataBase.add(tempScan);
		tempScan=new sameScanWifi();
		tempScan.setAltitude("660");tempScan.setLatitude("32.105");tempScan.setLongitude("35.205");
		temp=new wifi(); temp.setMAC("aa:aa:aa:aa"); temp.setSSID("a"); temp.setRSSI("-82"); tempScan.insert(temp);
		temp=new wifi(); temp.setMAC("cc:cc:cc:cc"); temp.setSSID("c"); temp.setRSSI("-82"); tempScan.insert(temp);
		temp=new wifi(); temp.setMAC("dd:dd:dd:dd"); temp.setSSID("c"); temp.setRSSI("-10000"); tempScan.insert(temp);
		dataBase.add(tempScan);
		tempScan=new sameScanWifi();
		tempScan.setAltitude("680");tempScan.setLatitude("32.103");tempScan.setLongitude("35.307");
		temp=new wifi(); temp.setMAC("bb:bb:bb:bb"); temp.setSSID("b"); temp.setRSSI("-89"); tempScan.insert(temp);
		temp=new wifi(); temp.setMAC("cc:cc:cc:cc"); temp.setSSID("c"); temp.setRSSI("-73"); tempScan.insert(temp);
		dataBase.add(tempScan);
		tempScan=new sameScanWifi();
		tempScan.setAltitude("1000000");tempScan.setLatitude("1000000");tempScan.setLongitude("1000000");
		temp=new wifi(); temp.setMAC("aa:aa:aa:aa"); temp.setSSID("a"); temp.setRSSI("-91"); tempScan.insert(temp);
		dataBase.add(tempScan);
		findPlaces.findManPlace(dataBase,man,3);
		assertEquals(658.0198453,input.getAltitude(),0.0001);
		assertEquals(32.1034296,input.getLatitude(),0.0001);
		assertEquals(35.22673264,input.getLongitude(),0.0001);
	}
	

	/**
	 * test the function collectIdenticalMAC with database that have two wifis, 
	 * one of them five times and one have three
	 */
	@Test
	public void collectDoubleMACTest(){
		Vector<sameScanWifi> myDatabase=new Vector<sameScanWifi>();
		sameScanWifi temp=new sameScanWifi();
		wifi Awifi=new wifi();Awifi.setMAC("AA:AA:AA:AA");Awifi.setChannel("1");temp.insert(Awifi);
		wifi Bwifi=new wifi();Bwifi.setMAC("BB:BB:BB:BB");Bwifi.setChannel("1");temp.insert(Bwifi);
		Awifi=new wifi();Awifi.setMAC("AA:AA:AA:AA");Awifi.setChannel("2");temp.insert(Awifi);
		Awifi=new wifi();Awifi.setMAC("AA:AA:AA:AA");Awifi.setChannel("3");temp.insert(Awifi);
		myDatabase.add(temp);

		temp=new sameScanWifi();
		Bwifi=new wifi();Bwifi.setMAC("BB:BB:BB:BB");Bwifi.setChannel("2");temp.insert(Bwifi);
		Bwifi=new wifi();Bwifi.setMAC("BB:BB:BB:BB");Bwifi.setChannel("3");temp.insert(Bwifi);
		Bwifi=new wifi();Bwifi.setMAC("BB:BB:BB:BB");Bwifi.setChannel("4");temp.insert(Bwifi);
		Bwifi=new wifi();Bwifi.setMAC("BB:BB:BB:BB");Bwifi.setChannel("5");temp.insert(Bwifi);
		
		myDatabase.add(temp);
		Vector<Vector<wifiWithCoordinate>> ans=dataBaseFunctions.collectIdenticalMAC(myDatabase);
		
		assertEquals(2,ans.size());
		
		assertTrue((ans.firstElement().size()==3&&ans.lastElement().size()==5)||
				(ans.firstElement().size()==5&&ans.lastElement().size()==3));
	}

}
