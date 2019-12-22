package controllers;

import assets.SqlResult;
import boundries.RequestListPageBoundary;
import boundries.UploadChangeRequestBoundary;

public class RequestListPageController extends BasicController {
	private RequestListPageBoundary myBoundary;

	public RequestListPageController(RequestListPageBoundary myBoundary)
	{
		this.myBoundary=myBoundary;
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		// TODO Auto-generated method stub
		
	}

}
