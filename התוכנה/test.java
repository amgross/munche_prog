
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import org.junit.Test;

public class test {
	/**
	 * checks the toCsv class
	 * @throws IOException
	 */
	@Test
	public void test1ToCsv() throws IOException {
		Vector<sameScanWifi> dataBase=WiggleWifi.collectInfoFromWiggleWifi("test\\toCsvTest\\test1");
		CSV.printFileFromDataBaseToCSV(dataBase,"test\\toCsvTest\\test1\\wifi.csv");
		FileReader fr = new FileReader("test\\toCsvTest\\test1\\wifi.csv");
		BufferedReader br = new BufferedReader(fr);
		assertEquals("2017-10-27 16:13:51,ONEPLUS A3003_28_171012,32.16876665,34.81320794,37.0,9,osnatg370,c0:ac:54:f5:7b:a7,11,-86,NGOGA,30:b5:c2:fe:aa:56,6,-87,DIRECT-35-HP DeskJet 3830 series,98:e7:f4:c6:4b:37,6,-94,BezeqFree,6a:12:f5:f9:5e:71,8,-85,Howmanyistwo,74:da:38:50:77:f2,2,-81,Robert1,3c:1e:04:03:7f:17,1,-82,tslila,c4:3d:c7:5a:79:1c,11,-89,OP2,a0:63:91:69:f6:af,1,-80,Lubelsky,c0:4a:00:ba:e8:f6,13,-88",br.readLine());
		br.close();
	}

	@Test
	public void test2ToCsv() throws IOException {
		Vector<sameScanWifi> dataBase=WiggleWifi.collectInfoFromWiggleWifi("test\\toCsvTest\\test2");
		CSV.printFileFromDataBaseToCSV(dataBase,"test\\toCsvTest\\test2\\wifi.csv");
		FileReader fr = new FileReader("test\\toCsvTest\\test2\\wifi.csv");
		BufferedReader br = new BufferedReader(fr);
		assertEquals("2017-10-30 15:31:24,V510_IS_CY_MT6735M_L1_V1.4,32.103325,35.2089866666667,690.3,10,Ariel_University,1c:b9:c4:16:d2:88,11,-50,Ariel_University,1c:b9:c4:16:28:e8,1,-58,Ariel_University,34:8f:27:20:8c:18,1,-70,Ariel_University,8c:0c:90:2d:75:e8,1,-86,Ariel_University,24:79:2a:2c:3d:68,11,-78,Ariel_University,1c:b9:c4:12:90:b8,11,-88,Ariel_University,8c:0c:90:2f:ca:18,6,-88,Ariel_University,1c:b9:c4:16:f4:28,11,-88,Ariel_University,1c:b9:c4:12:af:78,6,-89,,00:25:86:cb:fd:f8,9,-72",br.readLine());
		br.close();
	}
	@Test
	public void test3ToCsv() throws IOException {
		Vector<sameScanWifi> dataBase=WiggleWifi.collectInfoFromWiggleWifi("test\\toCsvTest\\test3");
		CSV.printFileFromDataBaseToCSV(dataBase,"test\\toCsvTest\\test3\\wifi.csv");
		FileReader fr = new FileReader("test\\toCsvTest\\test3\\wifi.csv");
		BufferedReader br = new BufferedReader(fr);
		assertEquals(null,br.readLine());
		br.close();
	}
	@Test
	public void test4ToCsv() throws IOException {
		Vector<sameScanWifi> dataBase=WiggleWifi.collectInfoFromWiggleWifi("test\\toCsvTest\\test4");
		CSV.printFileFromDataBaseToCSV(dataBase,"test\\toCsvTest\\test4\\wifi.csv");
		FileReader fr = new FileReader("test\\toCsvTest\\test4\\wifi.csv");
		BufferedReader br = new BufferedReader(fr);
		assertEquals("2017-10-27 16:34:47,MMB29K.A520FXXU1AQF3,32.1675586234778,34.8099447973073,61.7000007629395,4,Mouly,7c:b7:33:2e:76:73,11,-78,888Corp,0a:8d:db:6e:71:6d,1,-84,888Corp,0a:8d:db:6e:71:bf,1,-84,smartsell,78:e8:b6:69:2a:e9,11,-87",br.readLine());
		assertEquals(null,br.readLine());
		br.close();
	}
	
	
	/**
	 * check the collectInfoFromCSV with bad values
	 * @throws Exception
	 */
	@Test(expected = java.lang.Exception.class)
	public void test1collectInfoFromCSV() throws Exception {
		CSV.collectInfoFromCSV("test\\writeKmlTest\\bad1.csv");
	}
	@Test(expected = java.lang.Exception.class)
	public void test2collectInfoFromCSV() throws Exception {
		CSV.collectInfoFromCSV("test\\writeKmlTest\\bad2.csv");
	}
	/**
	 * check the toKml class
	 * @throws Exception 
	 */
	@Test
	public void test3WriteKml() throws Exception {
		FileWriter fw;             // statement
		try {                 //   try write the file 
			fw = new FileWriter("test\\writeKmlTest\\wifi.kml");
			PrintWriter outs = new PrintWriter(fw);
			outs.print("");
			fw.close();      // close fw writer
			outs.close();     //close outs writer
		} catch (IOException e) {     // exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<sameScanWifi> dataBase=CSV.collectInfoFromCSV("test\\writeKmlTest\\good1.csv");
		KML.printFileFromDataBaseToKML(dataBase, "test\\writeKmlTest\\wifi.kml");
		assertTrue(genericFunctions.countLines("test\\writeKmlTest\\wifi.kml")>100);
	}
	@Test
	public void test4WriteKml() throws Exception {
		FileWriter fw;             // statement
		try {                 //   try write the file 
			fw = new FileWriter("test\\writeKmlTest\\wifi.kml");
			PrintWriter outs = new PrintWriter(fw);
			outs.print("");
			fw.close();      // close fw writer
			outs.close();     //close outs writer
		} catch (IOException e) {     // exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<sameScanWifi> dataBase=CSV.collectInfoFromCSV("test\\writeKmlTest\\good2.csv");
		KML.printFileFromDataBaseToKML(dataBase, "test\\writeKmlTest\\wifi.kml");
		assertTrue(genericFunctions.countLines("test\\writeKmlTest\\wifi.kml")>18);
	}
	
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
	 * check the filters
	 * @throws Exception 
	 */
	@Test
	public void FilterByTimeGoodTest() throws Exception {
		sameScanWifi checkTime=new  sameScanWifi();
		checkTime.setTime("2010-12-01 23:00:59");
		Vector<sameScanWifi> test = new Vector<sameScanWifi>();
		test.add(checkTime);
		filter TimeFilterTest= new filterByTime("2007-12-01 23:00:59", "2020-12-01 23:00:59");
		assertEquals(1, TimeFilterTest.filt(test, null).size());
		TimeFilterTest= new filterByTime("2010-12-01 23:00:59", "2010-12-01 23:00:59");
		assertEquals(1, TimeFilterTest.filt(test, null).size());
	} 
	@Test
	public void FilterByTimeBadTest() throws Exception {
		sameScanWifi checkTime=new  sameScanWifi();
		checkTime.setTime("2010-12-01 23:00:59");
		Vector<sameScanWifi> test = new Vector<sameScanWifi>();
		test.add(checkTime);
		filter TimeFilterTest= new filterByTime("2015-12-01 23:00:59", "2020-12-01 23:00:59");
		assertEquals(0, TimeFilterTest.filt(test, null).size());
		test.add(checkTime);
		TimeFilterTest= new filterByTime("2010-12-01 23:01:00", "2010-12-01 23:01:00");
		assertEquals(0, TimeFilterTest.filt(test, null).size());
	} 
	
	
//	@Test
//	public void FilterByRXLAndRemoveTest() {
//		sameScanWifi checkRXL=new  sameScanWifi();
//		wifi test=new wifi();
//		test.setRSSI("-87");
//		test.setChannel("0");
//		test.setMAC("");
//		test.setSSID("");
//		checkRXL.insert(test);
//		test=new wifi();
//		test.setRSSI("-86");
//		test.setChannel("0");
//		test.setMAC("");
//		test.setSSID("");
//		checkRXL.insert(test);
//		filter RXLFilterTest= new filterByRXL();
//		filter.parm.setParm("-88");
//		assertTrue(RXLFilterTest.filters(checkRXL)&&checkRXL.size()==2);
//		filter.parm.setParm("-86");
//		assertTrue(RXLFilterTest.filters(checkRXL)&&checkRXL.size()==1);
//		filter.parm.setParm("-85");
//		assertFalse(RXLFilterTest.filters(checkRXL)||checkRXL.size()!=0);
//	} 
	
	/**
	 * check that the counting line function returns the right numbers
	 * @throws IOException
	 */
	@Test
	public void CountLineTest() throws IOException {
		//test when their is no enter in the end of the file
		assertEquals(1280,genericFunctions.countLines("test\\writeKmlTest\\good1.csv"));
		//test when their is enter in the end of the file
		assertEquals(1313,genericFunctions.countLines("test\\toCsvTest\\test2\\WigleWifi_20171107085918.csv"));
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
	 * test the filterTree toString
	 * @throws Exception 
	 */
	@Test
	public void filterToString() throws Exception{
		filterTree test = new filterTree((filter)null);
		assertEquals("null", test.toString());
		test.current = new filterByTime("2015-12-01 23:00:59", "2020-12-01 23:00:59");
		assertEquals("2015-12-01 23:00:59 < time < 2020-12-01 23:00:59", test.toString());
		test = new filterTree(new andGate(), test, test);
		assertEquals("(2015-12-01 23:00:59 < time < 2020-12-01 23:00:59)AND(2015-12-01 23:00:59 < time < 2020-12-01 23:00:59)", test.toString());
		
	}
	@Test
	public void filterFromStringToString() throws Exception{
		filterTree test = new filterTree(new andGate(),new filterTree(new notGate(), (filterTree)null, new filterTree(new filterByCoordinates("234.5", "3000", "20", "50.987"))),new filterTree(new orGate(),new filterTree(new filterByID("arye")),new filterTree( new filterByTime("2015-12-01 23:00:59", "2020-12-01 23:00:59"))));
		assertEquals("(NOT(234.5 < longitude < 3000 AND 20 < latitude < 50.987))AND((device ID = arye)OR(2015-12-01 23:00:59 < time < 2020-12-01 23:00:59))", test.toString());
		test = new filterTree(new andGate(), test, test);
	}
	
	/**
	 * test the filterTree fromString
	 * @throws Exception 
	 */
	@Test
	public void filterFromStringAndTime() throws Exception{
		filterTree test = new filterTree("(2015-12-01 23:00:59 < time < 2020-12-01 23:00:59)AND(2015-12-01 23:00:59 < time < 2020-12-01 23:00:59)");
		assertEquals("(2015-12-01 23:00:59 < time < 2020-12-01 23:00:59)AND(2015-12-01 23:00:59 < time < 2020-12-01 23:00:59)", test.toString());
	}
	@Test
	public void filterFromStringOrCoordinates() throws Exception{
		filterTree test = new filterTree("54.77 < longitude < 58.99 AND 43.66 < latitude < 556.9");
		assertEquals("54.77 < longitude < 58.99 AND 43.66 < latitude < 556.9", test.toString());
	}
	
	@Test
	public void filterFromStringNotID() throws Exception{
		filterTree test = new filterTree("NOT(device ID = erty)");
		assertEquals("NOT(device ID = erty)", test.toString());
	}
	
	@Test
	public void filterFromString() throws Exception{
		filterTree test = new filterTree("(NOT(234.5 < longitude < 3000 AND 20 < latitude < 50.987))AND((device ID = arye)OR(2015-12-01 23:00:59 < time < 2020-12-01 23:00:59))");
		assertEquals("(NOT(234.5 < longitude < 3000 AND 20 < latitude < 50.987))AND((device ID = arye)OR(2015-12-01 23:00:59 < time < 2020-12-01 23:00:59))",test.toString());
	}
}
