package application;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class firstvisit extends Application{
public void  start(Stage primaryStage){
	 
	try {
		BufferedReader reader = new BufferedReader(new FileReader("nbrvisit.txt"));
		
		String nbrvist;
		try {
			nbrvist = reader.readLine();
			System.out.println(nbrvist);
			if (nbrvist.equals("0")) {
			try {
				nbrvist = Integer.toString(Integer.parseInt(nbrvist) + 1); 
			  BufferedWriter writer = new BufferedWriter(new FileWriter("nbrvisit.txt"));
			  writer.write(nbrvist);
			  writer.close();
				  
			       
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/fxml/firstvisit.fxml"));
				Scene Scene = new Scene(root);
//				Scene.getStylesheets().add(getClass().getResource("/fxml/login.css").toExternalForm());
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
			else {
				try {	
					nbrvist = Integer.toString(Integer.parseInt(nbrvist) + 1);
					  BufferedWriter writer = new BufferedWriter(new FileWriter("nbrvisit.txt"));
					  writer.write(nbrvist);
					  writer.close();
				       
					Parent root;
					root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
					Scene Scene = new Scene(root);
					Scene.getStylesheets().add(getClass().getResource("../css/login.css").toExternalForm());
					primaryStage.setResizable(false);
					primaryStage.setScene(Scene);
					primaryStage.show();
				} catch (IOException e) {
					e.getCause();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
		
		// primaryStage.setHeight(400);
		// primaryStage.setWidth(400);
		// primaryStage.setResizable(false);
		// primaryStage.setFullScreen(true);
		// primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
