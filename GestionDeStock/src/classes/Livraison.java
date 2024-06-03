package classes;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

import application.ConnectToDB;
import controller.SupModifLivraisonController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Livraison {

	public int numeroLivraison;
	public LocalDate dateLivraison;
	public int numeroCommande;

//	Ce button est associe a chaque objet de type Facture, il va nous permet de le supprimer du tableauview.
	public Button deletebutton;
	public Button modifbutton;
	
	public Livraison(int numeroLivraison, LocalDate dateLivraison,int numeroCommande) {
		this.numeroLivraison = numeroLivraison;
		this.dateLivraison = dateLivraison;
		this.numeroCommande = numeroCommande;
		
//		Le button de suppresion
		this.deletebutton = new Button();
		this.deletebutton.setText("Delete");

		this.deletebutton.setStyle("-fx-background-color:#cc0202;-fx-text-fill:white;");
		Image image = new Image("images/delete.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(16);
		imageView.setFitHeight(16);
		this.deletebutton.setGraphic(imageView);
		this.deletebutton.setOnAction(event -> deleteLivraison());


//		Le button de modification

		this.modifbutton = new Button("modifier");
		this.modifbutton.setStyle("-fx-background-color:blue;-fx-text-fill:white;");
		Image imagemod = new Image("imges/pen.png");
		ImageView imageViewmod = new ImageView(imagemod);
		imageViewmod.setFitWidth(16);
		imageViewmod.setFitHeight(16);
		this.modifbutton.setGraphic(imageViewmod);

		this.modifbutton.setOnAction(event -> modifieLivraison());
		
	}

	public void  deleteLivraison() {

//		System.out.println(this.montant);
		
//		Un alert pour confirmer la suppresion

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Suppresion de facture");
		alert.setContentText("Tu veux supprimer la facture de maniere definitive?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isEmpty() || result.get() == ButtonType.CANCEL) {
		}
		else if(result.get() == ButtonType.OK) {
			Connection connexion = ConnectToDB.connectionDB();
			ConnectToDB.delete(connexion, "livraison", "numerolivraison", this.numeroLivraison);

			SupModifLivraisonController.refreshLivraison();
		}
	}
	
	public void  modifieLivraison() {
		SupModifLivraisonController.modifierLivraison(this);
	}
	
//	public void  modifieclicked() {
//		SampleController.modifierFacture(this);
//	}
//	les getters et les setters.
	public int getNumeroLivraison() {
		return numeroLivraison;
	}
	public void  setNumeroLivraison(int numeroLivraison) {
		this.numeroLivraison = numeroLivraison;
	}
	public LocalDate getDateLivraison() {
		return dateLivraison;
	}
	public void  setDateLivraison(LocalDate dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	
	public int getNumeroCommande() {
		return numeroCommande;
	}
	public void  setNumeroCommande(int numeroCommande) {
		this.numeroCommande = numeroCommande;
	}
	public Button getDeletebutton() {
		return deletebutton;
	}
	public void  setDeletebutton(Button deletebutton) {
		this.deletebutton = deletebutton;
	}
	public Button getModifbutton() {
		return modifbutton;
	}
	public void  setModifbutton(Button modifbutton) {
		this.modifbutton = modifbutton;
	}

}
