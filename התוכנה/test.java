
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.Test;

public class test {
	/**
	 * checks the toCsv class
	 * @throws IOException
	 */
	@Test
	public void test1ToCsv() throws IOException {
		toCsv.writeCsv("test\\toCsvTest\\test1");
		FileReader fr = new FileReader("test\\toCsvTest\\test1\\wifi.csv");
		BufferedReader br = new BufferedReader(fr);
		Scanner scanner = new Scanner(br.readLine());
		assertEquals("2017-10-27 16:13:51,ONEPLUS A3003_28_171012,32.16876665,34.81320794,37,9,osnatg370,c0:ac:54:f5:7b:a7,11,-86,NGOGA,30:b5:c2:fe:aa:56,6,-87,DIRECT-35-HP DeskJet 3830 series,98:e7:f4:c6:4b:37,6,-94,BezeqFree,6a:12:f5:f9:5e:71,8,-85,Howmanyistwo,74:da:38:50:77:f2,2,-81,Robert1,3c:1e:04:03:7f:17,1,-82,tslila,c4:3d:c7:5a:79:1c,11,-89,OP2,a0:63:91:69:f6:af,1,-80,Lubelsky,c0:4a:00:ba:e8:f6,13,-88",br.readLine());
		scanner.close();
		br.close();
	}

	@Test
	public void test2ToCsv() throws IOException {
		toCsv.writeCsv("test\\toCsvTest\\test2");
		FileReader fr = new FileReader("test\\toCsvTest\\test2\\wifi.csv");
		BufferedReader br = new BufferedReader(fr);
		Scanner scanner = new Scanner(br.readLine());
		assertEquals("2017-10-30 15:31:24,V510_IS_CY_MT6735M_L1_V1.4,32.103325,35.2089866666667,690.3,10,Ariel_University,1c:b9:c4:16:28:e8,1,-58,Ariel_University,34:8f:27:20:8c:18,1,-70,Ariel_University,8c:0c:90:2d:75:e8,1,-86,Ariel_University,24:79:2a:2c:3d:68,11,-78,Ariel_University,1c:b9:c4:12:90:b8,11,-88,Ariel_University,1c:b9:c4:12:af:78,6,-89,Ariel_University,1c:b9:c4:16:f4:28,11,-88,Ariel_University,ec:8c:a2:08:94:18,1,-92,,00:25:86:cb:fd:f8,9,-72,Ariel_University,8c:0c:90:2f:ca:18,6,-88",br.readLine());
		scanner.close();
		br.close();
	}
	@Test
	public void test3ToCsv() throws IOException {
		toCsv.writeCsv("test\\toCsvTest\\test3");
		FileReader fr = new FileReader("test\\toCsvTest\\test3\\wifi.csv");
		BufferedReader br = new BufferedReader(fr);
		Scanner scanner = new Scanner(br.readLine());
		assertEquals(null,br.readLine());
		scanner.close();
		br.close();
	}
	@Test
	public void test4ToCsv() throws IOException {
		toCsv.writeCsv("test\\toCsvTest\\test4");
		FileReader fr = new FileReader("test\\toCsvTest\\test4\\wifi.csv");
		BufferedReader br = new BufferedReader(fr);
		Scanner scanner = new Scanner(br.readLine());
		assertEquals("2017-10-27 16:34:47,MMB29K.A520FXXU1AQF3,32.1675586234778,34.8099447973073,61.7000007629395,4,Mouly,7c:b7:33:2e:76:73,11,-78,888Corp,0a:8d:db:6e:71:6d,1,-84,888Corp,0a:8d:db:6e:71:bf,1,-84,smartsell,78:e8:b6:69:2a:e9,11,-87",br.readLine());
		assertEquals(null,br.readLine());
		scanner.close();
		br.close();
	}
	
	
	/**
	 * check the toKml class
	 */
	@Test
	public void test1WriteKml() {
		System.out.println("press 'n' three times with enter");
		assertEquals(0, toKml.writeKml("test\\writeKmlTest\\bad1.csv","test\\writeKmlTest"));
	}
	@Test
	public void test2WriteKml() {
		System.out.println("press 'n' three times with enter");
		assertEquals(0, toKml.writeKml("test\\writeKmlTest\\bad2.csv","test\\writeKmlTest"));
	}
	@Test
	public void test3WriteKml() {
		System.out.println("press 'n' three times with enter");
		assertEquals(1,toKml.writeKml("test\\writeKmlTest\\good1.csv","test\\writeKmlTest"));
	}
	@Test
	public void test4WriteKml() {
		System.out.println("press 'n' three times with enter");
		assertEquals(1,toKml.writeKml("test\\writeKmlTest\\good2.csv","test\\writeKmlTest"));
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
	 */
	@Test
	public void FilterByTimeGoodTest() {
		String[] time={"2007-12-01 23:00:59"};
		filter TimeFilterTest= new filterByTime();
		assertTrue(TimeFilterTest.filters(time,"95-04-26 15:25:56"));
		assertTrue(TimeFilterTest.filters(time,"2007-12-01 22:15:59"));
		assertTrue(TimeFilterTest.filters(time,time[0]));
	} 
	@Test
	public void FilterByTimeBadTest() {
		String[] time={"2007-12-01 23:00:59"};
		filter TimeFilterTest= new filterByTime();
		assertFalse(TimeFilterTest.filters(time,"2008-04-26 15:25:56"));
		assertFalse(TimeFilterTest.filters(time,"2007-12-01 23:01:00"));
	} 
	
	
	@Test
	public void FilterByRXLTest() {
		String[] line="2017-10-27 16:13:51,ONEPLUS A3003_28_171012,32.16876665,34.81320794,37,2,osnatg370,c0:ac:54:f5:7b:a7,11,-86,NGOGA,30:b5:c2:fe:aa:56,6,-87".split(",");
		String[] check="2017-10-27 16:13:51,ONEPLUS A3003_28_171012,32.16876665,34.81320794,37,2,osnatg370,c0:ac:54:f5:7b:a7,11,-86,NGOGA,30:b5:c2:fe:aa:56,6,-87".split(",");
		filter RXLFilterTest= new filterByRXL();
		assertTrue(RXLFilterTest.filters(line,"-88")&&Arrays.equals(check, line));
		assertTrue(RXLFilterTest.filters(line,"-86")&&line[13].equals("0"));
		assertFalse(RXLFilterTest.filters(check,"-85"));
	} 
	
	@Test
	public void CountLineTest() throws IOException {
		//test when their is no enter in the end of the file
		assertEquals(1281,genericFunctions.countLines("test\\writeKmlTest\\good1.csv"));
		//test when their is enter in the end of the file
		assertEquals(1313,genericFunctions.countLines("test\\toCsvTest\\test2\\WigleWifi_20171107085918.csv"));
	} 
}
