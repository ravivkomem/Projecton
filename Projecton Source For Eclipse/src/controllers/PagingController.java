package controllers;

import java.io.IOException;

import boundries.DataInitializable;
import boundries.ProjectFX;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


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
	
	public void loadBoundary (String path, Object obj)
	{
		FXMLLoader loader = new FXMLLoader();
		Pane root;
		try {
			root = loader.load(getClass().getResource(path));
			Scene scene = new Scene(root);			
			ProjectFX.mainStage.setScene(scene);		
			ProjectFX.mainStage.show();
			DataInitializable boundary = loader.getController();		
			boundary.initData(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
