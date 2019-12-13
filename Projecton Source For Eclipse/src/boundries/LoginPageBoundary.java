package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.LoginController;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginPageBoundary implements Initializable{

	/* FXML Elements */
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private Button signInButton;
    @FXML
    private ImageView loginLoadingImageView;
    
    /* Private variables */
    private LoginController myController = new LoginController(this);
    private int loginAttempts = 0;
    
    /* Methods */
    @FXML
    void signInButtonPressed(MouseEvent event) {
    	String userName = userNameTextField.getText();
    	String userPassword = userPasswordField.getText();
    	
    	if (userName.equals("") || userPassword.equals(""))
    	{
    		Toast.makeText(ProjectFX.mainStage, "Please fill all the fields", 1500, 500, 500);
    	}
    	else 
    	{
    		loginLoadingImageView.setVisible(true);
    		myController.verifyLoginCredtinals(userName, userPassword);
    	}
    }
    
    public void handleUserAttempInformation(User resultUser)
    {
    	loginLoadingImageView.setVisible(false);
    	
    	if (resultUser == null)
    	{
    		Toast.makeText(ProjectFX.mainStage, "User name or password are incorrect", 1500, 500, 500);
    		loginAttempts++;
    		if (loginAttempts == 3)
        	{
        		/*TODO: Lock user login  */
        	}
    	}
    	else 
    	{
    		/*TODO: Add user to the logged users list */
    		ProjectFX.currentUser = resultUser;
    		loginLoadingImageView.getScene().getWindow().hide();
    		ProjectFX.pagingController.loadBoundray(ProjectPages.DEMO_LANDING_PAGE.getPath());
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loginLoadingImageView.setVisible(false);
	}

}
