package controllers;

import java.util.ArrayList;
import java.util.Random;

import assets.SqlAction;
import assets.SqlFileAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.UploadChangeRequestBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.ChangeRequest;
import entities.CommitteeComment;
import entities.MyFile;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import java.math.BigInteger; 

public class UploadChangeRequestController extends BasicController {

	private UploadChangeRequestBoundary myBoundary;
	private ChangeRequest currentChangeRequest;
	
	public UploadChangeRequestController(UploadChangeRequestBoundary myBoundary){
		this.myBoundary=myBoundary;//connection to my boundary  
	}
	/*building the querey and update the database */
	public void buildChangeRequestBeforeSendToDataBase(ChangeRequest newchangerequest)
	{
		currentChangeRequest = newchangerequest;//get the information about the change request from boundary 
		this.chooseAutomaticallyAnalyzer();//pick the analyzer randomly 
		
	}
	
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
		/*execute the insert new change request query */
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_CHANGE_REQUEST,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void sendFilesToServer(String path, Integer chnageRequestId)
	{
		if (path.equals(""))
		{
			/*DO NOTHING */
		}
		else
		{
			MyFile newFile = MyFile.parseToMyFile(path);
			
			String extension = "";

			int i = path.lastIndexOf('.');
			if (i > 0) {
			    extension = path.substring(i+1);
			}
			
			ArrayList<Object> varArray = new ArrayList<>();
			varArray.add(chnageRequestId);
			varArray.add(extension);
			SqlFileAction sqlFileAction = new SqlFileAction(SqlQueryType.INSERT_NEW_FILE, varArray, newFile);
			this.sendSqlActionToClient(sqlFileAction);
		}
	}
	
	@Override
	public void getResultFromClient(SqlResult result) {
		
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case INSERT_NEW_CHANGE_REQUEST:
					int changeRequestId = -1;
					this.unsubscribeFromClientDeliveries();
					/*TODO: Check if result is empty ---> something wrong happend */
					if (true)
					{
						changeRequestId = ((BigInteger) (result.getResultData().get(0).get(0))).intValue();
					}
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
					/*TODO: Do something */
					int fileID = ((BigInteger) (result.getResultData().get(0).get(0))).intValue();
					
					break;
				default:
					break;
			}
		});
		return;	
	}
	/*execute the select all information engineers query  */
	public void chooseAutomaticallyAnalyzer()
	{
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_INFROMATION_ENGINEERS,new ArrayList<Object>());
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}

}
