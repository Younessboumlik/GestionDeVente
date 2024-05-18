package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class suppmodifProduitInterface extends Application{
	public void start(Stage primaryStage){
		
		
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("produitsupmodif.fxml"));
			Scene Scene = new Scene(root);
    		Scene.getStylesheets().add(getClass().getResource("tablestyle.css").toExternalForm());
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

	public static void main(String[] args) {
		launch(args);
	}
}
