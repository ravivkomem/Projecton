package boundries;

import java.io.IOException;

import client.ClientConsole;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import server.MysqlConnection;

public class ProjectFX extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane anchorPane;
		//DemoLandingBoundry controller;
		LoginPageBoundry controller;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("LoginPage.fxml"));
			anchorPane = loader.load();
			controller = loader.getController();
			/*TODO: Change null to actual ip of server */
			String[] args = null;
			ClientConsole.connection(args);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		Scene scene = new Scene(anchorPane);
		stage.setTitle("ICM Demo");
		stage.setScene(scene);
		stage.show();
		
	}

}
