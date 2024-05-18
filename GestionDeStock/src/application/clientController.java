package application;


import java.sql.Connection;

import classes.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class clientController{
	@FXML
	private TextField textnomclient;
	@FXML
	private TextField textprenomclient;
	@FXML
	private TextField textadressclient;
	@FXML
	private TextField textteleclient;
	
	
	 @FXML
	    void AjouterClient(ActionEvent event) {
		  System.out.println("lllllllllllllllllllll");
          Client client = new Client(0,textnomclient.getText(),textprenomclient.getText(),
        		  textadressclient.getText(),Integer.parseInt(textteleclient.getText()));
          Connection connection = ConnectToDB.connectionDB();
          ConnectToDB.insertClientData(connection, client);
//		 
	    }

	
	  
}