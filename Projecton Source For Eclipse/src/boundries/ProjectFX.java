package boundries;

import java.io.IOException;

import assets.ProjectPages;
import controllers.PagingController;
import entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProjectFX extends Application {

	public static Stage mainStage;
	public static User currentUser;
	public static PagingController pagingController;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Pane pane;
		/* DemoLandingBoundry controller; */
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(ProjectPages.CONNECT_TO_SERVER_PAGE.getPath()));
			pane = loader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		Scene scene = new Scene(pane);
		stage.setTitle("ICM Demo");
		stage.setScene(scene);
		stage.show();
		mainStage = stage;
		pagingController = new PagingController();
		
	}

}
