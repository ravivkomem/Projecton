package boundries;

import controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/*TODO: Add methods for the links or remove them */
public class LoginPageBoundary {

    @FXML private TextField userNameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Button loginButton;
    @FXML private Button registertrationButton;
    @FXML private Button exitButton;
    @FXML private Hyperlink ForgotPasswordLink; 
    @FXML private Hyperlink ContectUsLink;
    @FXML private Hyperlink PrivacyTermLink;
    @FXML private ImageView loadingLoginGif;
   
    private LoginController myController = new LoginController(this);
    
    @FXML
    void exitSystem(MouseEvent event) {
    	System.out.println("Client is closed");
    	System.exit(0);
    }

    @FXML
    void ClickbtnContactus(ActionEvent event) {

    }

    @FXML
    void ClickbtnForgotPass(ActionEvent event) {

    }

    @FXML
    void ClickbtnPrivacyTerms(ActionEvent event) {

    }

    @FXML
    void ClickbtnRegistration(ActionEvent event) {

    }
    @FXML
    void loginAttemp(ActionEvent event) {
    	loadingLoginGif.setVisible(true);
    	String result = myController.verifyLoginCredtinals(userNameTextField.getText(), passwordTextField.getText());
    	System.out.println("bla");
 
    }
    
    public void displayLoginError ()
    {
    	loadingLoginGif.setVisible(false);
    	userNameTextField.setText("INCORRECT");
    	passwordTextField.setText("INCORRECT");
    }

}
