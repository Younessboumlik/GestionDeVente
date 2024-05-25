package ringprogress;

public class SousClassRingProgIndSkin extends RingProgressIndicatorSkin{

	public SousClassRingProgIndSkin(RingProgressIndicator indicator) {
		super(indicator);
		// TODO Auto-generated constructor stub
	}
	 public void setProgressLabel(int value) {
	        if (value >= 0) {
	            percentLabel.setText(String.format("%d", value));
	            percentLabel.setStyle("-fx-text-fill: red");
	        }
	    }

}
