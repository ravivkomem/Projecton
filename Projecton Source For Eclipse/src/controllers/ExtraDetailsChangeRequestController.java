package controllers;

import java.util.ArrayList;
import java.util.List;

import entities.MyFile;
import assets.SqlResult;
import assets.SqlAction;
import assets.SqlFileAction;
import assets.SqlQueryType;
import boundries.ExtraDetailsChangeRequestBoundary;
import client.ClientConsole;
import javafx.application.Platform;
/**
 * extra details page for a specific change request (Controller)
 * @author Ido Kadosh
 *
 */
@SuppressWarnings("serial")
public class ExtraDetailsChangeRequestController extends BasicController {

	private ExtraDetailsChangeRequestBoundary myBoundary;
	
	public ExtraDetailsChangeRequestController(ExtraDetailsChangeRequestBoundary extraDetailsChangeRequestBoundary) {
		this.myBoundary = extraDetailsChangeRequestBoundary;
	}

	/**
	 * execute querey in case the user attached a file to specific change request 
	 * @param changeRequestID
	 */
	public void getChangeRequestFiles(Integer changeRequestID) {

		/* Create sql action */
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(changeRequestID);
		//SqlAction sqlFileAction = new SqlAction(SqlQueryType.DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID, varArray);
		SqlFileAction sqlFileAction = new SqlFileAction(SqlQueryType.DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID, varArray);
		this.sendSqlActionToClient(sqlFileAction);
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID:
					this.unsubscribeFromClientDeliveries();
					if (result.getResultData().get(0).isEmpty())
					{
						myBoundary.recieveFileList(null);
					}
					else
					{
						List<MyFile> myFileList = new ArrayList<MyFile>();
						ArrayList<Object> resultRow = result.getResultData().get(0);
						for (Object obj : resultRow)
						{
							myFileList.add((MyFile)obj);
						}
						myBoundary.recieveFileList(myFileList);
					}
				case UPDATE_STATUS_BY_SUPERVISOR:
					this.unsubscribeFromClientDeliveries();
					break;
				default:
					break;
			}
		});
		return;
		
	}
	/**
	 * this method belongs only to user with a supervisor permission and allows this user to 
	 * suspend or unsuspend each specific request  
	 * @param changeRequestId
	 * @param updatedStatus
	 */
	public void updateStatusBySupervisor(int changeRequestId,String updatedStatus)
	{
		ArrayList<Object> data =new ArrayList<>();
		data.add(updatedStatus);
		data.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_STATUS_BY_SUPERVISOR,data);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	

}
