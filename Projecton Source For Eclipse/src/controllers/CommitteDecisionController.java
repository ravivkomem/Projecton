package controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.StepType;
import assets.Toast;
import boundries.CommitteeDecisionBoundary;
import boundries.ProjectFX;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.CommitteeComment;
import entities.Step;
import javafx.application.Platform;

/**
 * 
 * @author Lee Hugi
 *This controller handle with the committee decision page
 */

public class CommitteDecisionController extends BasicController{
	
	private CommitteeDecisionBoundary myBoundary;
	
	public CommitteDecisionController(CommitteeDecisionBoundary myBoundary){
		this.myBoundary=myBoundary;
	}
	
	public void getCommentsByChangeRequestId(int id) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(id);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_COMMENTS_BY_REQUEST_ID, varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void insertNewCommentToDB(CommitteeComment newComment) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newComment.getRequestId());
		varArray.add(newComment.getEmployeeUserName());
		varArray.add(newComment.getComment());
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_COMMITTEE_COMMENT,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void updateCommitteeStepDB(String status,Date date,Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(status);
		varArray.add(date);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_COMMITTEE_STEP,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void updateChangeRequestCurrentStep(String currentStep, String HamdlerUserName, Integer changeRequestID) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(currentStep);
		varArray.add(HamdlerUserName);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_CURRENT_STEP,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void insertToClosingStepDbTable(Integer changeRequestID, Date StartStepDate, String Status) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(StartStepDate);
		varArray.add(Status);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_CLOSING_STEP,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void getStartTimeFromCommitteeStep(Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_COMMITTEE_STEP_START_DATE,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
	public void getCommitteeStepDetails(Integer changeRequestId) {
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestId);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_COMMITTEE_STEP_DETAILS,varArray);
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case SELECT_COMMENTS_BY_REQUEST_ID:
					ArrayList<CommitteeComment> resultList=new ArrayList<>();
					resultList.addAll(this.changeResultToCommitteeComment(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleCommitteeCommentResultForTable(resultList);
					break;
				case INSERT_NEW_COMMITTEE_COMMENT:
					/* insert query return only one int value */
					int affectedRows;
					affectedRows = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					myBoundary.committeeCommentInsertToDBSuccessfully(affectedRows);
					break;
				case UPDATE_COMMITTEE_STEP:
					/*TODO check if affected rows == 1*/
//					int affectedRows2;
//					affectedRows2 = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_CURRENT_STEP:
					/*TODO check if affected rows == 1*/
//					int affectedRows3;
//					affectedRows3 = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					break;
				case INSERT_NEW_CLOSING_STEP:
					/*TODO check if affected rows == 1*/
//					int affectedRows4;
//					affectedRows4 = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
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
					String handlerUserName=informationEngineers.get(randEngineerIndex);
					myBoundary.createObjectForUpdateChangeRequestDetails(handlerUserName);
					break;
				case SELECT_COMMITTEE_STEP_START_DATE:
					Date estimatedEndDate = (Date) (result.getResultData().get(0).get(0));
					myBoundary.displayTimeRemaining(estimatedEndDate);
					break;
				case SELECT_COMMITTEE_STEP_DETAILS:
					Step newStep =  new Step(StepType.COMMITTEE, (Integer)result.getResultData().get(0).get(0),
							(Integer)result.getResultData().get(0).get(1),(String)result.getResultData().get(0).get(2),
							(Date)result.getResultData().get(0).get(3),(String)result.getResultData().get(0).get(6),
							(Date)result.getResultData().get(0).get(4),(Date)result.getResultData().get(0).get(5));
					myBoundary.createCommitteStepDetails(newStep);
					break;
				default:
					break;
			}
		});
		return;
		
	}
	
	private ArrayList<CommitteeComment> changeResultToCommitteeComment(SqlResult result){
		ArrayList<CommitteeComment> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData()) {
			CommitteeComment comment=new CommitteeComment((int)a.get(0), (int)a.get(1),(String)a.get(2),
					(String)a.get(3));
			resultList.add(comment);
		}
		return resultList;
	}

	/*execute the select all information engineers query */
	public void chooseAutomaticallyAnalyzer(){
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_INFROMATION_ENGINEERS,new ArrayList<Object>());
		this.subscribeToClientDeliveries();		//subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}
	
}
