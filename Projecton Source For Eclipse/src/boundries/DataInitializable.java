package boundries;

import javafx.fxml.Initializable;

/** 
 * This interface is for every boundary which required initialize with specific data objects */
public interface DataInitializable extends Initializable {
	
	/**
	 * This method is used to initialize the boundary with specific object data
	 * @param data
	 */
	public void initData(Object data);
	
}
