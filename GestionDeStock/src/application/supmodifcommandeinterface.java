package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class supmodifcommandeinterface extends Application{
	public void start(Stage primaryStage){
		
		
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("modifsuppcommande.fxml"));
			Scene Scene = new Scene(root);
//			Scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(Scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	public static void main(String[] args) {
		launch(args);
	}
}
