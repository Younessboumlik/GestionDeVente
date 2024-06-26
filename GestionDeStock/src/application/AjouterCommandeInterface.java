package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AjouterCommandeInterface extends Application {
		public void  start(Stage primaryStage){
			
			try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/fxml/AjoueCommand.fxml"));
				Scene Scene = new Scene(root);
	    		Scene.getStylesheets().add(getClass().getResource("/css/tablestyle.css").toExternalForm());
////				Scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
				primaryStage.setScene(Scene);
				primaryStage.show();
			} catch (IOException e) {
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


