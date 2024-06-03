package controller;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jasypt.util.text.BasicTextEncryptor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ChangePasswordController {



    @FXML
    private PasswordField confirmNewPassword;


    @FXML
    private PasswordField newPassword;
    
    @FXML
    private Label labelcheck;


    @FXML
    public void  changePassword(ActionEvent event) {
		try {
	        BufferedReader readline;
	        BufferedWriter writer = null;
	    
			readline = new BufferedReader(new FileReader("userdata"));
	        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	        textEncryptor.setPassword("Gestion de vente");

	        String input = newPassword.getText();
	         
	        String user = readline.readLine();
	        String email = readline.readLine();
//	        Criptage de mot de passe
	        
//	        decryptage de mot de passe dans le file et la comparaison avec le mot de passe saisie
	        
	        
				if (input.equals(confirmNewPassword.getText())) {
					writer = new BufferedWriter(new FileWriter("userdata"));
					writer.write(user);
					writer.newLine();
					writer.write(email);
					writer.newLine();
//					criptage de mot de passe avant de l'ecrire dans le file
					writer.write(textEncryptor.encrypt(input));
					Stage primaryStage = new Stage();
					Parent root;
					root = FXMLLoader.load(getClass().getResource("/fxml/total.fxml"));
					Scene Scene = new Scene(root);
	 				Scene.getStylesheets().add(getClass().getResource("/css/Main.css").toExternalForm());
					primaryStage.setScene(Scene);
					primaryStage.initStyle(StageStyle.UNDECORATED);
					primaryStage.show();
				} else {
					labelcheck.setText("new password and confirm password are not the same !");
				}
	        readline.close();
	        if (writer != null) {
	            writer.close();
	        }

		} catch (FileNotFoundException e) {
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
		catch(Exception e) {
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
