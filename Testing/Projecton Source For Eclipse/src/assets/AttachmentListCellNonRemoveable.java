package assets;

/**
 * Extends attachmentListCell
 * Same concept but without remove button
 * This is a list cell that holds an item of object MyFile
 * It will display on the left side the image that represents the file
 * And on the right the file name (file.ext)
 * 
 * @author Raviv Komem
 *
 */
public class AttachmentListCellNonRemoveable<MyFile> extends AttachmentListCell{

	public AttachmentListCellNonRemoveable() {
		super();
		super.hbox.getChildren().remove(super.button);
	}
	
}
