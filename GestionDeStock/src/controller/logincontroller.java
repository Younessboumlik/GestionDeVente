package controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import com.mysql.cj.protocol.Message;


import org.jasypt.util.text.BasicTextEncryptor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
// import javafx.scene.text.*;
public class logincontroller {
    @FXML
    private TextField usernametext ;
    @FXML
    private PasswordField passwordtext;
    @FXML
    private Label labelcheck ;
    @FXML
    Button oubliermotdepass;
    @FXML
    	

    public void  opencodeverifwindow(ActionEvent event) {
    	try {
    		Stage  primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("../fxml/verificationcode.fxml"));
			Scene Scene = new Scene(root);
//			Scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		}      catch (Exception e) {
        // TODO Auto-generated catch block
            Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
			});
		} 
	}
    	
    

    @FXML
    public void  submit(ActionEvent event) {
      try {
    	  
	        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	        textEncryptor.setPassword("Gestion de vente");
	        
	        
        BufferedReader readline = new BufferedReader(new FileReader("userdata"));
        String input = usernametext.getText();
        String password = passwordtext.getText();
        if (input.equals(textEncryptor.decrypt( readline.readLine()))){
           if (password.equals(textEncryptor.decrypt(readline.readLine()))){
              Stage primaryStage = new Stage();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/fxml/total.fxml"));
				Scene Scene = new Scene(root);
				Scene.getStylesheets().add(getClass().getResource("/css/Main.css").toExternalForm());
				primaryStage.setScene(Scene);
				primaryStage.initStyle(StageStyle.UNDECORATED);
				primaryStage.show();
           }
           else {
            labelcheck.setText("erreur survenue: le mot de passe ou username est incorrect |");
            labelcheck.setStyle("-fx-background-color:#ff6e6e;-fx-text-fill:red");
           }
        }
        else {
            labelcheck.setText("erreur survenue: le mot de passe ou username est incorrect |");
            labelcheck.setStyle("-fx-background-color:#ff6e6e;-fx-text-fill:red");
      
        }
        
        readline.close();
     }
      
     catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
            Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
    } catch (IOException e) {
        // TODO Auto-generated catch block
            Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
alert.showAndWait();
e.printStackTrace();
    });
    } 
   
    }

    }
