package controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Properties;
//
//import com.mysql.cj.protocol.Message;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import com.mysql.cj.protocol.Message;


import org.jasypt.util.text.BasicTextEncryptor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

    void opencodeverifwindow(ActionEvent event) {
//    	Properties props = new Properties();
////    	props.put("mail.smtp.user", "boulidamabdellah8@gmail.com");
//    	props.put("mail.smtp.debug", "true");
//    	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true"); // Enable authentication if needed
//        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption
//
//        props.setProperty("mail.smtp.ssl.trust","*");
//        props.setProperty("mail.smtp.port", "2525");
//
//        props.setProperty("mail.smtp.port", "25");
//        props.setProperty("mail.smtp.ssl.trust","*");
//
//        // Get mail session
//        Session session = Session.getInstance(props);
//
//        try {
//          // Create MimeMessage object
//          MimeMessage email = new MimeMessage(session);
//
//          // Set sender address
//          email.setFrom(new InternetAddress("admin@localserver.com"));
//
//          // Set recipient address
//          email.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse("boulidamabdellah8@gmail.com"));
//
//          // Set subject
//          email.setSubject("code verification");
//
//          // Set message content (text/plain)
//          email.setContent("this is you code " + Math.random()*100, "text/plain; charset=utf-8");
//
//          // Send the email
//          System.out.println("lllllll");
//          Transport transport = session.getTransport("smtp");
//
//          transport.connect("sandbox.smtp.mailtrap.io", "boulidamabdellah8@gmail.com", "d6c113fabad2e1"); // Connect to SMTP server
//
//          transport.connect("smtp-mail.outlook.com", "testsmtplib@outlook.com", "smtplib2023."); // Connect to SMTP server
//
//          transport.sendMessage(email, email.getAllRecipients()); // Send email
//          transport.close();
//
//          System.out.println("Email sent successfully!");
//
//        } catch (MessagingException e) {
//              Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    });
//        }
    	

    	
    	try {
    		Stage  primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("verificationcode.fxml"));
			Scene Scene = new Scene(root);
//			Scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
			    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    });
		}
    }

    @FXML
    void submit(ActionEvent event) {
      try {
    	  
	        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	        textEncryptor.setPassword("Gestion de vente");
	        
	        
        BufferedReader readline = new BufferedReader(new FileReader("userdata"));
        String input = usernametext.getText();
        String password = passwordtext.getText();
        if (input.equals(readline.readLine())){
           if (password.equals(textEncryptor.decrypt(readline.readLine()))){
              labelcheck.setText("your password is correct !");
            labelcheck.setText("your password is correct !");
           }
           else {
            labelcheck.setText("erooooooor you are incorrect !");
           }
        }
        else {
            labelcheck.setText("erooooooor you are incorrect !");
      
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
    });
    } catch (IOException e) {
        // TODO Auto-generated catch block
            Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    });
    } 
   
    }

    }