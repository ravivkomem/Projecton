package unittests;

import assets.SqlAction;
import assets.SqlResult;

public interface ISqlConnection {
	public SqlResult getResult(SqlAction sqlAction);
}
