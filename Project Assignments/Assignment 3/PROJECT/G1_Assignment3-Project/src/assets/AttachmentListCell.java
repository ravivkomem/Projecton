package assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.filechooser.FileSystemView;

import entities.MyFile;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * This is a class for list view cell with a file attachment
 * @author Raviv Komem
 *
 */
public class AttachmentListCell extends ListCell<MyFile> {
	    
    	protected static HashMap<String, Image> mapOfFileExtToSmallIcon = new HashMap<String, Image>();
    	protected static final int LIST_ROW_HEIGHT = 24;
    	protected final Image buttonImage;
    	protected HBox hbox = new HBox(5);
    	protected Label label = new Label("");
    	protected Pane pane = new Pane();
    	protected ImageView imageView = new ImageView();
    	protected Button button = new Button();
		 
    	/**
    	 * Empty constructor
    	 */
		public AttachmentListCell() {
			super();
			buttonImage = new Image(getClass().getResourceAsStream("/boundries/X_icon.jpeg"));
            HBox.setHgrow(pane, Priority.ALWAYS);
            ImageView buttomImageView = new ImageView(buttonImage);
            buttomImageView.setFitHeight(LIST_ROW_HEIGHT);
            buttomImageView.setPreserveRatio(true);
            button.setGraphic(buttomImageView);
            button.setPadding(Insets.EMPTY);
            button.setOnAction(event -> getListView().getItems().remove(getItem()));
            hbox.getChildren().addAll(button, label, pane);
			
		}
		
		 @Override
	     public void updateItem(MyFile item, boolean empty) {
	            super.updateItem(item, empty);
	            if (empty || item == null || item.getFileName().equals("")) {
	                setGraphic(null);
	                setText(null);
	            } else {
	                Image fxImage = getFileIcon(item.getFileName());
	                imageView = new ImageView(fxImage);
	                label.setGraphic(imageView);
	                label.setText(item.getFileName());
	                setGraphic(hbox);
	            }
	     }
		 
		 
		 private static String getFileExt(String fname) {
		        String ext = ".";
		        int p = fname.lastIndexOf('.');
		        if (p >= 0) {
		            ext = fname.substring(p);
		        }
		        return ext.toLowerCase();
		    }
			
			private static javax.swing.Icon getJSwingIconFromFileSystem(File file) {
				
		        FileSystemView view = FileSystemView.getFileSystemView();
		        javax.swing.Icon icon = view.getSystemIcon(file);

		        return icon;
		    }
			
			private static Image getFileIcon(String fname) {
		        final String ext = getFileExt(fname);

		        Image fileIcon = mapOfFileExtToSmallIcon.get(ext);
		        if (fileIcon == null) {

		            javax.swing.Icon jswingIcon = null; 

		            File file = new File(fname);
		            if (file.exists()) {
		                jswingIcon = getJSwingIconFromFileSystem(file);
		            }
		            else {
		                File tempFile = null;
		                try {
		                    tempFile = File.createTempFile("icon", ext);
		                    jswingIcon = getJSwingIconFromFileSystem(tempFile);
		                }
		                catch (IOException ignored) {
		                    // Cannot create temporary file. 
		                }
		                finally {
		                    if (tempFile != null) tempFile.delete();
		                }
		            }

		            if (jswingIcon != null) {
		                fileIcon = jswingIconToImage(jswingIcon);
		                mapOfFileExtToSmallIcon.put(ext, fileIcon);
		            }
		        }

		        return fileIcon;
		    }
			
			 private static Image jswingIconToImage(javax.swing.Icon jswingIcon) {
			        BufferedImage bufferedImage = new BufferedImage(jswingIcon.getIconWidth(), jswingIcon.getIconHeight(),
			                BufferedImage.TYPE_INT_ARGB);
			        jswingIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
			        return SwingFXUtils.toFXImage(bufferedImage, null);
			 }
}

		