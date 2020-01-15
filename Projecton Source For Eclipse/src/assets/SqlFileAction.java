package assets;

import java.util.ArrayList;

import entities.MyFile;

/**
 * Extends the SqlAction class
 * Used for upload / download of files to/from server
 * @author Raviv Komem
 *
 * @parm myFile - MyFile object
 * @parm upload - Boolean for wether it is upload or download action
 */
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
