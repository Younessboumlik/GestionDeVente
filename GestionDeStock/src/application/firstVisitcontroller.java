package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jasypt.util.text.BasicTextEncryptor;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
public class firstVisitcontroller {

	@FXML
	private PasswordField password;

    @FXML
    private Button save;

    @FXML
    private PasswordField sicuritykey;

    @FXML
    private TextField username;
    @FXML
    private Label waitlabel;
    int i = 0;
    Timeline timeline;

    @FXML
    private ProgressIndicator progressbar;
    double progressVlue ;
    @FXML
    void save(ActionEvent event) {

    	try {
    		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	        textEncryptor.setPassword("Gestion de vente");
			BufferedWriter writer = new BufferedWriter(new FileWriter("userdata"));
			writer.write(textEncryptor.encrypt(username.getText()));
			writer.write('\n');
			writer.write(textEncryptor.encrypt(password.getText()));
			writer.write('\n');
			writer.write(textEncryptor.encrypt(sicuritykey.getText()));
			writer.close();
			progressbar.setVisible(true);
			progressVlue = progressbar.getProgress();
			    timeline = new Timeline(new KeyFrame(Duration.seconds(1),e ->{
				i++;
				progressVlue = progressVlue + 0.33333;
				progressbar.setProgress(progressVlue);
				waitlabel.setText("wait for " + Integer.toString(i) + " s ...");
				if (i == 3) {
					timeline.pause();
					Parent root;
					try {
						root = FXMLLoader.load(getClass().getResource("login.fxml"));
						Scene Scene = new Scene(root);
						Node source = (Node) event.getSource();
						Stage stage = (Stage) source.getScene().getWindow();
						stage.setScene(Scene);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    
    
}
