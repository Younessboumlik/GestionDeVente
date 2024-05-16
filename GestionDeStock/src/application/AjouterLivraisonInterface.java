package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AjouterLivraisonInterface extends Application {
		public void start(Stage primaryStage){
			
			try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("AjouterLivraison.fxml"));
				Scene Scene = new Scene(root);
//				Scene.getStylesheets(j).add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(Scene);
				primaryStage.show();
			} catch (IOException e) {
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


