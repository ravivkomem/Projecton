package controllers;

import java.sql.Date;
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
 * Extra details page for a specific change request (Controller).
 *
 * @author Ido Kadosh
 */
@SuppressWarnings("serial")
public class ExtraDetailsChangeRequestController extends BasicController {

	/** The my boundary. */
	private ExtraDetailsChangeRequestBoundary myBoundary;
	
	/**
	 * Instantiates a new extra details change request controller.
	 *
	 * @param extraDetailsChangeRequestBoundary the extra details change request boundary
	 */
	public ExtraDetailsChangeRequestController(ExtraDetailsChangeRequestBoundary extraDetailsChangeRequestBoundary) {
		this.myBoundary = extraDetailsChangeRequestBoundary;
	}

	/**
	 * Execute query in case the user attached a file to specific change request .
	 *
	 * @param changeRequestID the change request ID
	 * send SQL action to the server to get all the change request files
	 */
	public void getChangeRequestFiles(Integer changeRequestID) {

		/* Create sql action */
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(changeRequestID);
		//SqlAction sqlFileAction = new SqlAction(SqlQueryType.DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID, varArray);
		SqlFileAction sqlFileAction = new SqlFileAction(SqlQueryType.DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID, varArray);
		this.sendSqlActionToClient(sqlFileAction);
	}
	
	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
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
					break;
				case UPDATE_STATUS_BY_SUPERVISOR:
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_CHANGE_REQUEST_STEP_ESTIMATED_END_DATE:
					this.unsubscribeFromClientDeliveries();
					if (result.getResultData().isEmpty())
					{
						myBoundary.fillEstimatedEndDateField(null);
					}
					else
					{
						myBoundary.fillEstimatedEndDateField((Date) result.getResultData().get(0).get(0));
					}
					break;
				case INSERT_NEW_SUPERVISOR_UPDATE:
					this.unsubscribeFromClientDeliveries();
					break;
				default:
					break;
			}
		});
		return;
		
	}
	
	/**
	 * This method belongs only to user with a supervisor permission and allows this user to 
	 * suspend or un-suspend each specific request  .
	 *
	 * @param changeRequestId the change request id
	 * @param updatedStatus the updated status
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

	public void getStepEstimatedEndDate(Integer changeRequestID) {
		
		ArrayList<Object> data =new ArrayList<>();
		data.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_CHANGE_REQUEST_STEP_ESTIMATED_END_DATE, data);
		this.sendSqlActionToClient(sqlAction);
	}

	public void inserntNewSupervisorUpdate(Integer id, String userName, String essence, Date date, String fullName) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(id);
		varArray.add(userName);
		varArray.add(essence);
		varArray.add(date);
		varArray.add(fullName);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_SUPERVISOR_UPDATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
}
