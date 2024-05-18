package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LivraisonToCommandeInterface extends Application{
	public void  start(Stage primaryStage){
		
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("LivraisonToCommande.fxml"));
			Scene Scene = new Scene(root);
//			Scene.getStylesheets(j).add(getClass().getResource("application.css").toExternalForm());
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
