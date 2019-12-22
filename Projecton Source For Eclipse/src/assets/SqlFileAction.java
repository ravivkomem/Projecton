package assets;

import java.util.ArrayList;

import entities.MyFile;

@SuppressWarnings("serial")
public class SqlFileAction extends SqlAction {

	private MyFile myFile;
	
	public SqlFileAction(SqlQueryType actionType, ArrayList<Object> actionVars, MyFile myFile) {
		super(actionType, actionVars);
		this.myFile = myFile;
	}

	public MyFile getMyFile() {
		return myFile;
	}

	public void setMyFile(MyFile myFile) {
		this.myFile = myFile;
	}

	
}
