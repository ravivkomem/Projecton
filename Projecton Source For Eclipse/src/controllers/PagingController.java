package controllers;

import java.io.IOException;
import java.util.ArrayList;

import assets.SqlAction;
import assets.SqlQueryType;
import assets.SqlResult;
import boundries.DataInitializable;
import boundries.ProjectFX;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


@SuppressWarnings("serial")
public class PagingController extends BasicController {
	
	public void loadBoundary (String path)
	{
		FXMLLoader loader = new FXMLLoader();
		Pane root;
		try {
			root = loader.load(getClass().getResource(path));
			Scene scene = new Scene(root);			
			ProjectFX.mainStage.setScene(scene);		
			ProjectFX.mainStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*TODO: Check if the boundary is indeed DataInitializable */
	public void loadBoundary (String path, Object obj)
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		Pane root;
		try {
			root = loader.load();
			DataInitializable boundary = loader.getController();
			boundary.initData(obj);
			Scene scene = new Scene(root);			
			ProjectFX.mainStage.setScene(scene);		
			ProjectFX.mainStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage loadAdditionalStage(String path)
	{
		Stage stage = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			Parent root;
			root = (Parent) loader.load();
			stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stage;
	}
	
	public Stage loadAdditionalStage(String path, Object data)
	{
		Stage stage = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			Parent root;
			root = (Parent) loader.load();
			stage = new Stage();
			stage.setScene(new Scene(root));
			DataInitializable boundary = loader.getController();
			stage.show();
			boundary.initData(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stage;
	}
	
	public void userLogout()
	{
		ArrayList<Object> varArray = new ArrayList<>();
		varArray.add(false);
		varArray.add(ProjectFX.currentUser.getUserID());
		SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_USER_LOGIN_STATUS, varArray);
		
		this.sendSqlActionToClient(sqlAction);
	}

	@Override
	public void getResultFromClient(SqlResult result) {
		Platform.runLater(() -> {
			switch(result.getActionType())
			{
				case UPDATE_USER_LOGIN_STATUS:
					int affectedRows = (int)result.getResultData().get(0).get(0);		
					this.unsubscribeFromClientDeliveries();
					
					if (affectedRows == 0)
					{
						System.out.println("Error with user logout");
					}
					else
					{
						ProjectFX.currentUser = null;
					}
					break;
				default:
					break;
			}
		});
		return;
		
	}
	

}
