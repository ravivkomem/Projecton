package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import assets.SqlAction;
import assets.SqlFileAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.UploadChangeRequestBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.MyFile;
import javafx.application.Platform;
import java.math.BigInteger; 


/**
 * Upload Change Request Page (Controller).
 *
 * @author Ido Kadosh
 */
@SuppressWarnings("serial")
public class UploadChangeRequestController extends BasicController {

	/** The my boundary. */
	private UploadChangeRequestBoundary myBoundary;
	
	/** The current change request. */
	private ChangeRequest currentChangeRequest;
	
	/**
	 * Instantiates a new upload change request controller.
	 *
	 * @param myBoundary the my boundary
	 */
	public UploadChangeRequestController(UploadChangeRequestBoundary myBoundary){
		this.myBoundary=myBoundary;//connection to my boundary  
	}
	
	/**
	 * Building the change request with the inserted in the boundary from the user.
	 *
	 * @param newchangerequest the newchangerequest
	 */
	public void buildChangeRequestBeforeSendToDataBase(ChangeRequest newchangerequest)
	{
		currentChangeRequest = newchangerequest;//get the information about the change request from boundary 
		this.appointHandlerBySystemRequired();//pick the analyzer randomly 
		
	}
	
	/**
	 * After all tests passed in the boundary and built the change request update the data base.
	 */
	private void uploadTheInsertedNewChangeRequestToDataBase()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		/*add current step field*/
		varArray.add(currentChangeRequest.getInitiatorUserName());
		varArray.add(currentChangeRequest.getStartDate());
		varArray.add(currentChangeRequest.getSelectedSubsystem());
		varArray.add(currentChangeRequest.getCurrentStateDescription());
		varArray.add(currentChangeRequest.getDesiredChangeDescription());
		varArray.add(currentChangeRequest.getDesiredChangeExplanation());
		varArray.add(currentChangeRequest.getDesiredChangeComments());
		varArray.add(currentChangeRequest.getStatus());
		varArray.add(currentChangeRequest.getCurrentStep());
		varArray.add(currentChangeRequest.getHandlerUserName());
		varArray.add(currentChangeRequest.getEmail());
		varArray.add(currentChangeRequest.getJobDescription());
		varArray.add(currentChangeRequest.getFullName());
		/*execute the insert new change request query */
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_CHANGE_REQUEST,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	/**
	 * In case the user chose to attach file with the change request saves the file on the server for reuse .
	 *
	 * @param filesToUploadList the files to upload list
	 * @param chnageRequestId the chnage request id
	 */
	public void sendFilesToServer(List<MyFile> filesToUploadList, Integer chnageRequestId)
	{
		if (filesToUploadList.isEmpty())
		{
			/*DO NOTHING */
		}
		else
		{
			for (MyFile file : filesToUploadList)
			{
				String path = file.getFileName();
				
				String extension = "";

				int i = path.lastIndexOf('.');
				if (i > 0) {
				    extension = path.substring(i+1);
				}
				
				ArrayList<Object> varArray = new ArrayList<>();
				varArray.add(chnageRequestId);
				varArray.add(extension);
				SqlFileAction sqlFileAction = new SqlFileAction(SqlQueryType.INSERT_NEW_FILE, varArray, file);
				this.sendSqlActionToClient(sqlFileAction);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see controllers.BasicController#getResultFromClient(assets.SqlResult)
	 */
	@Override
	public void getResultFromClient(SqlResult result) {
		
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case INSERT_NEW_CHANGE_REQUEST:
					int changeRequestId = -1;
					this.unsubscribeFromClientDeliveries();
					changeRequestId = ((BigInteger) (result.getResultData().get(0).get(0))).intValue();
					myBoundary.displayChangeRequestId(changeRequestId);
					break;
				case SELECT_ALL_INFROMATION_ENGINEERS:
					this.unsubscribeFromClientDeliveries();
					ArrayList<String> informationEngineers = new ArrayList<String>();
					for (ArrayList<Object> informationEngineerRow : result.getResultData())
					{
						String currEngineer = (String) informationEngineerRow.get(0);
						informationEngineers.add(currEngineer);
					}
					Random rand = new Random();
					int randEngineerIndex = rand.nextInt(informationEngineers.size());
					currentChangeRequest.setHandlerUserName(informationEngineers.get(randEngineerIndex));
					uploadTheInsertedNewChangeRequestToDataBase();
					break;
				case INSERT_NEW_FILE:
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_HANDLER_USER_NAME_BY_SYSTEM:
					this.unsubscribeFromClientDeliveries();
					currentChangeRequest.setHandlerUserName(result.getResultData().get(0).get(0).toString());
					uploadTheInsertedNewChangeRequestToDataBase();
					break;
				default:
					break;
			}
		});
		return;	
	}
	/**
	 * This method calls a query to update change request with the necessary handler.
	 */
	public void appointHandlerBySystemRequired()
	{
		ArrayList<Object> data =new ArrayList<>();
		data.add(currentChangeRequest.getSelectedSubsystem());
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_HANDLER_USER_NAME_BY_SYSTEM,data);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

}
