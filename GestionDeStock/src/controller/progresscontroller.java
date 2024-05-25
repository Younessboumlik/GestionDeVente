package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import ringprogress.RingProgressIndicator;

public class progresscontroller implements Initializable{

    @FXML
    private StackPane packp;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("jjjjjjjjjjj");
		// TODO Auto-generated method stub
//		RingProgressIndicator prog = new RingProgressIndicator();
//		prog.setMaxWidth(200);
//		prog.makeIndeterminate();
//		packp.getChildren().add(prog);
		try {
		    RingProgressIndicator prog = new RingProgressIndicator();
		    prog.setMaxWidth(200);
		    prog.makeIndeterminate();
		    prog.setProgress(10);
		    packp.getChildren().add(prog);
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
	}
    

}
