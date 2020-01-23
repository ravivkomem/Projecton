package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.Utilizer;

class UtilizerTest {
	private ArrayList<Long> list1;
	private ArrayList<Long> list2;
	private ArrayList<Long> list3;
	private ArrayList<Long> list4;
	
	@BeforeEach
	void setUp() throws Exception {
		list1 = new ArrayList<Long>();
		list1.add((long)2);
		list1.add((long)3);
		list1.add((long)4);
		list1.add((long)8);
		list1.add((long)2);
		
		list2 = new ArrayList<Long>();
		list2.add((long)2);
		list2.add((long)1);
		list2.add((long)3);
		list2.add((long)5);
		list2.add((long)14);
		list2.add((long)2);
		
		list3 = new ArrayList<Long>();
		
		list4 = new ArrayList<Long>();
		list4.add((long)-2);
		list4.add((long)-1);
		list4.add((long)-3);
	}

	@Test
	void testCalcMedian_oddNumberOfElements() {
		double expectedResult = 3.0;
		double actualResult = Utilizer.calcMedian(list1);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testCalcMedian_evenNumberOfElements() {
		double expectedResult = 2.5;
		double actualResult = Utilizer.calcMedian(list2);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testCalcMedian_emptyList() {
		double expectedResult = 0.0;
		double actualResult = Utilizer.calcMedian(list3);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testCalcMedian_negativeNumbers() {
		double expectedResult = -2.0;
		double actualResult = Utilizer.calcMedian(list4);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void testCalcStd_positiveNumber() {
		double expectedResult = 2.227105745132009;
		double actualResult = Utilizer.calcStd(list1);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testCalcStd_negativeNumber() {
		double expectedResult = 0.816496580927726;
		double actualResult = Utilizer.calcStd(list4);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testCalcStd_empty() {
		double expectedResult = 0.0;
		double actualResult = Utilizer.calcStd(list3);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void testCalcAvg_empty() {
		double expectedResult = 0.0;
		double actualResult = Utilizer.calcAvg(list3);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testCalcAvg_positive() {
		double expectedResult = 4.5;
		double actualResult = Utilizer.calcAvg(list2);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testCalcAvg_negative() {
		double expectedResult = -2.0;
		double actualResult = Utilizer.calcAvg(list4);
		assertEquals(expectedResult, actualResult);
	}
}
