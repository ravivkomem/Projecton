package boundries;

import java.io.IOException;
import java.util.Optional;

import assets.ProjectPages;
import controllers.PagingController;
import entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
		stage.setTitle("ICM");
		stage.setScene(scene);
		stage.show();
		mainStage = stage;
		pagingController = new PagingController();
		
		mainStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
		
	}
	
	private void closeWindowEvent(WindowEvent event) {
        System.out.println("Window close request ...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Quit application");
        alert.setContentText(String.format("Are you sure you want to quit?"));
        alert.initOwner(mainStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();

        if(res.isPresent()) 
        {
            if(res.get().equals(ButtonType.CANCEL))
            {
            	event.consume();
            }
            else if (currentUser != null)
            {
            	pagingController.userLogout();
            }
        }
    }

}
