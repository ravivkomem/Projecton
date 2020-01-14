package assets;

public class AttachmentListCellNonRemoveable<MyFile> extends AttachmentListCell{

	public AttachmentListCellNonRemoveable() {
		super();
		super.hbox.getChildren().remove(super.button);
	}
	
}
