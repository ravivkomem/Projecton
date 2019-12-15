package server;

import java.io.IOException;
import java.net.URL;
import assets.ProjectPages;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServerApp extends Application {
public static String[] newargs;
	@Override
	public void start(Stage primaryStage) throws IOException {

		URL url = getClass().getResource(ProjectPages.SERVER_START_PAGE.getPath());
		Parent pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane);
		// setting the stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("Start Server");
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				//MysqlConnection mysql = new MysqlConnection();
				//mysql.exitAllClients();
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {	
		//( new MysqlConnection()).openConnection();
		newargs=args;
		launch(args);
	}
}