package boundries;



import java.util.ArrayList;

import client.ChatClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import other.ServerEvent;
import other.SqlResult;

public class DemoLandingBoundries {

    @FXML
    private TextField changeRequestTextField;

    @FXML
    private Button viewButton;

    @FXML
    private TableView<ArrayList<String>> changeRequestDetailsTableView;
    
    @FXML
    private TableColumn<ArrayList<String>, String> initaitorNameColumn;

    @FXML
    private TableColumn<ArrayList<String>, String> subsystemColumn;

    @FXML
    private TableColumn<ArrayList<String>, String> currentStateColumn;

    @FXML
    private TableColumn<ArrayList<String>, String> changeDescriptionColumn;

    @FXML
    private TableColumn<ArrayList<String>, String> statusColumn;

    @FXML
    private TableColumn<ArrayList<String>, String> handlerNameColumn;
    
    @FXML
    private Button updateButton;
    
    private DemoLandingController demoLandingController = new DemoLandingController(this);
    @FXML
    void displayChangeRequestDetails(MouseEvent event) {
    	System.out.println(changeRequestTextField.getText());
    	changeRequestDetailsTableView.setVisible(true);
    	updateButton.setVisible(true);
    	//ChatClient.changeRequestByIdListeners.add(this);
    	demoLandingController.getChangeRequestById(changeRequestTextField.getText());
    }
    @FXML
    void updateChangeRequestTable(MouseEvent event) {

    }
    public void displayChangeRequestTable(SqlResult results)
    {
    	ObservableList<ArrayList<String>> list = FXCollections.observableArrayList();
    	for (ArrayList<Object> rowArray : results.getResultData())
    	{
    		/*Remove the ID because it is not needed for the table */
    		rowArray.remove(0);
    		ArrayList<String> stringArray = new ArrayList<String>();
    		for (Object obj : rowArray)
    		{
    			String string = (String)obj;
    			stringArray.add(string);
    		}
    		list.add(stringArray);		
    	}
    	
    	changeRequestDetailsTableView.setItems(list);
    }
    

}
