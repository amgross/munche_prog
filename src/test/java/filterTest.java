import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import data.sameScanWifi;
import filters.*;


public class filterTest {
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
