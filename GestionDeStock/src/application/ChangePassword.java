package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChangePassword extends Application {

	@Override
	public void  start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/fxml/changepassword.fxml"));
			Scene scene = new Scene(root,663,419);
			scene.getStylesheets().add(getClass().getResource("/fxml/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) { 
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

	public static void main(String[] args) {
		launch(args);
	}
}
