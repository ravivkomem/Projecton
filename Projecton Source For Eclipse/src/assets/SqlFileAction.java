package assets;

import java.util.ArrayList;

import entities.MyFile;

@SuppressWarnings("serial")
public class SqlFileAction extends SqlAction {

	private MyFile myFile;
	private boolean upload;
	
	public SqlFileAction(SqlQueryType actionType, ArrayList<Object> actionVars, MyFile myFile) {
		super(actionType, actionVars);
		this.myFile = myFile;
		upload = true;
	}
	
	public SqlFileAction(SqlQueryType actionType, ArrayList<Object> actionVars) {
		super(actionType, actionVars);
		this.myFile = null;
		upload = false;
	}

	public MyFile getMyFile() {
		return myFile;
	}

	public void setMyFile(MyFile myFile) {
		this.myFile = myFile;
	}
	
	public boolean getUpload()
	{
		return this.upload;
	}

	
}
