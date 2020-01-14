package controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import assets.StepType;
import boundries.SupervisorBoundary;
import client.ClientConsole;
import entities.ChangeRequest;
import entities.CommitteeComment;
import entities.Step;
import javafx.application.Platform;

public class SupervisorController extends BasicController
{
	
	
	

	private SupervisorBoundary myBoundary;
	
	
	
	public SupervisorController(SupervisorBoundary myBoundary)
	{
		this.myBoundary = myBoundary;
	}
	
	
	
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				
				case SELECT_ALL_CHANGE_REQUEST:
					ArrayList<ChangeRequest> resultList=new ArrayList<>();
					resultList.addAll(this.changeResultToChangerequest(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleChangerequestResultForTable(resultList);
					break;
				case SELECT_ALL_CHANGE_REQUEST_FOR_APPOINTMENTS:
					ArrayList<ChangeRequest> resultList2=new ArrayList<>();
					resultList2.addAll(this.changeResultToChangerequest(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleChangerequestResultForTable(resultList2);
					break;
				case SELECT_ALL_CHANGE_REQUEST_FOR_APPROVALS:
					ArrayList<ChangeRequest> resultList3=new ArrayList<>();
					resultList3.addAll(this.changeResultToChangerequest(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleChangerequestResultForTable(resultList3);
					break;
				case SELECT_ALL_CHANGE_REQUEST_FOR_CLOSE:
					ArrayList<ChangeRequest> resultList4=new ArrayList<>();
					resultList4.addAll(this.changeResultToChangerequest(result));
					this.unsubscribeFromClientDeliveries();
					myBoundary.handleChangerequestResultForTable(resultList4);
					break;
				case UPDATE_CURRENT_STEP_TO_ANALYZER_SUPERVISOR_APPOINT:
					int affectedRows;
					affectedRows = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					myBoundary.ShowAnalyzerSupervisorAppointToast(affectedRows);
					break;	
				case UPDATE_ANALYZER_BY_SUPERVISOR:
					int affectedRows2;
					affectedRows2 = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					myBoundary.ShowSuccessAnalyzerAppoint(affectedRows2);
					break;
				case INSERT_NEW_ANALYSIS_STEP:
					int affectedRows3;
					affectedRows3 = (Integer) (result.getResultData().get(0).get(0));
					this.unsubscribeFromClientDeliveries();
					//myBoundary.ShowSuccessAnalyzerAppoint(affectedRows2);
					break;
				case UPDATE_STEP_TO_ANALYSIS_SET_TIME:
					this.unsubscribeFromClientDeliveries();
					myBoundary.ShowSuccessAproveAppoint();
					break;
				case UPDATE_NEW_EXECUTION_LEADER:	
					this.unsubscribeFromClientDeliveries();
					myBoundary.ShowAppointExecutionLeaderSuccess();
					break;
				case INSERT_NEW_EXECUTION_STEP:
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_ANALYSIS_SET_TIME:
					myBoundary.showDenyAnalysisTime();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_ANALYSIS_SET_TIME:	
					myBoundary.showApproveAnalysisTime();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_EXECUTION_SET_TIME:
					myBoundary.showApproveExecutionTime();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_EXECUTION_SET_TIME:
					myBoundary.showDenyExecutionTime();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STATUS_TO_SUSPENDED:
					myBoundary.showChangeRequestSuspended();
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STATUS_TO_ACTIVE:
					myBoundary.showChangeRequestUnsuspended();
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_ALL_ENGINEERS:
					ArrayList<String> employees;
					employees = this.createArrayListOfUserName(result);
					myBoundary.SetComboBox2(employees);
					this.unsubscribeFromClientDeliveries();
					break;
				case UPDATE_CHANGE_REQUEST_STATUS_TO_CLOSED:
					myBoundary.showChangeRequestClosed();
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_EXECUTION_ESTIMATED_DATE:
					Date res = (Date)result.getResultData().get(0).get(0);
					myBoundary.getExecutionEndDate(res);
					this.unsubscribeFromClientDeliveries();
					break;
				case SELECT_ANALYSIS_ESTIMATED_DATE:
					Date res2 = (Date)result.getResultData().get(0).get(0);
					myBoundary.getExecutionEndDate(res2);
					this.unsubscribeFromClientDeliveries();
					break;
					
				default:
					break;
			
			}
		});
		return;
		
	}


	private ArrayList<String> createArrayListOfUserName(SqlResult result)
	{
		ArrayList<String> res = new ArrayList<String>();
		for(ArrayList<Object> user : result.getResultData())
		{
			res.add((String)user.get(1));
		}
		return res;
		
	}



	private ArrayList<ChangeRequest> changeResultToChangerequest(SqlResult result)     // method that filter result into change requests
	{
		ArrayList<ChangeRequest> resultList=new ArrayList<>();
		for(ArrayList<Object> a: result.getResultData())
		{
			ChangeRequest changeRequest=new ChangeRequest((Integer)a.get(0),(String)a.get(1),(Date)a.get(2),
					(String)a.get(3),(String)a.get(4),(String)a.get(5),(String)a.get(6)
					,(String)a.get(7),(String)a.get(8),(String)a.get(9)
					,(String)a.get(10),(Date)a.get(11));
			resultList.add( changeRequest);
		}
		return resultList;	
	}



	public void SelectAllChangeRequest()      
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUEST, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void SelectChangeRequestForAppointments()
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_APPOINTMENTS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void SelectAllChangeRequestForApprovals()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_APPROVALS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}



	public void SelectAllChangeRequestForClose()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_CHANGE_REQUEST_FOR_CLOSE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}



	public void changeCurrentStepFromAnalyzerAutoAppoint(int changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("ANALYZER_SUPERVISOR_APPOINT");
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CURRENT_STEP_TO_ANALYZER_SUPERVISOR_APPOINT, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}



	public void UpdateNewAnalyzerBySupervisor(String newAnalyzerBySupervisorAppoint, Integer changeRequestID)
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("ANALYSIS_SET_TIME");
		varArray.add(newAnalyzerBySupervisorAppoint);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_ANALYZER_BY_SUPERVISOR, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void InsertNewAnalysisStep(Integer changeRequestID, String selectedItem, Date updateStepDate,
			String status)
	{
	
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(selectedItem);
		varArray.add(updateStepDate);
		varArray.add(status);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ANALYSIS_STEP, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void changeCurrentStepToAnalysisSetTime(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add("ANALYSIS_SET_TIME");
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_STEP_TO_ANALYSIS_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void InsertNewAnalysisStepAfterApprove(Integer changeRequestID, String handlerUserName, Date updateStepDate,
			String status)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(handlerUserName);
		varArray.add(updateStepDate);
		varArray.add(status);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_ANALYSIS_STEP_AFTER_APPROVE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}



	public void UpdateExecutionLeaderBySupervisor(String executionLeader,String nextStep, Integer changeRequestID)
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		
		varArray.add(executionLeader);
		varArray.add(nextStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_NEW_EXECUTION_LEADER, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void InsertNewExecutionLeaderStep(Integer changeRequestID, String handlerUserName, Date updateStepDate,
			String status)
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		varArray.add(handlerUserName);
		varArray.add(updateStepDate);
		varArray.add(status);
		SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_NEW_EXECUTION_STEP, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void denyAnalysisTime(String lastStep, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(lastStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_ANALYSIS_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
		
	}



	public void approvedAnalysisTime(String nextStep, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(nextStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_ANALYSIS_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}



	public void approvedExecutionTime(String nextStep, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(nextStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_APPROVE_EXECUTION_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void denyExecutionTime(String lastStep, Integer changeRequestID)
	{

		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(lastStep);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STEP_AFTER_DENY_EXECUTION_SET_TIME, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void suspendChangeRequest(String newStatus,Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newStatus);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STATUS_TO_SUSPENDED, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
		
	}



	public void unsuspendChangeRequest(String newStatus, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newStatus);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STATUS_TO_ACTIVE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}



	public void setComboBox()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ALL_ENGINEERS, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
	}



	public void setStatusToClosed(Date updateStepDate,String newStatus,String newStep, Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(newStatus);
		varArray.add(newStep);
		varArray.add(updateStepDate);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_CHANGE_REQUEST_STATUS_TO_CLOSED, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}



	public void getExecutionEstimatedDate(Integer changeRequestID)
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_EXECUTION_ESTIMATED_DATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}



	public void getAnalysisEstimatedDate(Integer changeRequestID)
	{
	
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.SELECT_ANALYSIS_ESTIMATED_DATE, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}



	public void setEndDate(Date updateStepDate,String newStatus, Integer changeRequestID)
	{
		
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(updateStepDate);
		varArray.add(newStatus);
		varArray.add(changeRequestID);
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_END_DATE_IN_CLOSING_STEP, varArray);
		this.subscribeToClientDeliveries(); // subscribe to listener array
		ClientConsole.client.handleMessageFromClientUI(sqlAction);
		
	}
	
	
	
	
	
	
	
	
	
}
