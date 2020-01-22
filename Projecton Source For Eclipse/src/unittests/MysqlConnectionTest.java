package unittests;

import server.MysqlConnection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import assets.SqlResult;
import assets.SqlAction;
import assets.SqlQueryType;

/* Testing the actual connection to the DataBase */

class MysqlConnectionTest {

	private MysqlConnection sqlConnection;
	private DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
	private Date date1;
	private Date date2;
	private Date date3;
	
	@BeforeEach
	void setUp() throws Exception {
		MysqlConnection.initSqlArray();
		sqlConnection = new MysqlConnection();
		date1 = new Date(df.parse("01-19-2020").getTime());
		date2 = new Date(df.parse("01-24-2020").getTime());
		date3 = new Date(df.parse("01-18-2020").getTime());
	}

	/* This test is select all change requests between 
	 * the first date and the second date, and get correct results
	 */
	@Test
	void testGetResult_SelectingChangeRequestsSuccess() {
		SqlResult expectedSqlResult;
		ArrayList<Object> expectedArray = new ArrayList<Object>();
		expectedArray.add(date3);
		expectedSqlResult = new SqlResult(expectedArray, SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN);

		
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(date1);
		varArray.add(date2);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN, varArray);
		SqlResult actualSqlResult = sqlConnection.getResult(sqlAction);
		assertEquals(expectedSqlResult.toString(), actualSqlResult.toString());
	}
	
	/* This test is select all change requests between 
	 * the first date and the second date, and get correct results
	 */
	@Test
	void testGetResult_SelectingEmptyResults() {
		SqlResult expectedSqlResult;
		ArrayList<Object> expectedArray = new ArrayList<Object>();
		expectedSqlResult = new SqlResult(expectedArray, SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN);

		
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(date2);
		varArray.add(date1);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_DATES_OF_ACTIVE_CHANGE_REQUESTS_BETWEEN, varArray);
		SqlResult actualSqlResult = sqlConnection.getResult(sqlAction);
		assertEquals(expectedSqlResult.toString(), actualSqlResult.toString());
	}

}
