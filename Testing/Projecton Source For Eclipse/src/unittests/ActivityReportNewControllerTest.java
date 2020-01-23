package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assets.SqlQueryType;
import assets.SqlResult;
import controllers.ActivityReportNewController;
import entities.NewActivityReport;

class ActivityReportNewControllerTest {

	private ActivityReportNewController controller;
	private SqlResult sqlResult1;
	private SqlResult sqlResult2;
	private SqlResult sqlResult3;
	private ArrayList<Date> dateList1;
	private ArrayList<Date> dateList2;
	private ArrayList<Date> dateList3;
	private ArrayList<Object> objectList1;
	private ArrayList<Object> objectList2;
	private ArrayList<Object> objectList3;
	private DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
	private Date date1;
	private Date date2;
	private Date date3;
	private Date date4;
	
	@BeforeEach
	void setUp() throws Exception {
		controller = new ActivityReportNewController(null);
		date1 = new Date(df.parse("01-19-2020").getTime());
		date2 = new Date(df.parse("01-24-2020").getTime());
		date3 = new Date(df.parse("01-18-2020").getTime());
		date4 = new Date(df.parse("12-01-2019").getTime());
		
		dateList1 = new ArrayList<Date>();
		dateList1.add(date1);
		dateList1.add(date2);
		dateList1.add(date3);
		
		objectList1 = new ArrayList<Object>();
		objectList1.addAll(dateList1);
		
		dateList2 = new ArrayList<Date>();
		objectList2 = new ArrayList<Object>();
		
		dateList3 = new ArrayList<Date>();
		dateList3.add(date4);
		
		objectList3 = new ArrayList<Object>();
		objectList3.add(7);
		objectList3.add(date1);
		
		sqlResult1 = new SqlResult(objectList1, SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN);
		sqlResult2 = new SqlResult(objectList2, SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN);
		sqlResult3 = new SqlResult(objectList3, SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN);
	
	}

	@Test
	void testCreateDateList_SuccessfullCreation() {
		ArrayList<Date> expectedResult = dateList1;
		ArrayList<Date> actualResult = controller.createDateList(sqlResult1);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testCreateDateList_NullSqlResult() {
		ArrayList<Date> expectedResult = null;
		ArrayList<Date> actualResult = controller.createDateList(null);
		assertTrue(expectedResult == actualResult);
	}
	
	@Test
	void testCreateDateList_EmptySqlResult() {
		ArrayList<Date> expectedResult = dateList2;
		ArrayList<Date> actualResult = controller.createDateList(sqlResult2);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testCreateDateList_NonDateObjectList() {
		ArrayList<Date> expectedResult = null;
		ArrayList<Date> actualResult = controller.createDateList(sqlResult3);
		assertTrue(expectedResult == actualResult);
	}
	

	@Test()
	void testCreateNewActivityReport_Successfully() {
		controller.setDates(date4, date2);
		long[] array = {0, 0, 0, 0, 0, 0, 0, 0, 1, 2};
		NewActivityReport expectedResult = new NewActivityReport(array, date4, date2);
		NewActivityReport actualResult = controller.createNewActivityReport(dateList1);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test()
	void testCreateNewActivityReport_EmptyDateList() {
		controller.setDates(date4, date2);
		long[] array = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		NewActivityReport expectedResult = new NewActivityReport(array, date4, date2);
		NewActivityReport actualResult = controller.createNewActivityReport(dateList2);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test()
	void testCreateNewActivityReport_Successfully2() {
		controller.setDates(date4, date2);
		long[] array = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		NewActivityReport expectedResult = new NewActivityReport(array, date4, date2);
		NewActivityReport actualResult = controller.createNewActivityReport(dateList3);
		assertEquals(expectedResult, actualResult);
	}

	@Test()
	void testCreateNewActivityReport_NullDateList() {
		controller.setDates(date4, date2);
		NewActivityReport expectedResult = null;
		NewActivityReport actualResult = controller.createNewActivityReport(null);
		assertTrue(expectedResult == actualResult);
	}
}
