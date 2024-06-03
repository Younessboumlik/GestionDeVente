package controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.jasypt.util.text.BasicTextEncryptor;

import application.SendMail;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class verificationController implements Initializable{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button verifybutton;
    @FXML
    private TextField codeinput;
    String email;
    int codeAverifier;

    @FXML
    public void  verify(ActionEvent event) {
       if (Integer.parseInt(codeinput.getText()) == codeAverifier){
    	   Stage primaryStage = new Stage();
    	   AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/fxml/changepassword.fxml"));
			Scene scene = new Scene(root,663,419);
			scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaxWidth(357);
	        primaryStage.setMaxHeight(319);
			primaryStage.setMinWidth(357);
	        primaryStage.setMinHeight(319);
			primaryStage.show();
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
       }
       else {
    	     Platform.runLater(() -> {
    	         Alert alert = new Alert(Alert.AlertType.ERROR);
    	         alert.setTitle("Erreur");
    	         alert.setHeaderText("Une erreur s'est produite.");
    	         alert.setContentText("le code est incorrect ");
    	         alert.showAndWait();
    	     });
    	   
       }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("Gestion de vente");
        
        
        try {
			BufferedReader readline = new BufferedReader(new FileReader("userdata"));
			try {
				readline.readLine();
				email = textEncryptor.decrypt(readline.readLine());
				codeAverifier = (int)(Math.random()*100000) ;
				System.out.println(email + codeAverifier);
				SendMail.sendmail(email, codeAverifier);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
