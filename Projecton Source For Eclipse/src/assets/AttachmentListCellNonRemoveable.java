package assets;

/**
 * Extends attachmentListCell
 * Same concept but without remove button
 * @author Raviv Komem
 *
 * @param <MyFile>
 */
public class AttachmentListCellNonRemoveable<MyFile> extends AttachmentListCell{

	public AttachmentListCellNonRemoveable() {
		super();
		super.hbox.getChildren().remove(super.button);
	}
	
}
