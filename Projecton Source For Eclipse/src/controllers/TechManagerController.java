package controllers;

import assets.SqlResult;
import boundries.TechManagerBoundary;
import javafx.application.Platform;

public class TechManagerController extends BasicController {
	private TechManagerBoundary myBoundry;
	
	public TechManagerController(TechManagerBoundary myBoundry) {
		this.myBoundry = myBoundry;
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch (result.getActionType()) {
			
			}

		});
		return;
	}
	
	public void getAllTheActiveChangeRequest(){
		
	}

}
