package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import server.EchoServer;

class EchoServerTest {

	private StubDatabase stubDataBase;
	private DateFormat df;
	private Date date1;
	private Date date2;
	private Date date3;
	private Date date4;
	private ArrayList<Object> dateList;
	private EchoServer server;
	
	
	@BeforeEach
	void setUp() throws Exception {
		server = new EchoServer(0);
		stubDataBase = new StubDatabase();
		
		df = new SimpleDateFormat("MM-dd-yyyy");
		date1 = new Date(df.parse("01-19-2020").getTime());
		date2 = new Date(df.parse("01-24-2020").getTime());
		date3 = new Date(df.parse("01-18-2020").getTime());
		date4 = new Date(df.parse("01-22-2020").getTime());
		dateList = new ArrayList<Object>();
		dateList.add(date1);
		dateList.add(date2);
		dateList.add(date3);
		dateList.add(date4);
		
		stubDataBase.setDateList(dateList);
		server.setSqlConnection(stubDataBase);
	}

	@Test
	void testGetResultFromDB_GettingChangeRequestsBetweenDates() {
		SqlResult expectedResult = new SqlResult(dateList, SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN);
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(date3);
		varArray.add(date2);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN, varArray);
		assertEquals(expectedResult, server.getResultFromDB(sqlAction));
	}

	@Test
	void testGetResultFromDB_NullActionVar() {
		SqlResult expectedResult = null;
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN, null);
		assertEquals(expectedResult, server.getResultFromDB(sqlAction));
	}
	
	@Test
	void testGetResultFromDB_EmptyActionVar() {
		SqlResult expectedResult = null;
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN, new ArrayList<Object>());
		assertEquals(expectedResult, server.getResultFromDB(sqlAction));
	}
	
	@Test
	void testGetResultFromDB_VarArraySizeNotTwo() {
		SqlResult expectedResult = null;
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(date3);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN, varArray);
		assertEquals(expectedResult, server.getResultFromDB(sqlAction));
	}
	
	@Test
	void testGetResultFromDB_VarArraySizeNotTwoPart2() {
		SqlResult expectedResult = null;
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(date3);
		varArray.add(date2);
		varArray.add(date1);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN, varArray);
		assertEquals(expectedResult, server.getResultFromDB(sqlAction));
	}
	
	@Test
	void testGetResultFromDB_DateNotExistInVarArray() {
		SqlResult expectedResult = null;
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(date3);
		varArray.add("string");
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN, varArray);
		assertEquals(expectedResult, server.getResultFromDB(sqlAction));
	}
	
	@Test
	void testGetResultFromDB_InsertActivityReportSuccess() {
		SqlResult expectedResult = new SqlResult(1, SqlQueryType.INSERT_NEW_ACTIVITY_REPORT);
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(date3);
		varArray.add(date2);
		varArray.add(1);
		varArray.add(2);
		varArray.add(3);
		varArray.add(4);
		varArray.add(1);
		varArray.add(2);
		varArray.add(3);
		varArray.add(4);
		varArray.add(3);
		varArray.add(4);
		varArray.add(3);
		varArray.add(4);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ACTIVITY_REPORT, varArray);
		assertEquals(expectedResult, server.getResultFromDB(sqlAction));
	}
	
	@Test
	void testGetResultFromDB_InsertNewActivityReportNot14() {
		SqlResult expectedResult = null;
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(date3);
		varArray.add(date2);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ACTIVITY_REPORT, varArray);
		assertEquals(expectedResult, server.getResultFromDB(sqlAction));
	}
	
}
