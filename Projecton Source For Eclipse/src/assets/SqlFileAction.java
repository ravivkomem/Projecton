package assets;

import java.util.ArrayList;

import entities.MyFile;

/**
 * Extends the SqlAction class
 * Used for upload / download of files to/from server.
 *
 * @author Raviv Komem
 * @parm myFile - MyFile object
 * @parm upload - Boolean for wether it is upload or download action
 */
@SuppressWarnings("serial")
public class SqlFileAction extends SqlAction {

	/** The my file used to send or upload. */
	private MyFile myFile;
	
	/** A boolean used to represent if this action is sued to download a file or to upload a file. */
	private boolean isUpload;
	
	/**
	 * Instantiates a new sql file action.
	 *
	 * @param actionType the action type
	 * @param actionVars the action vars
	 * @param myFile the my file
	 */
	public SqlFileAction(SqlQueryType actionType, ArrayList<Object> actionVars, MyFile myFile) {
		super(actionType, actionVars);
		this.myFile = myFile;
		isUpload = true;
	}
	
	/**
	 * Instantiates a new sql file action.
	 *
	 * @param actionType the action type
	 * @param actionVars the action vars
	 */
	public SqlFileAction(SqlQueryType actionType, ArrayList<Object> actionVars) {
		super(actionType, actionVars);
		this.myFile = null;
		isUpload = false;
	}

	/**
	 * Gets the my file.
	 *
	 * @return the my file
	 */
	public MyFile getMyFile() {
		return myFile;
	}

	/**
	 * Sets the my file.
	 *
	 * @param myFile the new my file
	 */
	public void setMyFile(MyFile myFile) {
		this.myFile = myFile;
	}
	
	/**
	 * Gets isUpload boolean
	 *
	 * @return if the sql action is used for upload or download
	 */
	public boolean getUpload()
	{
		return this.isUpload;
	}

	
}
