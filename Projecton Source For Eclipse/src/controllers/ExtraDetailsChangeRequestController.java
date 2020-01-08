package controllers;

import java.util.ArrayList;
import entities.MyFile;
import assets.SqlResult;
import assets.SqlAction;
import assets.SqlFileAction;
import assets.SqlQueryType;
import boundries.ExtraDetailsChangeRequestBoundary;
import javafx.application.Platform;

@SuppressWarnings("serial")
public class ExtraDetailsChangeRequestController extends BasicController {

	private ExtraDetailsChangeRequestBoundary myBoundary;
	
	public ExtraDetailsChangeRequestController(ExtraDetailsChangeRequestBoundary extraDetailsChangeRequestBoundary) {
		this.myBoundary = extraDetailsChangeRequestBoundary;
	}

	public void getChangeRequestFile(Integer changeRequestID) {

		/* Create sql action */
		ArrayList<Object> varArray = new ArrayList<Object>();
		varArray.add(changeRequestID);
		SqlAction sqlFileAction = new SqlAction(SqlQueryType.DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID, varArray);
		//SqlFileAction sqlFileAction = new SqlFileAction(SqlQueryType.DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID, varArray);
		this.sendSqlActionToClient(sqlFileAction);
	}
	
	/*TODO: ask raviv : how myfile go inside sqlresult and its collapse the program */ 
	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case DOWNLOAD_FILE_BY_CHANGE_REQUEST_ID:
					MyFile downloadedFile = null;
					this.unsubscribeFromClientDeliveries();
					if(!result.getResultData().isEmpty()) {
						downloadedFile = new MyFile("R:\\ServerFiles\\"+result.getResultData().get(0).get(0)
								+"."+result.getResultData().get(0).get(1));
					}
					//MyFile downloadedFile = (MyFile) result.getResultData().get(0).get(0);
					myBoundary.displayFile(downloadedFile);
				default:
					break;
			}
		});
		return;
		
	}

}
