package controllers;

import java.io.IOException;

import boundries.ProjectFX;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class PagingController {
	
	public void loadBoundray (String path)
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
		
//		BasicController studentFormController = loader.getController();		
//		studentFormController.loadStudent(Test.students.get(itemIndex));
		
		
	}

}
