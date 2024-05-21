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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


public class ChangePasswordController {



    @FXML
    private PasswordField confirmNewPassword;


    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;
    
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

	        String password = oldPassword.getText();
	        String input = newPassword.getText();
	         
	        String user = readline.readLine();
//	        Criptage de mot de passe
	        
//	        decryptage de mot de passe dans le file et la comparaison avec le mot de passe saisie
	        
	        if (password.equals(textEncryptor.decrypt(readline.readLine()))){
				if (input.equals(confirmNewPassword.getText())) {
					writer = new BufferedWriter(new FileWriter("userdata"));
					writer.write(user);
					writer.newLine();
//					criptage de mot de passe avant de l'ecrire dans le file
					writer.write(textEncryptor.encrypt(input));
					labelcheck.setText("your password is changed !");
					System.out.println("Password changer...");
				} else {
					labelcheck.setText("new password and confirm password are not the same !");
				}
	        }
			else {
				labelcheck.setText("old passord is incorrect !");
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
