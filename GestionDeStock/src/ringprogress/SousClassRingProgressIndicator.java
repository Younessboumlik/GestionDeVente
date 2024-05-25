package ringprogress;

import javafx.scene.control.Skin;

public class SousClassRingProgressIndicator extends RingProgressIndicator{
	public SousClassRingProgressIndicator() {
        this.getStylesheets().add(RingProgressIndicator.class.getResource("../css/lowRPI.css").toExternalForm());
        this.getStyleClass().add("ringindicator");
    }
	 @Override
	    protected Skin<?> createDefaultSkin() {
	        return new SousClassRingProgIndSkin(this);
	    }

}
