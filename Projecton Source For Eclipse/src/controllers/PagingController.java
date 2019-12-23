package controllers;

import java.io.IOException;

import boundries.DataInitializable;
import boundries.ProjectFX;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class PagingController {
	
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
			DataInitializable boundary = loader.getController();
			boundary.initData(data);
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
	

}
