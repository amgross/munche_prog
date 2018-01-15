import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import org.junit.Test;

import data.sameScanWifi;
import inputOutput.*;

public class inputOutputTest {
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
}
