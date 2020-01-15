package boundries;

import java.net.URL;
import java.util.ResourceBundle;

import assets.ProjectPages;
import assets.Toast;
import controllers.LoginController;
import entities.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

// TODO: Auto-generated Javadoc
/**
 * This is the login page boundary.
 *
 * @author Raviv Komem
 */
public class LoginPageBoundary implements Initializable{

	/** The user name text field. */
	/* *******************************
	 * ****** FXML Objects ***********
	 * ******************************/
    @FXML
    private TextField userNameTextField;
    
    /** The user password field. */
    @FXML
    private PasswordField userPasswordField;
    
    /** The sign in button. */
    @FXML
    private Button signInButton;
    
    /** The login loading image view. */
    @FXML
    private ImageView loginLoadingImageView;
    
    /** The my controller. */
    /* ***************************************
     * ********** Private Objects ************
     * ***************************************/
    private LoginController myController = new LoginController(this);
    
    /** The login attempts. */
    private int loginAttempts = 0;
    
    /* ***************************************
     * ********** FXML Methods ***************
     * ***************************************/
    /**
     * This method is used in order to attem user sign in.
     *
     * @param event - Mouse click on "sign in" button
     */
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
    
    /**
     * This method is used to handle user attepmpt information from the controller.
     *
     * @param resultUser - the user with the password and username entered by user
     */
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
    		if (resultUser.isLogged() == true)
    		{
    			Toast.makeText(ProjectFX.mainStage, "This user is already logged", 1500, 500, 500);
    		}
    		else
    		{
    			myController.changeUserLoginStatus(resultUser, true);
    			ProjectFX.currentUser = resultUser;
        		ProjectFX.pagingController.loadBoundary(ProjectPages.MENU_PAGE.getPath());
    		}
    	}
    }

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loginLoadingImageView.setVisible(false);
		userPasswordField.setOnKeyPressed(new EventHandler<KeyEvent>()
				{

					@Override
					public void handle(KeyEvent ke) {
						if (ke.getCode().equals(KeyCode.ENTER))
			            {
							signInButtonPressed(null);
			            }
						
					}
			
				});
	}

}
